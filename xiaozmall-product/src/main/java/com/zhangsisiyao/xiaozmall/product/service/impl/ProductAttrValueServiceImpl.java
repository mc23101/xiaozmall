package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.dao.ProductAttrValueDao;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.ProductAttrValueService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrValueQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    @Cacheable(value = {"ProductAttrValue"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(AttrValueQueryVo params) {
        QueryWrapper<ProductAttrValueEntity> wrapper = new QueryWrapper<>();
        AttrVo.AttrValueVo attrValueVo=params.getAttrValueVo();
        if(attrValueVo.getId()!=null){
            wrapper.eq("id",attrValueVo.getId());
        }
        if(attrValueVo.getSpuId()!=null){
            wrapper.eq("spu_id",attrValueVo.getSpuId());
        }
        if(attrValueVo.getAttrId()!=null){
            wrapper.eq("attr_id",attrValueVo.getAttrId());
        }

        if(attrValueVo.getAttrValue()!=null){
            wrapper.eq("attr_value",attrValueVo.getAttrValue());
        }

        if(attrValueVo.getAttrName()!=null){
            wrapper.eq("attr_name",attrValueVo.getAttrName());
        }

        if(attrValueVo.getGroupId()!=null){
            wrapper.eq("group_id",attrValueVo.getGroupId());
        }

        if(StringUtils.isNotEmpty(params.getPageParamVo().getKey())){
            wrapper.like("attr_name",params.getPageParamVo().getKey());
        }

        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params.getPageParamVo().getPageIndex(),params.getPageParamVo().getPageSize()),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(value = {"ProductAttrValue"},keyGenerator = "customKeyGenerator",sync = true)
    public List<AttrVo.AttrValueVo> queryBySpuId(String spuId) {
        List<AttrVo.AttrValueVo> list=new ArrayList<>();
        this.query().eq("spu_id",spuId).list().forEach((attrValue)->{
            AttrVo.AttrValueVo cur=new AttrVo.AttrValueVo();
            BeanUtils.copyProperties(attrValue,cur);
            list.add(cur);
        });
        return list;
    }


    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean save(ProductAttrValueEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean saveBatch(Collection<ProductAttrValueEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<ProductAttrValueEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean remove(Wrapper<ProductAttrValueEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean updateById(ProductAttrValueEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean update(Wrapper<ProductAttrValueEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean update(ProductAttrValueEntity entity, Wrapper<ProductAttrValueEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"ProductAttrValue"},allEntries = true)
    public boolean updateBatchById(Collection<ProductAttrValueEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}