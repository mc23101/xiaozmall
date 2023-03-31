package com.zhangsisiyao.xiaozmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;
import com.zhangsisiyao.xiaozmall.product.vo.AttrGroupWithAttrsVo;
import com.zhangsisiyao.xiaozmall.product.dao.AttrGroupDao;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Autowired
    AttrAttrgroupRelationService attrgroupRelationService;

    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByColumn(Object column, Object val, Map<String, Object> params) {
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        if(column!=null){
            queryWrapper=queryWrapper.eq(String.valueOf(column),val);
        }
        if(params.containsKey("key")){
            queryWrapper=queryWrapper.like("attr_group_name",params.get("key"));
        }
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryNoAttrRelation(String attrGroupId, Map<String, Object> params) {
        Long catalogId=this.query().eq("attr_group_id",attrGroupId).one().getCatalogId();
        List<AttrAttrgroupRelationEntity> exist = attrgroupRelationService.query().eq("attr_group_id", attrGroupId).list();
        Set<Long> set=new HashSet<>();
        for(AttrAttrgroupRelationEntity entity:exist){
            set.add(entity.getAttrId());
        }
        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>().eq("catalog_id", catalogId).eq("attr_type",1).or().eq("attr_type",2);
        if(set.size()!=0){
            attrEntityQueryWrapper=attrEntityQueryWrapper.notIn("attr_id", set);
        }

        IPage<AttrEntity> page = attrService.page(
                new Query<AttrEntity>().getPage(params),
                attrEntityQueryWrapper

        );
        return new PageUtils(page);
    }

    @Override
    public List<AttrGroupWithAttrsVo> queryWithAttr(String catalogId) {
        //TODO 修改时间复杂度，使用联表查询
        List<AttrGroupEntity> attrGroup = this.query().eq("catalog_id", catalogId).list();
        List<AttrGroupWithAttrsVo> attrGroupWithAttrsVos=new ArrayList<>();
        for(AttrGroupEntity attrGroupEntity:attrGroup){
            AttrGroupWithAttrsVo cur=new AttrGroupWithAttrsVo(attrGroupEntity);
            List<AttrEntity> attrEntities = attrService.queryWithAttrGroup(String.valueOf(attrGroupEntity.getAttrGroupId()));
            if(attrEntities!=null){
                cur.getAttrs().addAll(attrEntities);
            }
            attrGroupWithAttrsVos.add(cur);
        }
        return attrGroupWithAttrsVos;
    }



}