package com.zhangsisiyao.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商品spu或sku属性值信息")
public class AttrValueVo implements Serializable {

    @ApiModelProperty("属性值信息id")
    private Long id;

    @ApiModelProperty("属性Id")
    private Long attrId;

    @ApiModelProperty(value = "spuId,如果为spu属性值信息，则此项不为0")
    private Long spuId=0L;

    @ApiModelProperty(value = "skuId,如果为sku属性值信息，则此项不为0")
    private Long skuId;

    @ApiModelProperty(value = "属性组id，如果为spu属性值信息，则此项不为0")
    private Long groupId;

    @ApiModelProperty(value = "属性值")
    private String attrValue;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "是否快速展示")
    private Integer showDesc;
}