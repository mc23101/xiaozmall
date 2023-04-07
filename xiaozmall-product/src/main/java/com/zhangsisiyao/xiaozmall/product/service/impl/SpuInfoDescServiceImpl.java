package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.SpuInfoDescDao;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoDescEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuInfoDescService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService {

    @Override
    @Cacheable(value = {"SpuInfoDesc"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoDescEntity> page = this.page(
                new Query<SpuInfoDescEntity>().getPage(params),
                new QueryWrapper<SpuInfoDescEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean save(SpuInfoDescEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean saveBatch(Collection<SpuInfoDescEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SpuInfoDescEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean remove(Wrapper<SpuInfoDescEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean updateById(SpuInfoDescEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean update(Wrapper<SpuInfoDescEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean update(SpuInfoDescEntity entity, Wrapper<SpuInfoDescEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuInfoDesc"},allEntries = true)
    public boolean updateBatchById(Collection<SpuInfoDescEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}