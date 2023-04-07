package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.CategoryBrandRelationDao;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryBrandRelationEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import com.zhangsisiyao.xiaozmall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Autowired
    BrandService brandService;

    @Override
    @Cacheable(value = {"CategoryBrandRelation"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"CategoryBrandRelation"},keyGenerator = "customKeyGenerator",sync = true)
    public List<BrandEntity> queryBrand(String catId) {
        List<BrandEntity> list=new ArrayList<>();
        if(catId.equals("0")){
            list=brandService.query().list();
        }else{
            List<CategoryBrandRelationEntity> entities = this.query().eq("catalog_id", catId).list();
            Set<Long> set=new HashSet<>();
            entities.forEach((entity)->set.add(entity.getBrandId()));
            if(set.size()!=0){
                list=brandService.query().in("brand_id",set).list();
            }
        }
        return list;
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean save(CategoryBrandRelationEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean saveBatch(Collection<CategoryBrandRelationEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<CategoryBrandRelationEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean remove(Wrapper<CategoryBrandRelationEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean updateById(CategoryBrandRelationEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean update(Wrapper<CategoryBrandRelationEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean update(CategoryBrandRelationEntity entity, Wrapper<CategoryBrandRelationEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"CategoryBrandRelation"},allEntries = true)
    public boolean updateBatchById(Collection<CategoryBrandRelationEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}