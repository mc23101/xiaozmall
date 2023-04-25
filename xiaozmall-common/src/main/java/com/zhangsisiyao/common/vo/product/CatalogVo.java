package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "商品分类信息")
public class CatalogVo implements Serializable {

    @ApiModelProperty(value = "商品分类Id")
    private Long catId;

    @ApiModelProperty(value = "商品分类名称")
    private String name;

    @ApiModelProperty(value = "父分类id")
    private Long parentCid;


    @ApiModelProperty(value = "分类层级")
    private Integer catLevel;

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标地址")
    private String icon;

    @ApiModelProperty(value = "计量单位")
    private String productUnit;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "子分类")
    List<CatalogVo> children;

    @Data
    @ApiModel(description = "商品分类和品牌关联信息")
    public static class CatalogBrandRelationVo implements Serializable {

        @ApiModelProperty(value = "商品分类和品牌关联信息Id")
        private Long id;

        @ApiModelProperty(value = "品牌id")
        private Long brandId;

        @ApiModelProperty(value = "分类id")
        private Long catalogId;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "分类名称")
        private String catalogName;

    }
}
