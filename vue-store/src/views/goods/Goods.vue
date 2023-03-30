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
        <el-input placeholder="请输入搜索内容" v-model="search">
          <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
        </el-input>
      </div>
    </div>
    <!--搜索栏结束-->
    <!-- 面包屑 -->
    <div class="breadcrumb">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item v-if="catalogId.length!==0">{{catalogId[2]}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div>
      <sort-bar></sort-bar>
    </div>
    <div>
      <list :product='product'></list>
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
      search:'',
      catalogId:[],
      product:[]
    };
  },
  methods:{
    searchClick() {
      console.log(this.catalogId)
    }
  },
  created() {
    this.catalogId=this.$route.query.catalogId.split(",")
    this.search=this.$route.query.search
  }
};
</script>
<style scoped>
@import "~@/assets/css/goods.css";
</style>