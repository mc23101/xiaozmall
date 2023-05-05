package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "商品分类信息")
public class CatalogVo implements Serializable {

    @ApiModelProperty(value = "商品分类Id")
    private Long catId;

    @ApiModelProperty(value = "商品分类名称",position = 1)
    private String name;

    @ApiModelProperty(value = "父分类id",position = 2)
    private Long parentCid;


    @ApiModelProperty(value = "分类层级",position = 3)
    private Integer catLevel;

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]",position = 4)
    private Integer showStatus;

    @ApiModelProperty(value = "排序",position = 5)
    private Integer sort;

    @ApiModelProperty(value = "图标地址",position = 6)
    private String icon;

    @ApiModelProperty(value = "计量单位",position = 7)
    private String productUnit;

    @ApiModelProperty(value = "商品数量",position = 8)
    private Integer productCount=null;

    @ApiModelProperty(value = "子分类",position = 9)
    List<CatalogVo> children=null;

    @ApiModelProperty(value = "完整路径",position = 10)
    List<Long> path=new ArrayList<>();

    @Data
    @ApiModel(description = "商品分类和品牌关联信息")
    public static class CatalogBrandRelationVo implements Serializable {

        @ApiModelProperty(value = "商品分类和品牌关联信息Id")
        private Long id;

        @ApiModelProperty(value = "品牌id",position = 1)
        private Long brandId;

        @ApiModelProperty(value = "分类id",position = 2)
        private Long catalogId;

        @ApiModelProperty(value = "品牌名称",position = 3)
        private String brandName;

        @ApiModelProperty(value = "分类名称",position = 4)
        private String catalogName;

    }
}
