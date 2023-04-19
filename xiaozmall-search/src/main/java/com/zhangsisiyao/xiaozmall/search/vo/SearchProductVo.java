package com.zhangsisiyao.xiaozmall.search.vo;

import com.zhangsisiyao.common.vo.ProductVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.elasticsearch.search.DocValueFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "spu",createIndex = false)
public class SearchProductVo extends ProductVo implements Serializable {

    @Min(1)
    @Id
    private Long id;

    private BigDecimal price;

}
