package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.SkuInfoVo;
import com.zhangsisiyao.xiaozmall.product.dao.SkuInfoDao;
import com.zhangsisiyao.xiaozmall.product.entity.SkuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuInfoService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.SkuInfoQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    @Cacheable(value = {"SkuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(SkuInfoQueryVo params) {
        QueryWrapper<SkuInfoEntity> wrapper = new QueryWrapper<>();
        SkuInfoVo skuInfoVo=params.getSkuInfoVo();
        if(skuInfoVo.getSkuId()!=null){
            wrapper.eq("sku_id",skuInfoVo.getSkuId());
        }
        if(skuInfoVo.getSkuName()!=null){
            wrapper.eq("spu_id",skuInfoVo.getSkuId());
        }
        if(skuInfoVo.getSkuName()!=null){
            wrapper.eq("sku_name",skuInfoVo.getSkuName());
        }
        if(skuInfoVo.getCatalogId()!=null){
            wrapper.eq("catalog_id",skuInfoVo.getCatalogId());
        }
        if(skuInfoVo.getBrandId()!=null){
            wrapper.eq("brand_id",skuInfoVo.getBrandId());
        }
        if(skuInfoVo.getPrice()!=null){
            wrapper.eq("price",skuInfoVo.getPrice());
        }
        if(skuInfoVo.getSaleCount()!=null){
            wrapper.eq("sale_count",skuInfoVo.getSaleCount());
        }

        if(params.getPageParamVo().getKey()!=null){
            wrapper.like("sku_name",params.getPageParamVo().getKey());
        }

        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params.getPageParamVo().getPageIndex(),params.getPageParamVo().getPageSize()),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"SkuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPageLimit(SkuInfoQueryVo queryVo) {
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        if(queryVo.getBrandId()!=0){
            queryWrapper=queryWrapper.eq("brand_id",queryVo.getBrandId());
        }
        if(queryVo.getCatalogId()!=0){
            queryWrapper=queryWrapper.eq("catalog_id",queryVo.getCatalogId());
        }
        if(StringUtils.isNotEmpty(queryVo.getPageParams().getKey())){
            queryWrapper=queryWrapper.like("spu_name", queryVo.getPageParams().getKey());
        }
        if(queryVo.getMin().compareTo(queryVo.getMax()) < 0){
            queryWrapper=queryWrapper.between("price",queryVo.getMin(),queryVo.getMax());
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(queryVo.getPageParams().getPageIndex(),queryVo.getPageParams().getPageSize()),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"SkuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public List<SkuInfoVo> listWithCatalogBrandSpu(String catalog, String brand, String spu) {
        List<SkuInfoVo> result=new ArrayList<>();
        this.query().eq("catalog_id",catalog).eq("brand_id",brand).eq("spu_id",spu).list().forEach((sku)->{
            SkuInfoVo cur=new SkuInfoVo();
            BeanUtils.copyProperties(sku,cur);
            result.add(cur);
        });
        return result;
    }


    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean save(SkuInfoEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean saveBatch(Collection<SkuInfoEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SkuInfoEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean remove(Wrapper<SkuInfoEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean updateById(SkuInfoEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean update(Wrapper<SkuInfoEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean update(SkuInfoEntity entity, Wrapper<SkuInfoEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuInfo"},allEntries = true)
    public boolean updateBatchById(Collection<SkuInfoEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}