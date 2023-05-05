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

    @ApiModelProperty(value = "属性名",position = 1)
    private String attrName;

    @ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]",position = 2)
    private Integer searchType;

    @ApiModelProperty(value = "属性图标",position = 3)
    private String icon;

    @ApiModelProperty(value = "可选值列表[用分号分隔]",position = 4)
    private String valueSelect;

    @ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]",position = 5)
    private Integer attrType;

    @ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]",position = 6)
    private Long enable;

    @ApiModelProperty(value = "分类Id",position = 7)
    private Long catalogId;

    @ApiModelProperty(value = "是否快速展示",position = 8)
    private Integer showDesc;

    private Integer valueType;

    @Data
    @ApiModel(description = "商品spu或sku属性值信息")
    public static class AttrValueVo implements Serializable {

        @ApiModelProperty("属性值信息id")
        private Long id;

        @ApiModelProperty(value = "属性Id",position = 1)
        private Long attrId;

        @ApiModelProperty(value = "spuId,如果为spu属性值信息，则此项不为0",position = 2)
        private Long spuId;

        @ApiModelProperty(value = "skuId,如果为sku属性值信息，则此项不为0",position = 3)
        private Long skuId;

        @ApiModelProperty(value = "属性组id，如果为spu属性值信息，则此项不为0",position = 4)
        private Long groupId;

        @ApiModelProperty(value = "属性值",position = 5)
        private String attrValue;

        @ApiModelProperty(value = "属性名称",position = 6)
        private String attrName;

        @ApiModelProperty(value = "是否快速展示",position = 7)
        private Integer showDesc;
    }
}
