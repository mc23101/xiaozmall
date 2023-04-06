package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.BaseAttrValueVo;
import com.zhangsisiyao.xiaozmall.product.dao.AttrDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.service.ProductAttrValueService;
import com.zhangsisiyao.xiaozmall.product.vo.AttrValueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Override
    @Cacheable(value = {"attr"},key = "#root.methodName+#root.args")
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attr"},key = "#root.methodName+#root.args")
    public PageUtils queryBaseAttr(Long catId, Map<String, Object> params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>();
        if(catId!=0){
            queryWrapper=queryWrapper.eq("catalog_id",catId);
        }
        if(params.containsKey("key")){
            queryWrapper=queryWrapper.like("attr_name",params.get("key"));
        }
        queryWrapper=queryWrapper.eq("attr_type",1).or().eq("attr_type",2);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attr"},key = "#root.methodName+#root.args")
    public PageUtils querySaleAttr(Long catId, Map<String, Object> params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>();
        if(catId!=0){
            queryWrapper=queryWrapper.eq("catalog_id",catId);
        }
        if(params.containsKey("key")){
            queryWrapper=queryWrapper.like("attr_name",params.get("key"));
        }
        queryWrapper=queryWrapper.eq("attr_type",0).or().eq("attr_type",2);
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"attr"},key = "#root.methodName+#root.args")
    public List<AttrEntity> queryWithAttrGroup(String groupId) {
        return this.baseMapper.queryWithAttrGroup(groupId);
    }

    @Override
    @Cacheable(value = {"attr"},key = "#root.methodName+#root.args")
    public List<ProductAttrValueEntity> queryListForSpu(Long spuId) {
        return productAttrValueService.query().eq("spu_id", spuId).list();
    }

    @Override
    @Cacheable(value = {"attr"},key = "#root.methodName+#root.args")
    public AttrEntity getById(Serializable attr) {
        return super.getById(attr);
    }


    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public void UpdateAttrsBySpuId(List<AttrValueVo> attrs, String spuId) {
        System.out.println(attrs);
        attrs.forEach((attrVo)->{
            ProductAttrValueEntity one = productAttrValueService.query().eq("spu_id", spuId).eq("attr_id", attrVo.getAttrId()).one();
            if(one!=null){
                one.setAttrValue(attrVo.getAttrValue());
                productAttrValueService.updateById(one);
            }else{
                ProductAttrValueEntity newValue=new ProductAttrValueEntity();
                newValue.setAttrValue(attrVo.getAttrValue());
                newValue.setSpuId(Long.valueOf(spuId));
                newValue.setAttrId(attrVo.getAttrId());
                productAttrValueService.save(newValue);
            }
        });
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public void UpdateByAttrId(AttrEntity attr) {
        this.updateById(attr);
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public void DeleteAttrsById(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    @CacheEvict(value = {"attr"},allEntries = true)
    public void SaveAttr(AttrEntity attr) {
        this.save(attr);
    }


}