package com.zhangsisiyao.common.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuVo implements Serializable {

    private Long skuId;

    private Long spuId;

    private String skuName;

    private String skuDesc;

    private Long catalogId;

    private CatalogVo catalog;

    private Long brandId;

    private BoundsVo brand;

    private String skuDefaultImg;

    private String skuTitle;

    private String skuSubtitle;

    private BigDecimal price;

    private Long saleCount;

    List<ImageVo> images;

    List<AttrValueVo> attr;

    //TODO 会员信息折扣

    BigDecimal fullCount;

    BigDecimal discount;

    Integer countStatus;

    BigDecimal fullPrice;

    BigDecimal reducePrice;

    Integer priceStatus;

    List<Integer> memberPrice;

}
