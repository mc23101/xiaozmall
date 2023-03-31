package com.zhangsisiyao.xiaozmall.search.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SpuUpListener {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("product.spu.up"),
                    exchange = @Exchange(type = ExchangeTypes.TOPIC,value = "ElasticSearch"),
                    key = "product.spu.up"
            )
    })
    @RabbitHandler
    public void receiveUp(String in) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        elasticsearchRestTemplate.save(mapper.readValue(in,ProductVo.class));
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("product.spu.down"),
                    exchange = @Exchange(type = ExchangeTypes.TOPIC,value = "ElasticSearch"),
                    key = "product.spu.down"
            )
    })
    @RabbitHandler
    public void receiveDown(Long spuId){
        elasticsearchRestTemplate.delete(String.valueOf(spuId),ProductVo.class);
    }

}
