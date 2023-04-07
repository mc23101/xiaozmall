package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.CategoryDao;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.zhangsisiyao.xiaozmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    @Cacheable(cacheNames = {"Category"},sync = true,keyGenerator = "customKeyGenerator")
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(cacheNames = {"Category"},sync = true,keyGenerator = "customKeyGenerator")
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> allEntities = baseMapper.selectList(null);
        return allEntities.stream()
                .filter((menu) -> menu.getCatLevel() == 1)
                .peek((menu) -> {
                    menu.setPath(menu.getName());
                    menu.setChildren(getChildren(menu, allEntities));
                })
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = {"Category"},keyGenerator = "customKeyGenerator",sync = true)
    public Map<Long, CategoryEntity> listWithMap() {
        Map<Long,CategoryEntity> map=new HashMap<>();
        baseMapper.selectList(null).forEach((entity->{
            map.put(entity.getCatId(),entity);
        }));
        return map;
    }

    public List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter((menu) -> Objects.equals(menu.getParentCid(), root.getCatId()))
                .peek((menu) -> {
                    menu.setPath(root.getPath()+"/"+menu.getName());
                    menu.setChildren(getChildren(menu, all));
                })
                .sorted(Comparator.comparingInt(CategoryEntity::getSort))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean save(CategoryEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean saveBatch(Collection<CategoryEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<CategoryEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean remove(Wrapper<CategoryEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean updateById(CategoryEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean update(Wrapper<CategoryEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean update(CategoryEntity entity, Wrapper<CategoryEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean updateBatchById(Collection<CategoryEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}