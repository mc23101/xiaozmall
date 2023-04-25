package com.zhangsisiyao.xiaozmall.search.vo;


import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.common.vo.product.BrandVo;
import com.zhangsisiyao.common.vo.product.CatalogVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchResult implements Serializable {
    long count;

    List<SearchProductVo> products;

    List<CatalogVo> catalogs;

    List<BrandVo> brands;

    List<AttrVo> spuAttrs;

    List<AttrVo> skuAttrs;
}
