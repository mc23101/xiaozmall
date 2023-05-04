package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.BrandVo;
import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.xiaozmall.product.dao.CategoryBrandRelationDao;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryBrandRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import com.zhangsisiyao.xiaozmall.product.service.CategoryBrandRelationService;
import com.zhangsisiyao.xiaozmall.product.service.CategoryService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.CatalogBrandRelationQueryVo;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    CategoryService categoryService;

    @Override
    @Cacheable(value = {"CategoryBrandRelation"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(CatalogBrandRelationQueryVo params) {
        CatalogVo.CatalogBrandRelationVo relationVo=params.getCatalogBrandRelationVo();
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
        if(relationVo.getId()!=null){
            wrapper.eq("id",relationVo.getId());
        }
        if(relationVo.getBrandId()!=null){
            wrapper.eq("brand_id",relationVo.getBrandId());
        }
        if(relationVo.getCatalogId()!=null){
            wrapper.eq("catalog_id",relationVo.getCatalogId());
        }
        if(relationVo.getBrandName()!=null){
            wrapper.eq("brand_name",relationVo.getBrandName());
        }
        if(relationVo.getCatalogName()!=null){
            wrapper.eq("catalog_name",relationVo.getCatalogName());
        }
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params.getPageParamVo().getPageIndex(),params.getPageParamVo().getPageSize())

        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"CategoryBrandRelation"},keyGenerator = "customKeyGenerator",sync = true)
    public List<BrandVo> queryBrand(String catId) {
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
        List<BrandVo> result=new ArrayList<>();
        list.forEach((entity)->{
            BrandVo cur=new BrandVo();
            BeanUtils.copyProperties(entity,cur);
            result.add(cur);
        });
        return result;
    }

    @Override
    public R<Object> save(CatalogVo.CatalogBrandRelationVo catalogBrandRelationVo) {
        if(this.query().eq("brand_id",catalogBrandRelationVo.getBrandId()).eq("catalog_id",catalogBrandRelationVo.getCatalogId()).list().size()>0){
            return new R<>().error("添加失败,关联已经存在");
        }
        BrandEntity brand = brandService.query().eq("brand_id", catalogBrandRelationVo.getBrandId()).one();
        CategoryEntity category=categoryService.query().eq("cat_id", catalogBrandRelationVo.getCatalogId()).one();
        catalogBrandRelationVo.setBrandName(brand.getName());
        catalogBrandRelationVo.setCatalogName(category.getName());
        CategoryBrandRelationEntity entity=new CategoryBrandRelationEntity();
        BeanUtils.copyProperties(catalogBrandRelationVo,entity);
        this.save(entity);
        return new R<>().ok();
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