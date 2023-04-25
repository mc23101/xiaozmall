package com.zhangsisiyao.common.vo.product;

import com.baomidou.mybatisplus.annotation.TableId;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(description = "商品spu信息")
public class SpuInfoVo implements Serializable {

    @ApiModelProperty(value = "商品spuId")
    private Long id;

    @ApiModelProperty(value = "商品名称",position = 1)
    private String spuName;

    @ApiModelProperty(value = "商品描述",position = 2)
    private String spuDescription;

    @ApiModelProperty(value = "所属分类id",position = 3)
    private Long catalogId;

    @ApiModelProperty(value = "品牌id",position = 4)
    private Long brandId;

    @ApiModelProperty(value = "默认图片地址",position = 5)
    private String defaultImg;

    @ApiModelProperty(value = "商品重量",position = 6)
    private BigDecimal weight;

    @ApiModelProperty(value = "上架状态[0 - 下架，1 - 上架]",position = 7)
    private Integer publishStatus;
}
