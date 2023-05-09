package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.product.AttrGroupVo.*;
import com.zhangsisiyao.common.vo.product.AttrGroupVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.common.vo.PageParamVo;
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

    public PageUtils queryAttrRelation(String attrGroupId,PageParamVo params);

    public void addAttrRelation(List<AttrAttrgroupRelationVo> relationEntities);

    public void deleteAttrRelation(List<AttrAttrgroupRelationVo> relationVos);

    public PageUtils queryNoAttrRelation(String attrGroupId, PageParamVo params);

    public void deleteAttrGroup(Long[] ids);




}

