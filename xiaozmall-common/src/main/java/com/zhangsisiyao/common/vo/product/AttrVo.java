package com.zhangsisiyao.common.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@ApiModel(description = "属性信息")
public class AttrVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性Id，新增属性时，自动生成，无需填写")
    private Long attrId;

    @ApiModelProperty(value = "属性名")
    private String attrName;

    @ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;

    @ApiModelProperty(value = "属性图标")
    private String icon;

    @ApiModelProperty(value = "可选值列表[用分号分隔]")
    private String valueSelect;

    @ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    @ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
    private Long enable;

    @ApiModelProperty(value = "分类Id")
    private Long catalogId;

    @ApiModelProperty(value = "是否快速展示")
    private Integer showDesc;

    @Data
    @ApiModel(description = "商品spu或sku属性值信息")
    public static class AttrValueVo implements Serializable {

        @ApiModelProperty("属性值信息id")
        private Long id;

        @ApiModelProperty("属性Id")
        private Long attrId;

        @ApiModelProperty(value = "spuId,如果为spu属性值信息，则此项不为0")
        private Long spuId=0L;

        @ApiModelProperty(value = "skuId,如果为sku属性值信息，则此项不为0")
        private Long skuId;

        @ApiModelProperty(value = "属性组id，如果为spu属性值信息，则此项不为0")
        private Long groupId;

        @ApiModelProperty(value = "属性值")
        private String attrValue;

        @ApiModelProperty(value = "属性名称")
        private String attrName;

        @ApiModelProperty(value = "是否快速展示")
        private Integer showDesc;
    }
}
