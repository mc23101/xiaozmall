package com.zhangsisiyao.xiaozmall.search.controller;

import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.search.service.SearchService;
import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import com.zhangsisiyao.xiaozmall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping("/search")
    public R search(@RequestBody(required = false) SearchParam param){
        System.out.println(param);
        SearchResult search = searchService.search(param);
        return R.ok().put("data",search);
    }

}
