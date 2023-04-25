package com.zhangsisiyao.xiaozmall.product.vo;

import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "spu信息搜索参数")
public class SpuInfoQueryVo extends PageParamVo implements Serializable {

    @ApiModelProperty(value = "品牌id")
    Long brandId=null;

    @ApiModelProperty(value = "分类id")
    Long catalogId=null;

    @ApiModelProperty(value = "搜索关键字")
    String key=null;

    @ApiModelProperty(value = "上架状态[0 - 下架，1 - 上架]")
    Integer status=null;
}
