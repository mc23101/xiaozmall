package com.zhangsisiyao.xiaozmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 19:33:49
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    long getSkuCountById(String skuid);

    PageUtils queryPage(Map<String, Object> params);
}

