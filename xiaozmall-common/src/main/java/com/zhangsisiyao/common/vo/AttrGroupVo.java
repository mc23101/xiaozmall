package com.zhangsisiyao.common.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "属性分组信息")
public class AttrGroupVo {

    @ApiModelProperty(value = "分组id",example = "1")
    private Long attrGroupId;

    @ApiModelProperty(value = "分组名称",example = "主体")
    private String attrGroupName;

    @ApiModelProperty(value = "分组排序",example = "0")
    private Integer sort;

    @ApiModelProperty(value = "分组描述",example = "无")
    private String descript;

    @ApiModelProperty(value = "分组图标",example = "")
    private String icon;

    @ApiModelProperty(value = "所属分类id",example = "225")
    private Long catalogId;

    @ApiModelProperty(value = "关联的属性信息")
    private List<AttrVo> attrs=new ArrayList<>();
}
