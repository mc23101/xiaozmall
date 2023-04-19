package com.zhangsisiyao.common.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
public class BrandVo implements Serializable {
    /**
     * 品牌id
     */
    @Min(value = 1)
    private Long brandId;

    @NotEmpty
    private String name;
    /**
     * 品牌logo地址
     */
    @NotEmpty
    private String logo;
    /**
     * 介绍
     */
    @NotEmpty
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @Max(value = 1)
    @Min(value = 0)
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotEmpty
    private String firstLetter;
    /**
     * 排序
     */
    @Min(value = 0)
    private Integer sort;
}
