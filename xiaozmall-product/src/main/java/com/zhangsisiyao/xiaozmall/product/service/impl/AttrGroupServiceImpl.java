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
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.dao.AttrGroupDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
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
    public PageUtils queryPage(PageParamVo params) {

        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params.getPageIndex(), params.getPageSize()),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attrGroup"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPageByColumn(Object column, Object val, PageParamVo params) {
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        if(column!=null){
            queryWrapper=queryWrapper.eq(String.valueOf(column),val);
        }
        if(StringUtils.isNotEmpty(params.getKey())){
            queryWrapper=queryWrapper.like("attr_group_name",params.getKey());
        }
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params.getPageIndex(), params.getPageSize()),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attrGroup"},keyGenerator = "customKeyGenerator",sync = true)
    public List<AttrGroupVo> queryWithAttr(String catalogId) {
        //TODO 修改时间复杂度，使用联表查询
        List<AttrGroupEntity> attrGroup = this.query().eq("catalog_id", catalogId).list();
        List<AttrGroupVo> attrGroupVos=new ArrayList<>();
        for(AttrGroupEntity attrGroupEntity:attrGroup){
            AttrGroupVo cur=new AttrGroupVo();
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
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean save(AttrGroupEntity attrGroup){
        return super.save(attrGroup);
    }

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList){
        return super.removeByIds(idList);
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

    @Override
    @CacheEvict(value = {"attrGroup"},allEntries = true)
    public boolean updateBatchById(Collection<AttrGroupEntity> entityList) {
        return super.updateBatchById(entityList);
    }

    @Override
    @Cacheable(value = {"attrRelation"},key = "#root.methodName+#root.args",sync = true)
    public List<AttrVo> queryAttrRelation(String attrGroupId) {
        List<AttrAttrgroupRelationEntity> attr_group_id = attrgroupRelationService.query().eq("attr_group_id", attrGroupId).list();
        List<AttrVo> attrVos=new ArrayList<>();
        for(AttrAttrgroupRelationEntity entity:attr_group_id){
            List<AttrVo> cur=new ArrayList<>();
            attrService.query().eq("attr_id",entity.getAttrId()).list().forEach((attr)->{
                AttrVo curVo=new AttrVo();
                BeanUtils.copyProperties(attr,curVo);
                cur.add(curVo);
            });
            attrVos.addAll(cur);
        }
        return attrVos;
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public void addAttrRelation(List<AttrAttrgroupRelationVo> relationEntities) {
        relationEntities.forEach((vo)->{
            AttrAttrgroupRelationEntity relation=new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(vo,relation);
            attrgroupRelationService.save(relation);
        });
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public void deleteAttrRelation(Long[] ids) {
        for(Long relationId:ids){
            attrgroupRelationService.removeById(relationId);
        }
    }

    @Override
    @Cacheable(value = {"attrRelation"},key = "#root.methodName+#root.args",sync = true)
    public PageUtils queryNoAttrRelation(String attrGroupId, PageParamVo params) {
        Long catalogId=this.query().eq("attr_group_id",attrGroupId).one().getCatalogId();
        List<AttrAttrgroupRelationEntity> exist = attrgroupRelationService.query().eq("attr_group_id", attrGroupId).list();
        Set<Long> set=new HashSet<>();
        for(AttrAttrgroupRelationEntity entity:exist){
            set.add(entity.getAttrId());
        }
        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>().eq("catalog_id", catalogId).eq("attr_type",1).or().eq("attr_type",2);
        if(set.size()!=0){
            attrEntityQueryWrapper=attrEntityQueryWrapper.notIn("attr_id", set);
        }

        IPage<AttrEntity> page = attrService.page(
                new Query<AttrEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                attrEntityQueryWrapper

        );
        return new PageUtils(page);
    }
}