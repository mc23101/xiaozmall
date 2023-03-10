package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.zhangsisiyao.xiaozmall.product.dao.SkuInfoDao;
import com.zhangsisiyao.xiaozmall.product.entity.SkuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
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
    public List<SkuInfoEntity> listWithCatalogBrandSpu(String catalog, String brand, String spu) {
        return this.query().eq("catalog_id",catalog).eq("brand_id",brand).eq("spu_id",spu).list();
    }

}