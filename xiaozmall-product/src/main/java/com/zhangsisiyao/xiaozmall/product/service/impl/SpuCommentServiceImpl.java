package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.dao.SpuCommentDao;
import com.zhangsisiyao.xiaozmall.product.entity.SpuCommentEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuCommentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("spuCommentService")
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentDao, SpuCommentEntity> implements SpuCommentService {

    @Override
    @Cacheable(value = {"SpuComment"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(PageParamVo params) {
        IPage<SpuCommentEntity> page = this.page(
                new Query<SpuCommentEntity>().getPage(params.getPageIndex(),params.getPageSize()),
                new QueryWrapper<SpuCommentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean save(SpuCommentEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean saveBatch(Collection<SpuCommentEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SpuCommentEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean remove(Wrapper<SpuCommentEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean updateById(SpuCommentEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean update(Wrapper<SpuCommentEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean update(SpuCommentEntity entity, Wrapper<SpuCommentEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuComment"},allEntries = true)
    public boolean updateBatchById(Collection<SpuCommentEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}