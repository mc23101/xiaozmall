package com.zhangsisiyao.common.vo.product;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel(description = "sku信息")
public class SkuInfoVo {

    @ApiModelProperty("商品skuId")
    private Long skuId;

    @ApiModelProperty(value = "商品spuId")
    private Long spuId;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "sku介绍描述")
    private String skuDesc;

    @ApiModelProperty(value = "所属分类id")
    private Long catalogId;

    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    @ApiModelProperty(value = "sku默认图片")
    private String skuDefaultImg;

    @ApiModelProperty(value = "sku标题")
    private String skuTitle;

    @ApiModelProperty(value = "sku副标题")
    private String skuSubtitle;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "销量")
    private Long saleCount;
}
