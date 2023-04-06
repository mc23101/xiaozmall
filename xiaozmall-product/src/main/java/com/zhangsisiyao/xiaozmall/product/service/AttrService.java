package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.common.vo.*;
import com.zhangsisiyao.xiaozmall.product.vo.AttrValueVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public PageUtils queryBaseAttr(Long catId,Map<String, Object> params);
    public PageUtils querySaleAttr(Long catId,Map<String, Object> params);

    List<AttrEntity> queryWithAttrGroup(String groupId);

    List<ProductAttrValueEntity> queryListForSpu(Long spuId);

    @Override
    AttrEntity getById(Serializable attr);

    void UpdateAttrsBySpuId(List<AttrValueVo> attrs, String spuId);

    void UpdateByAttrId(AttrEntity attr);

    void DeleteAttrsById(Long[] ids);

    void SaveAttr(AttrEntity attr);
}
