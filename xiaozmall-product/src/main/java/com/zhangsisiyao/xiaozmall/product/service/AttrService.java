package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;

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

    public PageUtils queryPageByColumn(Object column, Object val, Map<String, Object> params);


    public PageUtils queryPageByColumns(Map<Object,Object> column,Map<String, Object> params);

    public PageUtils queryBaseAttr(Long catId,Map<String, Object> params);
    public PageUtils querySaleAttr(Long catId,Map<String, Object> params);

    List<AttrEntity> queryWithAttrGroup(String groupId);
}

