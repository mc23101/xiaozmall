package com.zhangsisiyao.xiaozmall.search.service;

import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import com.zhangsisiyao.xiaozmall.search.vo.SearchResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface SearchService {
    SearchResult search(SearchParam param);

}
