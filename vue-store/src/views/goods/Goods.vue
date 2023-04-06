<template>
  <div class="main">
    <div class="top">
      <topbar></topbar>
    </div>
    <!--搜索栏-->
    <div class="search">
      <div class="logo">
        <router-link to="/">
          <img src="@/assets/imgs/logo.png" alt />
        </router-link>
      </div>
      <div class="so">
        <el-input placeholder="请输入搜索内容" v-model="SearchParam.keyword">
          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
        </el-input>
      </div>
    </div>
    <!--搜索栏结束-->
    <!-- 面包屑 -->
    <div>
      <breadcrumb :data-resp="SearchResult" :search-param="SearchParam"></breadcrumb>
    </div>
    <!--参数过滤器-->
    <AttrFilter :search-result="SearchResult"></AttrFilter>
    <!--排序器-->
    <sort-bar></sort-bar>
    <!--搜索结果-->
    <div>
      <list :search-result="SearchResult"></list>
    </div>
    <footer-container></footer-container>
  </div>
</template>
<script>

import topbar from "@/views/components/topbar";
import sortBar from "@/views/goods/sortBar";
import list from "@/views/goods/list";
import footerContainer from "@/views/components/footerContainer";
import breadcrumb from "@/views/goods/breadcrumb";
import PubSub from "pubsub-js";
import AttrFilter from "@/views/goods/AttrFilter";
export default {
  components:{
    AttrFilter, topbar,sortBar,list,footerContainer,breadcrumb
  },
  data() {
    return {
      pageSub:'',
      sortBarSub:'',
      spuAttrSub:'',
      skuAttrSub:'',
      breadcrumbSub:'',
      SearchParam:{
        keyword:'',
        catalogId:null,
        minPrice:0,
        maxPrice:0,
        spuAttrs:[],
        skuAttrs:[],
        pageSize:20,
        pageIndex: 1
      },
      SearchResult:{
      }
    };
  },
  watch:{
    SearchParam(){
      this.search()
    }
  },
  methods:{
    search(){
      console.log(this.SearchParam.keyword)
      this.$axios
          .post(this.$target+"/search/search",this.SearchParam)
          .then(res=>{
            this.SearchResult=res.data.data
          })
    }
  },
  created() {
    // eslint-disable-next-line no-unused-vars
    this.sortBarSub=PubSub.subscribe('sortUpdate',(msg,val)=>{
      this.SearchParam.minPrice=val.min*1.0.toFixed(2)
      this.SearchParam.maxPrice=val.max*1.0.toFixed(2)
      this.search()
    })
    this.spuAttrSub=PubSub.subscribe('pushSpuAttr',(msg,val)=>{
      let flag=true
      this.SearchParam.spuAttrs.forEach(attr=>{
        if(attr.attrId===val.attrId){
          flag=false
        }
      })
      if(flag){
        this.SearchParam.spuAttrs.push(val)
      }
      this.search()
    })

    this.skuAttrSub=PubSub.subscribe("pushSkuAttr",(msg,val)=>{
      let flag=true
      this.SearchParam.skuAttrs.forEach(attr=>{
        if(attr.attrId===val.attrId){
          flag=false
        }
      })
      if(flag){
        this.SearchParam.skuAttrs.push(val)
      }
      this.search()
      this.search()
    })

    this.breadcrumbSub=PubSub.subscribe("changeParam",(msg,val)=>{
      this.SearchParam=val
      this.search()
    })

    this.pageSub=PubSub.subscribe("pageChange",(msg,val)=>{
      this.SearchParam.pageSize=val.pageSize
      this.SearchParam.pageIndex=val.pageIndex
      this.search()
    })

    if(this.$route.params.catalogId){
      let catalogId=this.$route.params.catalogId.split(",")
      this.SearchParam.catalogId=catalogId[2]
    }
    if(this.$route.params.search){
      this.SearchParam.keyword=this.$route.params.search
    }
    this.search()
  },
  destroyed() {
    PubSub.unsubscribe(this.sortBarSub)
    PubSub.unsubscribe(this.spuAttrSub)
    PubSub.unsubscribe(this.skuAttrSub)
    PubSub.unsubscribe(this.breadcrumbSub)
  }
};
</script>
<style scoped>
@import "~@/assets/css/goods.css";
</style>