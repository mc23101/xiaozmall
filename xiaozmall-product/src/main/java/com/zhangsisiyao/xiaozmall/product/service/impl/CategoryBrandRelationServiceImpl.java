package com.zhangsisiyao.xiaozmall.product.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.*;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Autowired
    BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
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

}