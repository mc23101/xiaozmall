package com.zhangsisiyao.xiaozmall.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "分页的请求参数")
public class PageParamVo implements Serializable {
    @ApiModelProperty(name = "页码",value = "页码从1开始",example = "1")
    long pageIndex;
    @ApiModelProperty(name = "每页大小",value = "分页查询每页大小",example = "20")
    long pageSize;
    @ApiModelProperty(name = "查询关键字",value = "属性模糊查询的关键字")
    String key;
}
