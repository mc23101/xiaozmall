package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.ImageVo;
import com.zhangsisiyao.xiaozmall.product.dao.SkuImagesDao;
import com.zhangsisiyao.xiaozmall.product.entity.SkuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuImagesService;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.ImageQueryVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    @Cacheable(value = {"SkuImages"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(ImageQueryVo params) {
        QueryWrapper<SkuImagesEntity> wrapper = new QueryWrapper<>();
        ImageVo imageVo=params.getImageVo();
        if(imageVo.getId()!=null){
            wrapper.eq("id",imageVo.getId());
        }
        if(imageVo.getSkuId()!=null){
            wrapper.eq("sku_id",imageVo.getSkuId());
        }
        if(imageVo.getImgSort()!=null){
            wrapper.eq("img_sort",imageVo.getImgSort());
        }

        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params.getPageParamVo().getPageIndex(),params.getPageParamVo().getPageSize()),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean save(SkuImagesEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean saveBatch(Collection<SkuImagesEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SkuImagesEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean remove(Wrapper<SkuImagesEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean updateById(SkuImagesEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean update(Wrapper<SkuImagesEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean update(SkuImagesEntity entity, Wrapper<SkuImagesEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SkuImages"},allEntries = true)
    public boolean updateBatchById(Collection<SkuImagesEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}