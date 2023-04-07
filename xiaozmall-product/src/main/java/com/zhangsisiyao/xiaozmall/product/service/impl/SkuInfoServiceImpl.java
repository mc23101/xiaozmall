package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.SkuInfoDao;
import com.zhangsisiyao.xiaozmall.product.entity.SkuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuInfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    @Cacheable(value = {"SkuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"SkuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPageLimit(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        if(params.containsKey("brandId")&&!params.get("brandId").equals("0")){
            queryWrapper=queryWrapper.eq("brand_id",params.get("brandId"));
        }
        if(params.containsKey("catalogId")&&!params.get("catalogId").equals("0")){
            queryWrapper=queryWrapper.eq("catalog_id",params.get("catalogId"));
        }
        if(params.containsKey("key")&&!params.get("key").equals("")){
            queryWrapper=queryWrapper.like("spu_name", params.get("key"));
        }
        if(params.containsKey("max")&&params.containsKey("min")){
            BigDecimal max= new BigDecimal((String) params.get("max"));
            BigDecimal min= new BigDecimal((String) params.get("min"));
            if(max.compareTo(new BigDecimal("-1"))!=0&&min.compareTo(new BigDecimal("-1"))!=0){
                queryWrapper=queryWrapper.between("price",min,max);
            }
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"SkuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public List<SkuInfoEntity> listWithCatalogBrandSpu(String catalog, String brand, String spu) {
        return this.query().eq("catalog_id",catalog).eq("brand_id",brand).eq("spu_id",spu).list();
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