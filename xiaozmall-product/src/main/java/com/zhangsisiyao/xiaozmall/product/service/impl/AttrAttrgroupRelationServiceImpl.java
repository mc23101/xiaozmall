package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.AttrAttrgroupRelationDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    @Cacheable(value = {"attrRelation"},keyGenerator = "customKeyGenerator")
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean save(AttrAttrgroupRelationEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean saveBatch(Collection<AttrAttrgroupRelationEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<AttrAttrgroupRelationEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean remove(Wrapper<AttrAttrgroupRelationEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean updateById(AttrAttrgroupRelationEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean update(Wrapper<AttrAttrgroupRelationEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean update(AttrAttrgroupRelationEntity entity, Wrapper<AttrAttrgroupRelationEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"attrRelation"},allEntries = true)
    public boolean updateBatchById(Collection<AttrAttrgroupRelationEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}