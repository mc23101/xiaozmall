package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.dao.SkuSaleAttrValueDao;
import com.zhangsisiyao.xiaozmall.product.entity.SkuSaleAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuSaleAttrValueService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    @Cacheable(value = {"SkuSaleAttrValue"},keyGenerator = "customKeyGenerator")
    public PageUtils queryPage(PageParamVo params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean save(SkuSaleAttrValueEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean saveBatch(Collection<SkuSaleAttrValueEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SkuSaleAttrValueEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean remove(Wrapper<SkuSaleAttrValueEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean updateById(SkuSaleAttrValueEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean update(Wrapper<SkuSaleAttrValueEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean update(SkuSaleAttrValueEntity entity, Wrapper<SkuSaleAttrValueEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuSaleAttrValue"},allEntries = true)
    public boolean updateBatchById(Collection<SkuSaleAttrValueEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}