package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.common.vo.product.PageParamVo;

import java.util.List;

/**
 * 商品属性
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(PageParamVo params);

    public PageUtils queryBaseAttr(Long catId, PageParamVo params);
    public PageUtils querySaleAttr(Long catId, PageParamVo params);

    List<AttrEntity> queryWithAttrGroup(String groupId);

    List<AttrVo.AttrValueVo> queryListForSpu(Long spuId);

    void UpdateAttrsBySpuId(List<AttrVo.AttrValueVo> attrs, String spuId);
}
