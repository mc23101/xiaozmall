package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.common.vo.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrQueryVo;

import java.util.List;

/**
 * 商品属性
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(AttrQueryVo params);

    PageUtils queryBaseAttr(Long catId, PageParamVo params);
    PageUtils querySaleAttr(Long catId, PageParamVo params);

    List<AttrEntity> queryWithAttrGroup(String groupId);

}
