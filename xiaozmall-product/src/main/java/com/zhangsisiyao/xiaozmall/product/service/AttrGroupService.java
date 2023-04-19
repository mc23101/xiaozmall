package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.AttrGroupVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-25 15:45:31
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByColumn(Object column,Object val,Map<String, Object> params);

    //List<AttrGroupEntity> queryByColumn(Map<String,Object> columns);

    public List<AttrGroupVo> queryWithAttr(String catalogId);

    public List<AttrEntity> queryAttrRelation(String attrGroupId);

    public void addAttrRelation(List<AttrAttrgroupRelationEntity> relationEntities);

    public void deleteAttrRelation(List<AttrAttrgroupRelationEntity> relationEntities);
    public PageUtils queryNoAttrRelation(String attrGroupId,Map<String, Object> params);




}

