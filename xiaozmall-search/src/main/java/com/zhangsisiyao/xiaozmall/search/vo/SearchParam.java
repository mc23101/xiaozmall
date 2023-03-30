package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchParam implements Serializable {
    long catalogId;

    String keyword;

    double min;

    double max;

    int sort;


}
