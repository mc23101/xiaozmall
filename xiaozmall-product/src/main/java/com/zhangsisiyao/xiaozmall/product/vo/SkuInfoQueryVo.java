package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuInfoQueryVo {
    private Long brandId;
    private Long catalogId;
    private String key;
    private BigDecimal min;
    private BigDecimal max;
    long pageIndex;
    long pageSize;
}
