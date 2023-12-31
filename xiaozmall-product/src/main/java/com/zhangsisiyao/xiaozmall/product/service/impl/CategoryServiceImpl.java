package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.xiaozmall.product.dao.CategoryDao;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.zhangsisiyao.xiaozmall.product.service.CategoryService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.CatalogQueryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    @Cacheable(cacheNames = {"Category"},sync = true,keyGenerator = "customKeyGenerator")
    public PageUtils queryPage(CatalogQueryVo params) {
        CatalogVo catalogVo=params.getCatalogVo();
        QueryWrapper<CategoryEntity> wrapper = new QueryWrapper<>();
        if(catalogVo.getCatId()!=null){
            wrapper.eq("cat_id",catalogVo.getCatId());
        }
        if(catalogVo.getName()!=null){
            wrapper.eq("name",catalogVo.getName());
        }
        if(catalogVo.getParentCid()!=null){
            wrapper.eq("parent_cid",catalogVo.getParentCid());
        }
        if(catalogVo.getCatLevel()!=0){
            wrapper.eq("cat_level",catalogVo.getCatLevel());
        }
        if(catalogVo.getShowStatus()!=null){
            wrapper.eq("show_status",catalogVo.getShowStatus());
        }
        if(catalogVo.getProductUnit()!=null){
            wrapper.eq("product_unit",catalogVo.getProductUnit());
        }

        if(catalogVo.getProductCount()!=null){
            wrapper.eq("product_count",catalogVo.getProductCount());
        }

        if (StringUtils.isNotEmpty(params.getPageParamVo().getKey())){
            wrapper.like("name",params.getPageParamVo().getKey());
        }

        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params.getPageParamVo().getPageIndex(),params.getPageParamVo().getPageSize()),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Cacheable(cacheNames = {"Category"},sync = true,keyGenerator = "customKeyGenerator")
    public List<CatalogVo> listWithTree() {
        List<CatalogVo> all = new ArrayList<>();
        baseMapper.selectList(null).forEach((entity)->{
            CatalogVo cur=new CatalogVo();
            BeanUtils.copyProperties(entity,cur);
            cur.setChildren(null);
            all.add(cur);
        });
        return all.stream()
                .filter((menu) -> menu.getCatLevel() == 1)
                .peek((menu) -> {
                    menu.getPath().add(menu.getCatId());
                    menu.setChildren(getChildren(menu, all));
                })
                .sorted(Comparator.comparingInt(CatalogVo::getSort))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = {"Category"},keyGenerator = "customKeyGenerator",sync = true)
    public Map<Long, CatalogVo> listWithMap() {
        Map<Long,CatalogVo> map=new HashMap<>();
        List<CatalogVo> tree=listWithTree();
        Queue<CatalogVo> queue = new LinkedList<>(tree);
        while (!queue.isEmpty()) {
            CatalogVo poll = queue.poll();
            if(poll.getChildren()!=null){
                queue.addAll(poll.getChildren());
            }
            map.put(poll.getCatId(),poll);
        }
        for(Long key:map.keySet()){
            map.get(key).setChildren(null);
        }
        return map;
    }

    public List<CatalogVo> getChildren(CatalogVo root, List<CatalogVo> all) {
        List<CatalogVo> collect = all.stream()
                .filter((menu) -> Objects.equals(menu.getParentCid(), root.getCatId()))
                .peek((menu) -> {
                    menu.setPath(new ArrayList<>(root.getPath()));
                    menu.getPath().add(menu.getCatId());
                    menu.setChildren(getChildren(menu, all));
                })
                .sorted(Comparator.comparingInt(CatalogVo::getSort))
                .collect(Collectors.toList());
        return collect.size()==0?null:collect;
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean save(CategoryEntity entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean saveBatch(Collection<CategoryEntity> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean saveOrUpdateBatch(Collection<CategoryEntity> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean remove(Wrapper<CategoryEntity> queryWrapper) {
        return super.remove(queryWrapper);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean updateById(CategoryEntity entity) {
        return super.updateById(entity);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean update(Wrapper<CategoryEntity> updateWrapper) {
        return super.update(updateWrapper);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean update(CategoryEntity entity, Wrapper<CategoryEntity> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = {"Category"},allEntries = true)
    public boolean updateBatchById(Collection<CategoryEntity> entityList) {
        return super.updateBatchById(entityList);
    }
}