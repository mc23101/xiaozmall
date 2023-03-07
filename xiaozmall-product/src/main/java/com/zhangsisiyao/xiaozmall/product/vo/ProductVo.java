package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductVo implements Serializable {

    /**
     * 商品名称
     */
    @NotEmpty
    private String spuName;
    /**
     * 商品描述
     */
    private String spuDescription;
    /**
     * 所属分类id
     */
    @Min(value = 1)
    private Long catalogId;
    /**
     * 品牌id
     */
    @Min(value = 1)
    private Long brandId;
    /**
     *
     */
    @NotNull
    private BigDecimal weight;
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    @Min(value = 0)
    @Max(value = 1)
    private Integer publishStatus;


    List<String> decript;

    List<String> images;

    BoundsVo bounds;

    List<BaseAttrVo> baseAttrs;

    List<SkuVo> skus;
}
