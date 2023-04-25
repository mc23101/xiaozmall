package com.zhangsisiyao.xiaozmall.search.vo;

import com.zhangsisiyao.common.vo.product.AttrVo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchParam implements Serializable {

    String keyword;

    String brandId;

    String catalogId;

    float minPrice=0;

    float maxPrice=0;

    int sortState;

    int pageIndex=1;

    int pageSize=20;

    List<AttrVo.AttrValueVo> spuAttrs=new ArrayList<>();

    List<AttrVo.AttrValueVo> skuAttrs=new ArrayList<>();
}
