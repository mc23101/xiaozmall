package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(description = "sku信息")
public class SkuInfoVo implements Serializable {

    @ApiModelProperty("商品skuId")
    private Long skuId;

    @ApiModelProperty(value = "商品spuId",position = 1)
    private Long spuId;

    @ApiModelProperty(value = "sku名称",position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku介绍描述",position = 3)
    private String skuDesc;

    @ApiModelProperty(value = "所属分类id",position = 4)
    private Long catalogId;

    @ApiModelProperty(value = "品牌id",position = 5)
    private Long brandId;

    @ApiModelProperty(value = "sku默认图片",position = 6)
    private String skuDefaultImg;

    @ApiModelProperty(value = "sku标题",position = 7)
    private String skuTitle;

    @ApiModelProperty(value = "sku副标题",position = 8)
    private String skuSubtitle;

    @ApiModelProperty(value = "价格",position = 9)
    private BigDecimal price;

    @ApiModelProperty(value = "销量",position = 10)
    private Long saleCount;
}
