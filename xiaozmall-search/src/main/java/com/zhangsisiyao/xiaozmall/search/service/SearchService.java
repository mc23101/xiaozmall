package com.zhangsisiyao.xiaozmall.search.service;

import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import com.zhangsisiyao.xiaozmall.search.vo.SearchResult;

public interface SearchService {
    SearchResult search(SearchParam param);

}
