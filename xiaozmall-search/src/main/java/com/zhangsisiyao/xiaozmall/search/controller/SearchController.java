package com.zhangsisiyao.xiaozmall.search.controller;

import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.search.service.SearchService;
import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ElasticSearch")
public class SearchController {


    @Autowired
    SearchService searchService;

    @RequestMapping("/Search")
    public R search(SearchParam param){
        List<ProductVo> search = searchService.search(param);
        return R.ok().put("data",search);
    }

}
