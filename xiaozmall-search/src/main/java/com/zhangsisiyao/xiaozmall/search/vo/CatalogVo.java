package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CatalogVo implements Serializable {

    private Long catId;

    private String name;
    /**
     * 父分类id
     */
    @Min(value = 0)
    private Long parentCid;
    /**
     * 层级
     */
    @Min(value = 0)
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */

    @Min(value = 0)
    @Min(value = 1)
    private Integer showStatus;
    /**
     * 排序
     */
    @Min(value = 0)
    private Integer sort;
    /**
     * 图标地址
     */
    @NotEmpty
    private String icon;
    /**
     * 计量单位
     */
    @NotEmpty
    private String productUnit;
    /**
     * 商品数量
     */
    @Min(value = 0)
    private Integer productCount;

    private String path;
}
