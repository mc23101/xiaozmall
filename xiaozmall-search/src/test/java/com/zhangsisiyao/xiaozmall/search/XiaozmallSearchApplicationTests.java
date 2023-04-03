package com.zhangsisiyao.xiaozmall.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.xiaozmall.search.service.SearchService;
import com.zhangsisiyao.xiaozmall.search.vo.AttrValueVo;
import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import com.zhangsisiyao.xiaozmall.search.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class XiaozmallSearchApplicationTests {

    @Autowired
    SearchService searchService;

    public SearchParam getParam() throws JsonProcessingException {
        SearchParam searchParam=new SearchParam();
//        searchParam.setKeyword("小");
        searchParam.setCatalogId("225");
        searchParam.setBrandId("1");
        AttrValueVo attrValueVo1 = new AttrValueVo();
        attrValueVo1.setAttrId(13L);
        attrValueVo1.setAttrValue("158.3");
        searchParam.getSpuAttrs().add(attrValueVo1);
        AttrValueVo attrValueVo2 = new AttrValueVo();
        attrValueVo2.setAttrId(11L);
        attrValueVo2.setAttrValue("黑色");
        searchParam.getSpuAttrs().add(attrValueVo2);

        AttrValueVo attrValueVo3=new AttrValueVo();
        attrValueVo3.setAttrId(10L);
        attrValueVo3.setAttrValue("6GB");
        searchParam.getSkuAttrs().add(attrValueVo3);

        return searchParam;
    }

    @Test
    void contextLoads() throws IOException {
        SearchParam searchParam=getParam();
        SearchResult searchResult = searchService.search(searchParam);
    }

}
