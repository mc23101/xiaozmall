package com.zhangsisiyao.xiaozmall.search.vo;

import com.zhangsisiyao.common.vo.product.ProductVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchProductVo extends ProductVo implements Serializable {

    private BigDecimal price;

}
