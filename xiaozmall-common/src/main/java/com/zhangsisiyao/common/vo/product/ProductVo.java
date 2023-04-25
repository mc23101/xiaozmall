package com.zhangsisiyao.common.vo.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductVo implements Serializable {
    @Min(1)
    private Long id;

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

    private String defaultImg;
    /**
     *
     */
    @NotNull
    private BigDecimal weight;

    @NotNull
    private Date createTime;

    @NotNull
    private Date updateTime;

    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    @Min(value = 0)
    @Max(value = 1)
    private Integer publishStatus;


    List<ImageVo> descript;

    //TODO 修改image信息
    List<ImageVo> images;

    BoundsVo bounds;

    List<AttrGroupVo.AttrGroupWithAttrValueVo> spuAttrGroup;

    @Size(min = 1)
    List<SkuVo> skus;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SkuVo extends SkuInfoVo implements Serializable {

        List<ImageVo> images;

        List<AttrVo.AttrValueVo> attr;

        //TODO 会员信息折扣

        BigDecimal fullCount;

        BigDecimal discount;

        Integer countStatus;

        BigDecimal fullPrice;

        BigDecimal reducePrice;

        Integer priceStatus;

        List<Integer> memberPrice;

    }

    @Data
    public static class BoundsVo implements Serializable {

        @NotNull
        BigDecimal buyBounds;

        @NotNull
        BigDecimal growBounds;
    }
}
