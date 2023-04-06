package com.zhangsisiyao.xiaozmall.search.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchResult implements Serializable {
    long count;

    List<ProductVo> products;

    List<CatalogVo> catalogs;

    List<BrandVo> brands;

    List<AttrVo> spuAttrs;

    List<AttrVo> skuAttrs;
}
