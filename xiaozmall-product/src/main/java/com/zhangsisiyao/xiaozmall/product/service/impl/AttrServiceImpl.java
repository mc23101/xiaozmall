package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.AttrDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.service.ProductAttrValueService;
import com.zhangsisiyao.common.vo.AttrValueVo;
import com.zhangsisiyao.xiaozmall.product.vo.PageParamVo;
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
    public PageUtils queryPage(PageParamVo params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params.getPageIndex(),params.getPageIndex()),
                new QueryWrapper<>()
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
    public List<AttrValueVo> queryListForSpu(Long spuId) {
        List<AttrValueVo> result=new ArrayList<>();
        productAttrValueService.query().eq("spu_id", spuId).list().forEach(attr->{
            AttrValueVo cur=new AttrValueVo();
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
    public void UpdateAttrsBySpuId(List<AttrValueVo> attrs, String spuId) {
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