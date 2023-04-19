package com.zhangsisiyao.xiaozmall.search.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.xiaozmall.search.vo.SearchProductVo;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpuUpListener {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "product.spu.up"),
                    exchange = @Exchange(type = ExchangeTypes.TOPIC,value = "ElasticSearch"),
                    key = "product.spu.up"
            )
    })
    @RabbitHandler
    public void receiveUp(String in) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        SearchProductVo product = mapper.readValue(in, SearchProductVo.class);
        if(!elasticsearchRestTemplate.indexExists(SearchProductVo.class)){
            elasticsearchRestTemplate.createIndex(SearchProductVo.class);
            elasticsearchRestTemplate.putMapping(SearchProductVo.class);
        }
        elasticsearchRestTemplate.save(product);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "product.spu.down"),
                    exchange = @Exchange(type = ExchangeTypes.TOPIC,value = "ElasticSearch"),
                    key = "product.spu.down"
            )
    })
    @RabbitHandler
    public void receiveDown(Long spuId){
        boolean exists = elasticsearchRestTemplate.exists(String.valueOf(spuId), SearchProductVo.class);
        if(exists){
            elasticsearchRestTemplate.delete(String.valueOf(spuId), SearchProductVo.class);
        }
    }

}
