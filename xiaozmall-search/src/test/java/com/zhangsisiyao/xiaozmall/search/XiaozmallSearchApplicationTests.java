package com.zhangsisiyao.xiaozmall.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.xiaozmall.search.service.SearchService;
import com.zhangsisiyao.xiaozmall.search.vo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class XiaozmallSearchApplicationTests {

    @Autowired
    SearchService searchService;

    public SearchParam getParam() throws JsonProcessingException {
        SearchParam searchParam=new SearchParam();
        searchParam.setKeyword("13");
        //searchParam.setCatalogId("266");
        return searchParam;
    }

    @Test
    void contextLoads() throws IOException {
        SearchParam searchParam=getParam();
        SearchResult searchResult = searchService.search(searchParam);
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(searchResult));
    }


}
