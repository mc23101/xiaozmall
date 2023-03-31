package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.product.entity.SkuInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageLimit(Map<String, Object> params);

    List<SkuInfoEntity> listWithCatalogBrandSpu(String catalog, String brand, String spu);
}

