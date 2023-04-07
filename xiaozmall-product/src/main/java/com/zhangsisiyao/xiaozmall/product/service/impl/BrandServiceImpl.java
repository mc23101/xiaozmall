package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.BrandDao;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    @Cacheable(value = {"brand"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if(params.containsKey("key")){
            queryWrapper.like("name",params.get("key"));
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean save(BrandEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean updateById(BrandEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean saveBatch(Collection<BrandEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<BrandEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean remove(Wrapper<BrandEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean update(Wrapper<BrandEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean update(BrandEntity entity, Wrapper<BrandEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"brand"},allEntries = true)
    public boolean updateBatchById(Collection<BrandEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}