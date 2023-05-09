package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.ImageVo;
import com.zhangsisiyao.xiaozmall.product.dao.SpuImagesDao;
import com.zhangsisiyao.xiaozmall.product.entity.SpuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuImagesService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.ImageQueryVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    @Cacheable(value = {"SpuImages"},keyGenerator = "customKeyGenerator",sync = true)
    public PageUtils queryPage(ImageQueryVo params) {
        QueryWrapper<SpuImagesEntity> wrapper = new QueryWrapper<>();
        ImageVo imageVo=params.getImageVo();
        if(imageVo.getId()!=null){
            wrapper.eq("id",imageVo.getId());
        }
        if(imageVo.getSpuId()!=null){
            wrapper.eq("spu_id",imageVo.getSpuId());
        }
        if(imageVo.getImgSort()!=null){
            wrapper.eq("img_sort",imageVo.getImgSort());
        }
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params.getPageParamVo().getPageIndex(),params.getPageParamVo().getPageSize()),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean save(SpuImagesEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean saveBatch(Collection<SpuImagesEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<SpuImagesEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean remove(Wrapper<SpuImagesEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean updateById(SpuImagesEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean update(Wrapper<SpuImagesEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean update(SpuImagesEntity entity, Wrapper<SpuImagesEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"SpuImages"},allEntries = true)
    public boolean updateBatchById(Collection<SpuImagesEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}