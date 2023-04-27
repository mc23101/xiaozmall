package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "分页的请求参数")
public class PageParamVo implements Serializable {
    @ApiModelProperty(name = "页码",value = "页码从1开始")
    Long pageIndex=1L;
    @ApiModelProperty(name = "每页大小",value = "分页查询每页大小",position = 1)
    Long pageSize=Long.parseLong(String.valueOf(Integer.MAX_VALUE));
    @ApiModelProperty(name = "查询关键字",value = "模糊查询的关键字",position = 2)
    String key;
}
