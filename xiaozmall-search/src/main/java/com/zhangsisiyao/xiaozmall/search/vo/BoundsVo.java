package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BoundsVo implements Serializable {

    @NotNull
    BigDecimal buyBounds;

    @NotNull
    BigDecimal growBounds;
}