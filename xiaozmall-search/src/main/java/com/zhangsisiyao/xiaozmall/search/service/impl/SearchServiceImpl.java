package com.zhangsisiyao.xiaozmall.search.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.xiaozmall.search.service.SearchService;
import com.zhangsisiyao.xiaozmall.search.vo.ProductVo;
import com.zhangsisiyao.xiaozmall.search.vo.SearchParam;
import com.zhangsisiyao.xiaozmall.search.vo.SearchResult;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("SearchService")
public class SearchServiceImpl implements SearchService {

    @Autowired
    RestHighLevelClient esClient;

    @SneakyThrows
    @Override
    public SearchResult search(SearchParam searchParam) {
        SearchRequest request=new SearchRequest();
        request.indices("spu");
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        if(StringUtils.isNotEmpty(searchParam.getKeyword())){
            boolQueryBuilder.should(QueryBuilders.fuzzyQuery("spuName",searchParam.getKeyword()).fuzziness(Fuzziness.AUTO));
            boolQueryBuilder.should(QueryBuilders.fuzzyQuery("spuDescription",searchParam.getKeyword()).fuzziness(Fuzziness.AUTO));
        }
        if(StringUtils.isNotEmpty(searchParam.getCatalogId())){
            boolQueryBuilder.must(new MatchQueryBuilder("catalogId",searchParam.getCatalogId()));
        }
        if(StringUtils.isNotEmpty(searchParam.getBrandId())){
            boolQueryBuilder.must(new MatchQueryBuilder("brandId",searchParam.getBrandId()));
        }
        Set<Long> existSpuAttrs=new HashSet<>();
        //查询spuAttrs
        {
            searchParam.getSpuAttrs().forEach((attrValueVo -> {
                existSpuAttrs.add(attrValueVo.getAttrId());
                BoolQueryBuilder spuAttrBoolQueryBuilder=QueryBuilders.boolQuery();
                spuAttrBoolQueryBuilder.must(QueryBuilders.matchQuery("baseAttrs.attrId",attrValueVo.getAttrId()));
                spuAttrBoolQueryBuilder.must(QueryBuilders.matchQuery("baseAttrs.attrValue",attrValueVo.getAttrValue()));
                NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder("baseAttrs",spuAttrBoolQueryBuilder, ScoreMode.None);
                boolQueryBuilder.must(nestedQueryBuilder);
            }));
        }
        Set<Long> existSkuAttrs=new HashSet<>();
        //查询skuAttrs
        {
            BoolQueryBuilder skusBoolQuery=new BoolQueryBuilder();
            searchParam.getSkuAttrs().forEach(attrValueVo -> {
                existSkuAttrs.add(attrValueVo.getAttrId());
                BoolQueryBuilder skusAttrBoolQuery=new BoolQueryBuilder();
                skusAttrBoolQuery.must(QueryBuilders.matchQuery("skus.attr.attrId",attrValueVo.getAttrId()));
                skusAttrBoolQuery.must(QueryBuilders.matchQuery("skus.attr.attrValue",attrValueVo.getAttrValue()));
                NestedQueryBuilder attrNestedQueryBuilder=QueryBuilders.nestedQuery("skus.attr",skusAttrBoolQuery,ScoreMode.None);
                skusBoolQuery.must(attrNestedQueryBuilder);
            });
            if(searchParam.getMinPrice()!=0&&searchParam.getMaxPrice()!=0){
                skusBoolQuery.must(QueryBuilders.rangeQuery("skus.price").gte(searchParam.getMinPrice()).lte(searchParam.getMaxPrice()));
            }
            NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder("skus",skusBoolQuery, ScoreMode.None);
            boolQueryBuilder.must(nestedQueryBuilder);
        }

        sourceBuilder.query(boolQueryBuilder).from((searchParam.getPageIndex()-1)*searchParam.getPageSize()).size(searchParam.getPageSize());

        //聚合查询spuAttrs
        {
            sourceBuilder.aggregation(AggregationBuilders.nested("baseAttrs","baseAttrs").subAggregation(AggregationBuilders.terms("baseAttrs.attrId").field("baseAttrs.attrId"))).size(5);
        }
        //聚合查询skuAttrs
        {
            sourceBuilder.aggregation(
                    AggregationBuilders.nested("skus","skus").subAggregation(AggregationBuilders.nested("skus.attr","skus.attr").subAggregation(AggregationBuilders.terms("skus.attr.attrId").field("skus.attr.attrId")))
            ).size(5);
        }


        request.source(sourceBuilder);
        SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);

        SearchResult result=new SearchResult();

        //解析spuAttrs的聚合结果
        {
            List<Long> spuAttrs=new ArrayList<>();
            ParsedNested baseAttrs = search.getAggregations().get("baseAttrs");
            ((ParsedLongTerms)baseAttrs.getAggregations().get("baseAttrs.attrId")).getBuckets().forEach((item)->{
                Long attr_id = (Long) item.getKey();
                if(!existSpuAttrs.contains(attr_id)){
                    spuAttrs.add(attr_id);
                }
            });
            result.setSpuAttrs(spuAttrs);
        }
        //解析skuAttrs
        {
            List<Long> skuAttrs=new ArrayList<>();
            ParsedNested skus = search.getAggregations().get("skus");
            ParsedNested skus_attr = skus.getAggregations().get("skus.attr");
            ((ParsedLongTerms)skus_attr.getAggregations().get("skus.attr.attrId")).getBuckets().forEach((item->{
                Long attr_id = (Long) item.getKey();
                if(!existSkuAttrs.contains(attr_id)){
                    skuAttrs.add(attr_id);
                }
            }));
            result.setSkuAttrs(skuAttrs);
        }

        List<ProductVo> products=new ArrayList<>();
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for(SearchHit hit:search.getHits().getHits()){
            products.add(mapper.readValue(hit.getSourceAsString(),ProductVo.class));
        }

        result.setProducts(products);
        result.setCount(search.getHits().getTotalHits().value);
        return result;
    }
}
