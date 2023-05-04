package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.product.AttrGroupVo.*;
import com.zhangsisiyao.common.vo.product.AttrGroupVo;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrGroupQueryVo;

import java.util.List;

/**
 * 
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-25 15:45:31
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(AttrGroupQueryVo params);


    public List<AttrGroupVo> queryWithAttrGroup(String catalogId);

    public List<AttrVo> queryAttrRelation(String attrGroupId);

    public void addAttrRelation(List<AttrAttrgroupRelationVo> relationEntities);

    public void deleteAttrRelation(Long[] relationIds);
    public PageUtils queryNoAttrRelation(String attrGroupId, PageParamVo params);




}

