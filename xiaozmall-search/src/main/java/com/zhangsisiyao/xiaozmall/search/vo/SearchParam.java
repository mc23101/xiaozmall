package com.zhangsisiyao.xiaozmall.search.vo;

import com.zhangsisiyao.common.vo.AttrValueVo;
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

    int pageIndex=1;

    int pageSize=20;

    List<AttrValueVo> spuAttrs=new ArrayList<>();

    List<AttrValueVo> skuAttrs=new ArrayList<>();

}
