package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.BrandVo;
import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryBrandRelationEntity;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.CatalogBrandRelationQueryVo;

import java.util.List;

/**
 * 品牌分类关联
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-26 09:32:23
 */
public interface  CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(CatalogBrandRelationQueryVo params);

    List<BrandVo> queryBrand(String catId);

    R<Object> save(CatalogVo.CatalogBrandRelationVo catalogBrandRelationVo);

}

