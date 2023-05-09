package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.AttrGroupVo.*;
import com.zhangsisiyao.common.vo.product.AttrGroupVo;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.common.vo.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.dao.AttrGroupDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrGroupQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Autowired
    AttrAttrgroupRelationService attrgroupRelationService;

    @Autowired
    AttrService attrService;

    @Override
    @Cacheable(value = {"attrGroup"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(AttrGroupQueryVo params) {
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        AttrGroupVo attrGroupVo=params.getAttrGroupVo();
        if(attrGroupVo.getAttrGroupId()!=null){
            wrapper.eq("attr_group_id",attrGroupVo.getAttrGroupId());
        }
        if(attrGroupVo.getAttrGroupName()!=null){
            wrapper.eq("attr_group_name",attrGroupVo.getAttrGroupName());
        }
        if(attrGroupVo.getSort()!=null){
            wrapper.eq("sort",attrGroupVo.getSort());
        }
        if(attrGroupVo.getCatalogId()!=null){
            wrapper.like("catalog_id",attrGroupVo.getCatalogId());
        }

        if(StringUtils.isNotEmpty(params.getPageParams().getKey())){
            wrapper.like("attr_group_name",params.getPageParams().getKey());
        }
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params.getPageParams().getPageIndex(), params.getPageParams().getPageSize()),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attrRelation"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryNoAttrRelation(String attrGroupId, PageParamVo params) {
        Long catalogId=this.query().eq("attr_group_id",attrGroupId).one().getCatalogId();
        List<AttrAttrgroupRelationEntity> exist = attrgroupRelationService.query().eq("attr_group_id", attrGroupId).list();
        Set<Long> set=new HashSet<>();
        for(AttrAttrgroupRelationEntity entity:exist){
            set.add(entity.getAttrId());
        }
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().and(attrEntityQueryWrapper -> attrEntityQueryWrapper.eq("catalog_id", catalogId).eq("attr_type",1).or().eq("attr_type",2));
        if(set.size()!=0){
            wrapper=wrapper.notIn("attr_id", set);
        }
        if(StringUtils.isNotEmpty(params.getKey())){
            wrapper=wrapper.like("attr_name",params.getKey());
        }

        IPage<AttrEntity> page = attrService.page(
                new Query<AttrEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attrRelation"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryAttrRelation(String attrGroupId,PageParamVo params) {
        QueryWrapper<AttrAttrgroupRelationEntity> attrEntityQueryWrapper = new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId);

        IPage<AttrAttrgroupRelationEntity> page = attrgroupRelationService.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                attrEntityQueryWrapper
        );
        List<AttrVo> attrs=new ArrayList<>();
        page.getRecords().forEach((attrAttrgroupRelationEntity -> {
            AttrEntity attr = attrService.getById(attrAttrgroupRelationEntity.getAttrId());
            AttrVo attrVo=new AttrVo();
            if(attr!=null){
                BeanUtils.copyProperties(attr,attrVo);
            }else {
                attrVo.setAttrId(attrAttrgroupRelationEntity.getAttrId());
                attrVo.setAttrName("属性不存在，请删除");
            }
            attrs.add(attrVo);
        }));
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(attrs);
        return pageUtils;
    }

    @Override
    @Cacheable(value = {"attrGroup"},keyGenerator = "customKeyGenerator",sync = true)
    public List<AttrGroupVo> queryWithAttrGroup(String catalogId) {
        //TODO 修改时间复杂度，使用联表查询
        List<AttrGroupEntity> attrGroup = this.query().eq("catalog_id", catalogId).list();
        List<AttrGroupVo> attrGroupVos=new ArrayList<>();
        for(AttrGroupEntity attrGroupEntity:attrGroup){
            AttrGroupVo cur=new AttrGroupVo();
            cur.setAttrs(new ArrayList<>());
            BeanUtils.copyProperties(attrGroupEntity,cur);
            List<AttrEntity> attrEntities = attrService.queryWithAttrGroup(String.valueOf(attrGroupEntity.getAttrGroupId()));
            attrEntities.forEach(attr -> {
                AttrVo attrVo=new AttrVo();
                BeanUtils.copyProperties(attr,attrVo);
                cur.getAttrs().add(attrVo);
            });
            attrGroupVos.add(cur);
        }
        return attrGroupVos;
    }



    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public void addAttrRelation(List<AttrAttrgroupRelationVo> relationEntities) {
        relationEntities.forEach((vo)->{
            if(attrgroupRelationService.query().eq("attr_id", vo.getAttrId()).eq("attr_group_id", vo.getAttrGroupId()).list().size()==0){
                AttrAttrgroupRelationEntity relation=new AttrAttrgroupRelationEntity();
                BeanUtils.copyProperties(vo,relation);
                attrgroupRelationService.save(relation);
            }
        });
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public void deleteAttrRelation(List<AttrAttrgroupRelationVo> relationVos) {
        for(AttrAttrgroupRelationVo relation:relationVos){
            QueryWrapper<AttrAttrgroupRelationEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("attr_id", relation.getAttrId()).eq("attr_group_id", relation.getAttrGroupId());
            attrgroupRelationService.remove(wrapper);
        }
    }

    @Override
    public void deleteAttrGroup(Long[] ids) {
        //删除关联属性
        for(Long id:ids){
            attrgroupRelationService.query().eq("attr_group_id",id).list().forEach((relation)->{
                this.attrgroupRelationService.removeById(relation);
            });
        }

        //删除属性分组
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList){
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean updateBatchById(Collection<AttrGroupEntity> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean save(AttrGroupEntity attrGroup){
        return super.save(attrGroup);
    }



    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean saveBatch(Collection<AttrGroupEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<AttrGroupEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean remove(Wrapper<AttrGroupEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean updateById(AttrGroupEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean update(Wrapper<AttrGroupEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean update(AttrGroupEntity entity, Wrapper<AttrGroupEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

}