package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchParam implements Serializable {

    String keyword;

    String brandId;

    String catalogId;

    List<AttrValueVo> spuAttrs;

    List<AttrValueVo> skuAttrs;

}
