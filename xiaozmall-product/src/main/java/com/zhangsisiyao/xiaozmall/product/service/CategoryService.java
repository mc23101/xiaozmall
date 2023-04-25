package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.zhangsisiyao.common.vo.product.PageParamVo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(PageParamVo params);

    List<CatalogVo> listWithTree();

    Map<Long,CatalogVo> listWithMap();
}

