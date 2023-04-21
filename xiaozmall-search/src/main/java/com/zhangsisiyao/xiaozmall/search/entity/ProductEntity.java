package com.zhangsisiyao.xiaozmall.search.entity;

import com.zhangsisiyao.common.vo.AttrGroupWithAttrValueVo;
import com.zhangsisiyao.common.vo.BoundsVo;
import com.zhangsisiyao.common.vo.ImageVo;
import com.zhangsisiyao.common.vo.SkuVo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "spu",createIndex = false)
public class ProductEntity implements Serializable {
    
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

    @Field(type = FieldType.Nested)
    List<ImageVo> descript;

    //TODO 修改image信息
    @Field(type = FieldType.Nested)
    List<ImageVo> images;

    BoundsVo bounds;

    @Field(type = FieldType.Nested)
    List<AttrGroupWithAttrValueVo> spuAttrGroup;

    @Size(min = 1)
    List<SkuVo> skus;
}
