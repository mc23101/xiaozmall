package com.zhangsisiyao.xiaozmall.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.common.vo.*;
import com.zhangsisiyao.xiaozmall.search.service.ProductService;
import com.zhangsisiyao.xiaozmall.search.service.SearchService;
import com.zhangsisiyao.xiaozmall.search.vo.*;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("SearchService")
public class SearchServiceImpl implements SearchService {

    @Autowired
    RestHighLevelClient esClient;

    @Autowired
    ProductService productService;

    @SneakyThrows
    @Override
    public SearchResult search(SearchParam searchParam) {
        List<SearchProductVo> searchProductVos = searchProduct(searchParam);
        SearchResult result = searchAttrs(searchParam);
        result.setProducts(searchProductVos);
        return result;
    }


    public BoolQueryBuilder createQueryParam(SearchParam searchParam){
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        if(StringUtils.isNotEmpty(searchParam.getKeyword())){
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("spuName",searchParam.getKeyword()));
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("spuDescription",searchParam.getKeyword()));
        }
        if(StringUtils.isNotEmpty(searchParam.getCatalogId())){
            boolQueryBuilder.must(new MatchQueryBuilder("catalogId",searchParam.getCatalogId()));
        }
        if(StringUtils.isNotEmpty(searchParam.getBrandId())){
            boolQueryBuilder.must(new MatchQueryBuilder("brandId",searchParam.getBrandId()));
        }
        //查询spuAttrs
        {
            searchParam.getSpuAttrs().forEach((attrValueVo -> {
                BoolQueryBuilder spuAttrBoolQueryBuilder=QueryBuilders.boolQuery();
                spuAttrBoolQueryBuilder.must(QueryBuilders.matchQuery("spuAttrGroup.attrs.attrId",attrValueVo.getAttrId()));
                spuAttrBoolQueryBuilder.must(QueryBuilders.matchQuery("spuAttrGroup.attrs.attrValue",attrValueVo.getAttrValue()));
                NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder("spuAttrGroup.attrs",spuAttrBoolQueryBuilder, ScoreMode.None);
                NestedQueryBuilder spuAttrGroupNestedQueryBuilder = new NestedQueryBuilder("spuAttrGroup",nestedQueryBuilder, ScoreMode.None);
                boolQueryBuilder.must(spuAttrGroupNestedQueryBuilder);
            }));
        }
        //查询skuAttrs
        {
            BoolQueryBuilder skusBoolQuery=new BoolQueryBuilder();
            searchParam.getSkuAttrs().forEach(attrValueVo -> {
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
        return boolQueryBuilder;
    }

    public List<SearchProductVo> searchProduct(SearchParam searchParam) throws IOException {
        SearchRequest request=new SearchRequest();
        request.indices("spu");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=createQueryParam(searchParam);

        sourceBuilder.query(boolQueryBuilder).from((searchParam.getPageIndex()-1)*searchParam.getPageSize()).size(searchParam.getPageSize());
        request.source(sourceBuilder);
        SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);
        List<SearchProductVo> products=new ArrayList<>();
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for(SearchHit hit:search.getHits().getHits()){
            SearchProductVo product = mapper.readValue(hit.getSourceAsString(), SearchProductVo.class);
            product.getSkus().forEach((skuVo -> {
                product.setPrice(product.getPrice().min(skuVo.getPrice()));
            }));
            products.add(product);
        }
        return products;
    }

    public SearchResult searchAttrs(SearchParam searchParam) throws IOException {
        Set<Long> existSpuAttrs=new HashSet<>();
        Set<Long> existSkuAttrs=new HashSet<>();
        searchParam.getSpuAttrs().forEach((attrValueVo -> {
            existSpuAttrs.add(attrValueVo.getAttrId());
        }));

        searchParam.getSkuAttrs().forEach(attrValueVo -> {
            existSkuAttrs.add(attrValueVo.getAttrId());
        });
        SearchRequest request=new SearchRequest();
        request.indices("spu");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=createQueryParam(searchParam);

        sourceBuilder.query(boolQueryBuilder);
        //聚合查询catalog
        {
            sourceBuilder.aggregation(AggregationBuilders.terms("catalogId").field("catalogId")).size(20);
        }
        //聚合查询brand
        {
            sourceBuilder.aggregation(AggregationBuilders.terms("brandId").field("brandId")).size(20);
        }
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
        sourceBuilder.from(0).size(0);
        request.source(sourceBuilder);
        SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);

        SearchResult result=new SearchResult();
        ObjectMapper mapper=new ObjectMapper();
        //解析catalog聚合结果
        {
            List<CatalogVo> catalogs=new ArrayList<>();
            ParsedLongTerms catalog = search.getAggregations().get("catalogId");
            catalog.getBuckets().forEach((item)->{
                String catId = String.valueOf(item.getKey());
                Object category = productService.getCatalogInfo(catId).get("category");
                CatalogVo catalogVo;
                try {
                    catalogVo = mapper.readValue(mapper.writeValueAsString(category), CatalogVo.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                catalogs.add(catalogVo);
            });
            result.setCatalogs(catalogs);
        }

        //解析brand聚合结果
        {
            List<BrandVo> brands=new ArrayList<>();
            ParsedLongTerms brand = search.getAggregations().get("brandId");
            brand.getBuckets().forEach((item)->{
                String brandId = String.valueOf(item.getKey());
                Object brand1 = productService.getBrandInfo(brandId).get("brand");
                BrandVo brandVo;
                try {
                    brandVo = mapper.readValue(mapper.writeValueAsString(brand1), BrandVo.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                brands.add(brandVo);
            });
            result.setBrands(brands);
        }

        //解析spuAttrs的聚合结果
        {
            List<AttrVo> spuAttrs=new ArrayList<>();
            ParsedNested baseAttrs = search.getAggregations().get("baseAttrs");
            ((ParsedLongTerms)baseAttrs.getAggregations().get("baseAttrs.attrId")).getBuckets().forEach((item)->{
                Long attr_id = (Long) item.getKey();
                if(!existSpuAttrs.contains(attr_id)){
                    Object attr = productService.getAttrInfo(String.valueOf(attr_id)).get("attr");
                    AttrVo attrVo;
                    try {
                        attrVo = mapper.readValue(mapper.writeValueAsString(attr), AttrVo.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    spuAttrs.add(attrVo);
                }
            });
            result.setSpuAttrs(spuAttrs);
        }
        //解析skuAttrs
        {
            List<AttrVo> skuAttrs=new ArrayList<>();
            ParsedNested skus = search.getAggregations().get("skus");
            ParsedNested skus_attr = skus.getAggregations().get("skus.attr");
            ((ParsedLongTerms)skus_attr.getAggregations().get("skus.attr.attrId")).getBuckets().forEach((item->{
                Long attr_id = (Long) item.getKey();
                if(!existSkuAttrs.contains(attr_id)){
                    Object attr = productService.getAttrInfo(String.valueOf(attr_id)).get("attr");
                    AttrVo attrVo;
                    try {
                        attrVo = mapper.readValue(mapper.writeValueAsString(attr), AttrVo.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    skuAttrs.add(attrVo);
                }
            }));
            result.setSkuAttrs(skuAttrs);
        }
        result.setCount(search.getHits().getTotalHits().value);
        return result;
    }

}
