package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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


    @Data
    @ApiModel(description = "属性属性分组关联信息")
    public static class AttrAttrgroupRelationVo implements Serializable {

        @ApiModelProperty(value = "关联信息Id，新增时自动生成，无需填写")
        private Long id;

        @ApiModelProperty(value = "属性id")
        private Long attrId;

        @ApiModelProperty(value = "属性分组id")
        private Long attrGroupId;

        @ApiModelProperty(value = "属性组内排序")
        private Integer attrSort;
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @ApiModel(value = "属性分组携带属性值信息")
    public static class AttrGroupWithAttrValueVo extends AttrGroupVo implements Serializable{
        private List<AttrVo.AttrValueVo> attrValues=new ArrayList<>();
    }
}
