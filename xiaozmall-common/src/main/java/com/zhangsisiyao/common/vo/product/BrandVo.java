package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
@ApiModel(description = "品牌信息")
public class BrandVo implements Serializable {
    /**
     * 品牌id
     */
    @Min(value = 1)
    @ApiModelProperty(value = "品牌Id")
    private Long brandId;

    @NotEmpty
    @ApiModelProperty(value = "品牌名称",position = 1)
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "品牌logo地址",position = 2)
    private String logo;

    @NotEmpty
    @ApiModelProperty(value = "品牌介绍",position = 3)
    private String descript;

    @Max(value = 1)
    @Min(value = 0)
    @ApiModelProperty(value = "显示状态[0-不显示；1-显示]",position = 4)
    private Integer showStatus;

    @NotEmpty
    @ApiModelProperty(value = "检索首字母",position = 5)
    private String firstLetter;

    @Min(value = 0)
    @ApiModelProperty(value = "排序",position = 6)
    private Integer sort;
}
