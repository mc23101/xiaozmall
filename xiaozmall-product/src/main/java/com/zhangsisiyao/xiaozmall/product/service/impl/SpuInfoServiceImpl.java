package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.zhangsisiyao.xiaozmall.product.dao.SpuInfoDao;
import com.zhangsisiyao.xiaozmall.product.entity.*;
import com.zhangsisiyao.xiaozmall.product.service.*;
import com.zhangsisiyao.xiaozmall.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrGroupService attrGroupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageLimit(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();
        if(params.containsKey("brandId")&&!params.get("brandId").equals("0")){
            queryWrapper=queryWrapper.eq("brand_id",params.get("brandId"));
        }
        if(params.containsKey("catalogId")&&!params.get("catalogId").equals("0")){
            queryWrapper=queryWrapper.eq("catalog_id",params.get("catalogId"));
        }
        if(params.containsKey("key")&&!params.get("key").equals("")){
            queryWrapper=queryWrapper.like("spu_name", params.get("key"));
        }
        if(params.containsKey("status")&&!params.get("status").equals("")){
            queryWrapper=queryWrapper.eq("publish_status", params.get("status"));
        }
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public boolean saveProduct(ProductVo product) {
        long catalogId=product.getCatalogId();
        int size=0;
        List<AttrGroupEntity> groups = this.attrGroupService.query().eq("catalog_id", catalogId).list();
        for(AttrGroupEntity group:groups){
            size+=attrService.queryWithAttrGroup(String.valueOf(group.getAttrGroupId())).size();
        }
        System.out.println(product.getBaseAttrs().size());
        System.out.println(size);
        if(product.getBaseAttrs().size()<size){
            return false;
        }
        //TODO 优化储存
        //存储SpuInfo
        SpuInfoEntity spuInfo=new SpuInfoEntity();
        spuInfo.setCreateTime(new Date());
        spuInfo.setUpdateTime(new Date());
        BeanUtils.copyProperties(product,spuInfo);
        this.baseMapper.insert(spuInfo);

        //存储SpuInfoDescEntity
        List<String> decript=product.getDecript();
        SpuInfoDescEntity desc = new SpuInfoDescEntity();
        desc.setSpuId(spuInfo.getId());
        desc.setDecript(String.join(",",decript));
        spuInfoDescService.saveOrUpdate(desc);

        //储存SpuImageEntity
        List<String> images=product.getImages();
        SpuImagesEntity spuImage=new SpuImagesEntity();
        spuImage.setSpuId(spuInfo.getId());
        spuImage.setImgUrl(String.join(",",images));
        spuImagesService.getBaseMapper().insert(spuImage);
        
        //存储SpuAttr
        List<BaseAttrVo> baseAttrs = product.getBaseAttrs();
        baseAttrs.forEach((attr)->{
            ProductAttrValueEntity productAttr = new ProductAttrValueEntity();
            productAttr.setAttrId(attr.getAttrId());
            productAttr.setAttrValue(attr.getAttrValues());
            productAttr.setQuickShow(attr.getShowDesc());
            productAttr.setSpuId(spuInfo.getId());
            productAttrValueService.getBaseMapper().insert(productAttr);
        });


        //TODO 储存积分信息

        //储存sku信息
        List<SkuVo> skus = product.getSkus();
        skus.forEach((skuVo)->{
            SkuInfoEntity skuInfo=new SkuInfoEntity();
            BeanUtils.copyProperties(skuVo,skuInfo);
            skuInfo.setSpuId(spuInfo.getId());
            skuInfo.setCatalogId(spuInfo.getCatalogId());
            skuInfo.setBrandId(spuInfo.getBrandId());
            skuInfo.setSkuDesc(String.join(",",skuVo.getDescar()));

            //储存sku的图片
            List<ImageVo> skuImages = skuVo.getImages();
            List<String> urls=new ArrayList<>();
            SkuImagesEntity skuImagesEntity=new SkuImagesEntity();
            skuImages.forEach((skuImage)->{
                if(skuImage.getDefaultImg()==1){
                    skuInfo.setSkuDefaultImg(skuImage.getImgUrl());
                    skuImagesEntity.setDefaultImg(skuImage.getImgUrl());
                }
                urls.add(skuImage.getImgUrl());
            });
            skuInfoService.getBaseMapper().insert(skuInfo);
            skuImagesEntity.setImgUrl(String.join(",",urls));
            skuImagesEntity.setSkuId(skuInfo.getSkuId());
            skuImagesService.getBaseMapper().insert(skuImagesEntity);

            //储存SkuAttrValue
            List<SkuAttrVo> attrs = skuVo.getAttr();
            attrs.forEach((attr)->{
                SkuSaleAttrValueEntity attrValue = new SkuSaleAttrValueEntity();
                attrValue.setAttrId(attr.getAttrId());
                attrValue.setAttrName(attr.getAttrName());
                attrValue.setAttrValue(attr.getAttrValue());
                attrValue.setSkuId(skuInfo.getSkuId());
                skuSaleAttrValueService.getBaseMapper().insert(attrValue);
            });

        });
        return true;
    }

    @Override
    public void updateSpuPublishStatus(Long spuid) {
        SpuInfoEntity entity = this.query().eq("id", spuid).one();
        entity.setPublishStatus(1-entity.getPublishStatus());
        entity.setUpdateTime(new Date());
        this.updateById(entity);
    }

}