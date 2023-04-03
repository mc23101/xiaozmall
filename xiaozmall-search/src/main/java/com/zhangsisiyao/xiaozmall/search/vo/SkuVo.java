package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuVo implements Serializable {

    String skuName;

    BigDecimal price;

    String skuTitle;

    String skuSubtitle;

    List<ImageVo> images;

    @Field(type = FieldType.Nested)
    List<AttrValueVo> attr;


    //TODO 会员信息折扣

    BigDecimal fullCount;

    BigDecimal discount;

    Integer countStatus;

    BigDecimal fullPrice;

    BigDecimal reducePrice;

    Integer priceStatus;

    List<Integer> memberPrice;

}
