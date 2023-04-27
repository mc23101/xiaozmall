package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.dao.AttrDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.service.ProductAttrValueService;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Override
    @Cacheable(value = {"attr"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(AttrQueryVo params) {
        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();
        AttrVo attrVo=params.getAttrVo();
        if(attrVo.getAttrId()!=null){
            wrapper.eq("attr_id",attrVo.getAttrId());
        }
        if(attrVo.getAttrName()!=null){
            wrapper.eq("attr_name",attrVo.getAttrName());
        }
        if(attrVo.getSearchType()!=null){
            wrapper.eq("search_type",attrVo.getSearchType());
        }
        if(attrVo.getAttrType()!=null){
            wrapper.eq("attr_type",attrVo.getAttrType());
        }
        if(attrVo.getEnable()!=null){
            wrapper.eq("enable",attrVo.getEnable());
        }
        if(attrVo.getCatalogId()!=null){
            wrapper.eq("catalog_id",attrVo.getCatalogId());
        }
        if(attrVo.getShowDesc()!=null){
            wrapper.eq("show_desc",attrVo.getShowDesc());
        }
        if(StringUtils.isNotEmpty(params.getPageParams().getKey())){
            wrapper.like("attr_name",params.getPageParams().getKey());
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params.getPageParams().getPageIndex(),params.getPageParams().getPageIndex()),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attr"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryBaseAttr(Long catId, PageParamVo params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>();
        if(catId!=0){
            queryWrapper=queryWrapper.eq("catalog_id",catId);
        }
        if(StringUtils.isNotEmpty(params.getKey())){
            queryWrapper=queryWrapper.like("attr_name",params.getKey());
        }
        queryWrapper=queryWrapper.eq("attr_type",1).or().eq("attr_type",2);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attr"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils querySaleAttr(Long catId, PageParamVo params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>();
        if(catId!=0){
            queryWrapper=queryWrapper.eq("catalog_id",catId);
        }
        if(StringUtils.isNotEmpty(params.getKey())){
            queryWrapper=queryWrapper.like("attr_name",params.getKey());
        }
        queryWrapper=queryWrapper.eq("attr_type",0).or().eq("attr_type",2);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attr"},keyGenerator = "customKeyGenerator",sync = true)
    public List<AttrEntity> queryWithAttrGroup(String groupId) {
        return this.baseMapper.queryWithAttrGroup(groupId);
    }

    @Override
    @Cacheable(value = {"attr"},keyGenerator = "customKeyGenerator",sync = true)
    public List<AttrVo.AttrValueVo> queryListForSpu(Long spuId) {
        List<AttrVo.AttrValueVo> result=new ArrayList<>();
        productAttrValueService.query().eq("spu_id", spuId).list().forEach(attr->{
            AttrVo.AttrValueVo cur=new AttrVo.AttrValueVo();
            BeanUtils.copyProperties(attr,cur);
            result.add(cur);
        });
        return result;
    }

    @Override
    @Cacheable(value = {"attr"},keyGenerator = "customKeyGenerator",sync = true)
    public AttrEntity getById(Serializable attr) {
        return super.getById(attr);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean save(AttrEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean saveBatch(Collection<AttrEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<AttrEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean remove(Wrapper<AttrEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean updateById(AttrEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean update(Wrapper<AttrEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean update(AttrEntity entity, Wrapper<AttrEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public boolean updateBatchById(Collection<AttrEntity> entityList) {
        return super.updateBatchById(entityList);
    }


    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public void UpdateAttrsBySpuId(List<AttrVo.AttrValueVo> attrs, String spuId) {
        attrs.forEach((attrVo)->{
            ProductAttrValueEntity one = productAttrValueService.query().eq("spu_id", spuId).eq("attr_id", attrVo.getAttrId()).one();
            if(one!=null){
                one.setAttrValue(attrVo.getAttrValue());
                productAttrValueService.updateById(one);
            }else{
                ProductAttrValueEntity newValue=new ProductAttrValueEntity();
                newValue.setAttrValue(attrVo.getAttrValue());
                newValue.setSpuId(Long.valueOf(spuId));
                newValue.setAttrId(attrVo.getAttrId());
                productAttrValueService.save(newValue);
            }
        });
    }


}