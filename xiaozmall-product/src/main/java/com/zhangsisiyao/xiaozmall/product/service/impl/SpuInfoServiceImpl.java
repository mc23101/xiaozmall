package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.dao.SpuInfoDao;
import com.zhangsisiyao.xiaozmall.product.entity.*;
import com.zhangsisiyao.xiaozmall.product.service.*;
import com.zhangsisiyao.xiaozmall.product.vo.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    RedissonClient redisson;

    @Autowired
    SpuInfoService spuInfoService;

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

    @Autowired
    RabbitTemplate rabbitTemplate;

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
        
        //存储SpuAttrValue
        List<BaseAttrValueVo> baseAttrs = product.getBaseAttrs();
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
            List<SkuAttrValueVo> attrs = skuVo.getAttr();
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
    public ProductVo getProduct(Long spuId) {
        ProductVo productVo=new ProductVo();

        productVo.setId(spuId);

        //获取spu信息
        SpuInfoEntity spuInfoEntity=spuInfoService.getById(spuId);
        BeanUtils.copyProperties(spuInfoEntity,productVo);

        //获取SpuInfoDescEntity
        List<String> descList=new ArrayList<>();
        spuInfoDescService.query().eq("spu_id", spuId).list().forEach((entity)->descList.add(entity.getDecript()));
        productVo.setDecript(descList);

        //获取SpuImage
        List<String> imageList=new ArrayList<>();
        spuImagesService.query().eq("spu_id",spuId).list().forEach((entity)->imageList.add(entity.getImgUrl()));
        productVo.setImages(imageList);

        //TODO 获取积分信息

        //获取spu属性
        List<BaseAttrValueVo> baseAttrValueVos=new ArrayList<>();
        productAttrValueService.queryBySpuId(String.valueOf(spuId)).forEach(
                (entity)->{
                    BaseAttrValueVo baseAttr=new BaseAttrValueVo();
                    BeanUtils.copyProperties(entity,baseAttr);
                    baseAttrValueVos.add(baseAttr);
                });
        productVo.setBaseAttrs(baseAttrValueVos);

        //获取所有sku
        List<SkuVo> skuVos=new ArrayList<>();
        skuInfoService.query().eq("spu_id", spuId).list().forEach((entity)->{
            SkuVo skuVo=new SkuVo();
            BeanUtils.copyProperties(entity,skuVo);


            //获取sku的images
            List<ImageVo> imageVos=new ArrayList<>();
            skuImagesService.query().eq("sku_id",entity.getSkuId()).list().forEach((image)->{
                ImageVo imageVo=new ImageVo();
                BeanUtils.copyProperties(image,imageVo);
                imageVos.add(imageVo);
            });
            skuVo.setImages(imageVos);

            //获取sku的属性值
            List<SkuAttrValueVo> skuAttrValueVos=new ArrayList<>();
            skuSaleAttrValueService.query().eq("sku_id",entity.getSkuId()).list().forEach((skuAttrValue)->{
                SkuAttrValueVo skuAttrValueVo=new SkuAttrValueVo();
                BeanUtils.copyProperties(skuAttrValue,skuAttrValueVo);
                skuAttrValueVos.add(skuAttrValueVo);
            });
            skuVo.setAttr(skuAttrValueVos);
            skuVos.add(skuVo);
        });
        productVo.setSkus(skuVos);
        return productVo;
    }

    @Override
    public void upSpu(Long spuId) {
        RLock lock = redisson.getLock("upSpuLock");
        lock.lock();
        SpuInfoEntity entity = this.query().eq("id", spuId).one();
        entity.setPublishStatus(1);
        entity.setUpdateTime(new Date());
        ProductVo product = spuInfoService.getProduct(spuId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend("ElasticSearch","product.spu.up",mapper.writeValueAsString(product));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.updateById(entity);
        lock.unlock();
    }

    @Override
    public void downSpu(Long spuId) {
        this.deleteSpu(new Long[]{spuId});
        rabbitTemplate.convertAndSend("ElasticSearch","product.spu.down",spuId);
    }

    @Override
    public void deleteSpu(Long[] ids) {
        for (Long spuId : ids) {
            //删除SpuInfoEntity
            this.removeById(spuId);

            //删除SpuInfoDescEntity
            this.spuInfoDescService.removeById(spuId);

            //删除SpuImageEntity
            List<SpuImagesEntity> spuImagesEntities = this.spuImagesService.query().eq("spu_id", spuId).list();
            spuImagesEntities.forEach((image) -> {
                this.spuImagesService.removeById(image.getId());
            });

            //TODO 删除积分信息

            //删除ProductAttrValueEntity
            List<ProductAttrValueEntity> productAttrValueEntities = this.productAttrValueService.query().eq("spu_id", spuId).list();
            productAttrValueEntities.forEach((attrValue) -> {
                productAttrValueService.removeById(attrValue.getId());
            });

            //删除所有sku
            List<SkuInfoEntity> skus = skuInfoService.query().eq("spu_id", spuId).list();
            skus.forEach((sku)->{
                Long skuId=sku.getSkuId();
                skuInfoService.removeById(skuId);

                //删除sku图片
                List<SkuImagesEntity> skuImage = skuImagesService.query().eq("sku_id", skuId).list();
                skuImage.forEach((image)->{
                    skuImagesService.removeById(image.getId());
                });
                //删除skuAttrValues
                List<SkuSaleAttrValueEntity> skuAttrValues = skuSaleAttrValueService.query().eq("sku_id", skuId).list();
                skuAttrValues.forEach((value)->{
                    skuSaleAttrValueService.removeById(value.getId());
                });
            });

        }
    }

    @Override
    public List<SpuInfoEntity> getWithCatalogAndBrand(String catalog, String brand) {
        return this.query().eq("catalog_id",catalog).eq("brand_id",brand).list();
    }

}