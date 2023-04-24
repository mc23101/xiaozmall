package com.zhangsisiyao.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@ApiModel(description = "属性属性分组关联信息")
public class AttrAttrgroupRelationVo implements Serializable {

    @ApiModelProperty(value = "关联信息Id，新增时自动生成，无需填写")
    private Long id;

    @ApiModelProperty(value = "属性id")
    private Long attrId;

    @ApiModelProperty(value = "属性分组id")
    private Long attrGroupId;

    @ApiModelProperty(value = "属性组内排序")
    private Integer attrSort;
}
