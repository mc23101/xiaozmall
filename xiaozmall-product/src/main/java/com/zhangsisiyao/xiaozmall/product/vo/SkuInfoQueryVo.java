package com.zhangsisiyao.xiaozmall.product.vo;

import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "sku信息搜索参数")
public class SkuInfoQueryVo extends PageParamVo {

    @ApiModelProperty(value = "品牌id")
    private Long brandId=null;

    @ApiModelProperty(value = "分类id")
    private Long catalogId=null;

    @ApiModelProperty(value = "搜索关键字")
    private String key=null;

    @ApiModelProperty(value = "最低价格")
    private BigDecimal min=null;

    @ApiModelProperty(value = "最高价格")
    private BigDecimal max=null;

}
