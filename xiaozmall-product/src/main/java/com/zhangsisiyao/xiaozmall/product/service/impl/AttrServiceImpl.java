package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.zhangsisiyao.xiaozmall.product.dao.AttrDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.service.ProductAttrValueService;
import com.zhangsisiyao.xiaozmall.product.vo.BaseAttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    ProductAttrValueService productAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByColumn(Object column, Object val, Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>().eq(String.valueOf(column),val)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByColumns(Map<Object, Object> column, Map<String, Object> params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        for(Object key:column.keySet()){
            queryWrapper=queryWrapper.eq(String.valueOf(key),column.get(key));
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
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
    public List<AttrEntity> queryWithAttrGroup(String groupId) {
        return this.baseMapper.queryWithAttrGroup(groupId);
    }

    @Override
    public List<ProductAttrValueEntity> queryListForSpu(Long spuId) {
        return productAttrValueService.query().eq("spu_id", spuId).list();
    }

    @Override
    public void UpdateAttrsBySpuId(List<BaseAttrVo> attrs, String spuid) {
        System.out.println(attrs);
        attrs.forEach((attrVo)->{
            ProductAttrValueEntity one = productAttrValueService.query().eq("spu_id", spuid).eq("attr_id", attrVo.getAttrId()).one();
            one.setAttrValue(attrVo.getAttrValues());
            productAttrValueService.updateById(one);
        });
    }


}