package com.zhangsisiyao.xiaozmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.coupon.entity.SmsCategoryBoundsEntity;

import java.util.Map;

/**
 * 商品分类积分设置
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 21:44:26
 */
public interface SmsCategoryBoundsService extends IService<SmsCategoryBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

