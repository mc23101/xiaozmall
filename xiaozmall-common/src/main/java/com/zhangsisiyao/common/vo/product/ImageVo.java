package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@ApiModel(description = "图片信息")
public class ImageVo implements Serializable {

    @ApiModelProperty(value = "图片id，修改图片信息时需指定id")
    private Long id;

    @ApiModelProperty(value = "如果为spu图片，则不为0",position = 1)
    private Long skuId;

    @ApiModelProperty(value = "如果为sku图片，则不为0",position = 2)
    private Long spuId;

    @ApiModelProperty(value = "图片地址",position = 3)
    String imgUrl;

    @ApiModelProperty(value = "图片排序",position = 4)
    Integer imgSort;
}
