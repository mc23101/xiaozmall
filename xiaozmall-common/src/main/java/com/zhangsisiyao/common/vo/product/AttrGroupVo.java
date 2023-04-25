package com.zhangsisiyao.common.vo.product;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
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

    @ApiModelProperty(value = "分组名称",example = "主体",position = 1)
    private String attrGroupName;

    @ApiModelProperty(value = "分组排序",example = "0",position = 2)
    private Integer sort;

    @ApiModelProperty(value = "分组描述",example = "无",position = 3)
    private String descript;

    @ApiModelProperty(value = "分组图标",example = "",position = 4)
    private String icon;

    @ApiModelProperty(value = "所属分类id",example = "225",position = 5)
    private Long catalogId;

    @ApiModelProperty(value = "关联的属性信息",position = 6)
    private List<AttrVo> attrs=new ArrayList<>();


    @Data
    @ApiModel(description = "属性属性分组关联信息")
    public static class AttrAttrgroupRelationVo implements Serializable {

        @ApiModelProperty(value = "关联信息Id，新增时自动生成，无需填写")
        private Long id;

        @ApiModelProperty(value = "属性id",position = 1)
        private Long attrId;

        @ApiModelProperty(value = "属性分组id",position = 2)
        private Long attrGroupId;

        @ApiModelProperty(value = "属性组内排序",position = 3)
        private Integer attrSort;
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @ApiModel(description = "属性分组携带属性值信息")
    public static class AttrGroupWithAttrValueVo extends AttrGroupVo implements Serializable{
        private List<AttrVo.AttrValueVo> attrValues=new ArrayList<>();
    }
}
