package com.zhangsisiyao.xiaozmall.search.service;

import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    List<ProductVo> search(SearchParam param);
}
