package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BoundsVo implements Serializable {

    BigDecimal buyBounds;

    BigDecimal growBounds;
}