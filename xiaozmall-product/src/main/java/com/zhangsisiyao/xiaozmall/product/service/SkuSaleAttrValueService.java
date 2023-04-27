package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.entity.SkuSaleAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrQueryVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrValueQueryVo;

/**
 * sku销售属性&值
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(AttrValueQueryVo params);
}

