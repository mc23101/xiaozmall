package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "商品完整信息")
public class ProductVo extends SpuInfoVo implements Serializable {

    @ApiModelProperty(value = "商品spu描述图片",position = 8)
    List<ImageVo> descript;

    @ApiModelProperty(value = "商品spu图集",position = 9)
    List<ImageVo> images;

    @ApiModelProperty(value = "商品积分信息",position = 10)
    BoundsVo bounds;

    @ApiModelProperty(value = "商品spu属性值",position = 11)
    List<AttrGroupVo.AttrGroupWithAttrValueVo> spuAttrGroup;

    @ApiModelProperty(value = "商品sku信息",position = 12)
    List<SkuVo> skus;


    @EqualsAndHashCode(callSuper = true)
    @Data
    @ApiModel(description = "商品完整sku信息")
    public static class SkuVo extends SkuInfoVo implements Serializable {

        @ApiModelProperty(value = "sku图集")
        List<ImageVo> images;

        @ApiModelProperty(value = "商品sku属性值")
        List<AttrVo.AttrValueVo> attr;

        //TODO 会员信息折扣

        BigDecimal fullCount;

        BigDecimal discount;

        Integer countStatus;

        BigDecimal fullPrice;

        BigDecimal reducePrice;

        Integer priceStatus;

        List<Integer> memberPrice;

    }

    @Data
    public static class BoundsVo implements Serializable {

        @NotNull
        BigDecimal buyBounds;

        @NotNull
        BigDecimal growBounds;
    }
}
