package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "spu")
public class ProductVo implements Serializable {
    @Min(1)
    @Id
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


    List<String> decript;

    //TODO 修改image信息
    List<String> images;

    BoundsVo bounds;

    List<BaseAttrValueVo> baseAttrs;

    @Size(min = 1)
    List<SkuVo> skus;
}
