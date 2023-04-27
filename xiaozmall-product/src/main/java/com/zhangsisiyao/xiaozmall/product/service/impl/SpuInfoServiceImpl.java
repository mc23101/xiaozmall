package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.*;
import com.zhangsisiyao.common.vo.product.ProductVo.*;
import com.zhangsisiyao.common.vo.product.AttrGroupVo.*;
import com.zhangsisiyao.xiaozmall.product.dao.SpuInfoDao;
import com.zhangsisiyao.xiaozmall.product.entity.*;
import com.zhangsisiyao.xiaozmall.product.service.*;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.SpuInfoQueryVo;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;


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
    @Cacheable(value = {"SpuInfo"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(SpuInfoQueryVo params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        SpuInfoVo spuInfoVo=params.getSpuInfoVo();

        if(spuInfoVo.getId()!=null){
            wrapper.eq("id",spuInfoVo.getId());
        }

        if(spuInfoVo.getSpuName()!=null){
            wrapper.eq("spu_name",spuInfoVo.getSpuName());
        }
        if(spuInfoVo.getCatalogId()!=null){
            wrapper.eq("catalog_id",spuInfoVo.getCatalogId());
        }
        if(spuInfoVo.getBrandId()!=null){
            wrapper.eq("brand_id",spuInfoVo.getBrandId());
        }
        if(spuInfoVo.getWeight()!=null){
            wrapper.eq("weight",spuInfoVo.getWeight());
        }
        if(spuInfoVo.getPublishStatus()!=null){
            wrapper.eq("publish_status",spuInfoVo.getPublishStatus());
        }


        if(StringUtils.isNotEmpty(params.getPageParams().getKey())){
            wrapper.like("spu_name",params.getPageParams().getKey());
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params.getPageParams().getPageIndex(),params.getPageParams().getPageSize()),
                wrapper

        );
        return new PageUtils(page);
    }

    @Override
    public boolean saveProduct(ProductVo product) {
        long catalogId=product.getCatalogId();
        int size1=0;
        List<AttrGroupEntity> groups = this.attrGroupService.query().eq("catalog_id", catalogId).list();
        for(AttrGroupEntity group:groups){
            size1+=attrService.queryWithAttrGroup(String.valueOf(group.getAttrGroupId())).size();
        }
        int size2=0;
        for(AttrGroupWithAttrValueVo group:product.getSpuAttrGroup()){
           size2+=group.getAttrs().size();
        }
        if(size1!=size2){
            return false;
        }

        //TODO 优化储存
        //存储SpuInfo
        SpuInfoEntity spuInfo=new SpuInfoEntity();
        BeanUtils.copyProperties(product,spuInfo);
        spuInfo.setCreateTime(new Date());
        spuInfo.setUpdateTime(new Date());
        this.baseMapper.insert(spuInfo);

        //存储SpuInfoDescEntity
        product.getDescript().forEach((descript)->{
            SpuInfoDescEntity desc=new SpuInfoDescEntity();
            BeanUtils.copyProperties(descript,desc);
            desc.setSpuId(spuInfo.getId());
            spuInfoDescService.getBaseMapper().insert(desc);
        });


        //储存SpuImageEntity
        product.getImages().forEach((image)->{
            SpuImagesEntity spuImage=new SpuImagesEntity();
            BeanUtils.copyProperties(image,spuImage);
            spuImage.setSpuId(spuInfo.getId());
            spuImagesService.getBaseMapper().insert(spuImage);
        });


        //存储SpuAttrValue
        List<AttrGroupWithAttrValueVo> baseAttrs = product.getSpuAttrGroup();
        baseAttrs.forEach((attrGroup)->{
            attrGroup.getAttrValues().forEach((attr)->{
                ProductAttrValueEntity productAttr = new ProductAttrValueEntity();
                productAttr.setAttrId(attr.getAttrId());
                productAttr.setAttrValue(attr.getAttrValue());
                productAttr.setAttrName(attr.getAttrName());
                productAttr.setSpuId(spuInfo.getId());
                productAttr.setGroupId(attrGroup.getAttrGroupId());
                productAttrValueService.getBaseMapper().insert(productAttr);
            });
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

            skuInfoService.getBaseMapper().insert(skuInfo);

            //储存sku的图片
            List<ImageVo> skuImages = skuVo.getImages();
            skuImages.forEach((skuImage)->{
                SkuImagesEntity skuImagesEntity=new SkuImagesEntity();
                BeanUtils.copyProperties(skuImage,skuImagesEntity);
                skuImagesEntity.setSkuId(skuInfo.getSkuId());
                this.skuImagesService.getBaseMapper().insert(skuImagesEntity);
            });

            //储存SkuAttrValue
            List<AttrVo.AttrValueVo> attrs = skuVo.getAttr();
            attrs.forEach((attr)->{
                SkuSaleAttrValueEntity attrValue = new SkuSaleAttrValueEntity();
                attrValue.setAttrId(attr.getAttrId());
                attrValue.setAttrValue(attr.getAttrValue());
                attrValue.setSkuId(skuInfo.getSkuId());
                attrValue.setAttrName(attr.getAttrName());
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
        List<ImageVo> descList=new ArrayList<>();
        spuInfoDescService.query().eq("spu_id", spuId).list().forEach((entity)->{
            ImageVo image=new ImageVo();
            BeanUtils.copyProperties(entity,image);
            descList.add(image);
        });
        productVo.setDescript(descList);

        //获取SpuImage
        List<ImageVo> imageList=new ArrayList<>();
        spuImagesService.query().eq("spu_id",spuId).list().forEach((entity)->{
            ImageVo image=new ImageVo();
            BeanUtils.copyProperties(entity,image);
            imageList.add(image);
        });
        productVo.setImages(imageList);

        //TODO 获取积分信息

        //获取spu属性
        List<AttrGroupWithAttrValueVo> attrValueVos =new ArrayList<>();
        List<AttrGroupEntity> groups = attrGroupService.query().eq("catalog_id", productVo.getCatalogId()).list();
        groups.forEach((group)->{
            AttrGroupWithAttrValueVo cur=new AttrGroupWithAttrValueVo();
            BeanUtils.copyProperties(group,cur);
            List<ProductAttrValueEntity> attrs = productAttrValueService.query().eq("group_id", group.getAttrGroupId()).list();
            List<AttrVo.AttrValueVo> valueVos=new ArrayList<>();
            attrs.forEach((attr)->{
                AttrVo.AttrValueVo curAttr=new AttrVo.AttrValueVo();
                BeanUtils.copyProperties(attr,curAttr);
                valueVos.add(curAttr);
            });
            cur.setAttrValues(valueVos);
            attrValueVos.add(cur);
        });
        productVo.setSpuAttrGroup(attrValueVos);

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
            List<AttrVo.AttrValueVo> skuAttrValueVos =new ArrayList<>();
            skuSaleAttrValueService.query().eq("sku_id",entity.getSkuId()).list().forEach((skuAttrValue)->{
                AttrVo.AttrValueVo skuAttrValueVo =new AttrVo.AttrValueVo();
                BeanUtils.copyProperties(skuAttrValue, skuAttrValueVo);
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
        RLock lock = redisson.getLock("SpuLock."+spuId);
        lock.lock();
        SpuInfoEntity entity = this.query().eq("id", spuId).one();
        entity.setPublishStatus(1);
        entity.setUpdateTime(new Date());
        ProductVo product = spuInfoService.getProduct(spuId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend("ElasticSearch","product.spu.up",mapper.writeValueAsString(product));
            updateById(entity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void downSpu(Long spuId) {
        SpuInfoEntity entity = this.query().eq("id", spuId).one();
        entity.setPublishStatus(0);
        entity.setUpdateTime(new Date());
        rabbitTemplate.convertAndSend("ElasticSearch","product.spu.down",spuId);
        updateById(entity);
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
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean save(SpuInfoEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean saveBatch(Collection<SpuInfoEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SpuInfoEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean remove(Wrapper<SpuInfoEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean updateById(SpuInfoEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean update(Wrapper<SpuInfoEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean update(SpuInfoEntity entity, Wrapper<SpuInfoEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuInfo"},allEntries = true)
    public boolean updateBatchById(Collection<SpuInfoEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}