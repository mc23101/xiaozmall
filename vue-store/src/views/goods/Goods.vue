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
          <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
        </el-input>
      </div>
    </div>
    <!--搜索栏结束-->
    <!-- 面包屑 -->
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>全部结果</el-breadcrumb-item>
        <el-breadcrumb-item v-if="SearchParam.catalogId!==null">{{dataResp.catalogMap[SearchParam.catalogId].name}}</el-breadcrumb-item>
        <el-breadcrumb-item v-if="SearchParam.keyword!==null">{{SearchParam.keyword}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div>
      <sort-bar></sort-bar>
    </div>
    <div>
      <list :product='dataResp.product'></list>
    </div>
    <div>
      <footer-container></footer-container>
    </div>
  </div>
</template>
<script>

import topbar from "@/views/components/topbar";
import sortBar from "@/views/goods/sortBar";
import list from "@/views/goods/list";
import footerContainer from "@/views/components/footerContainer";
export default {
  components:{
    topbar,sortBar,list,footerContainer
  },
  data() {
    return {
      dataResp:{
        catalogMap:Map,
        catalogTree:[],
        product:[]
      },
      SearchParam:{
        keyword:null,
        catalogId:null
      }
    };
  },
  methods:{
    searchClick() {

    },
    getCategoryMapAndTree(){
      this.$axios
          .post(this.$target+"/product/category/list/tree", {})
          .then(res => {
            this.dataResp.catalogTree = res.data.data;
            console.log(this.dataResp.catalogTree)
          })
          .catch(err => {
            return Promise.reject(err);
          });
      this.$axios
          .post(this.$target+"/product/category/list/map", {})
          .then(res => {
            this.dataResp.catalogMap = res.data.data;
            console.log(this.dataResp.catalogMap)
          })
          .catch(err => {
            return Promise.reject(err);
          });
    }
  },
  created() {
    this.getCategoryMapAndTree()
    let catalogId=this.$route.query.catalogId.split(",")
    this.SearchParam.catalogId=catalogId[2]
    this.SearchParam.keyword=this.$route.query.search
  }
};
</script>
<style scoped>
@import "~@/assets/css/goods.css";
</style>