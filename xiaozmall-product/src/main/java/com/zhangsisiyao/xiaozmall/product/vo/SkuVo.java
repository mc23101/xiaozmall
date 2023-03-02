package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuVo implements Serializable {
    String skuName;

    BigDecimal price;

    String skuTitle;

    String skuSubtitle;

    List<ImageVo> images;

    List<String> descar;

    List<SkuAttrVo> attr;

    BigDecimal fullCount;

    BigDecimal discount;

    Integer countStatus;

    BigDecimal fullPrice;

    BigDecimal reducePrice;

    Integer priceStatus;

    List<Integer> memberPrice;

}
