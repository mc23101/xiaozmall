<template>
<div>
  <!-- 登录模块 -->
  <MyLogin></MyLogin>
  <!-- 注册模块 -->
  <MyRegister :register="register" @fromChild="isRegister"></MyRegister>
  <!-- 主要区域容器 -->
  <el-header>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        active-text-color="#409eff"
        router
    >
      <div class="logo">
        <router-link to="/">
          <img src="@/assets/imgs/logo.png" alt />
        </router-link>
      </div>
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="/goods">全部商品</el-menu-item>
      <el-menu-item index="/about">关于我们</el-menu-item>

      <div class="so">
        <el-input placeholder="请输入搜索内容" v-model="search">
          <el-button slot="append" icon="el-icon-search" @click="searchClick"></el-button>
        </el-input>
      </div>
    </el-menu>
    <!-- 顶栏容器END -->
  </el-header>
</div>

</template>

<script>
import {mapActions} from "vuex";

export default {
  data() {
    return {
      activeIndex: "", // 头部导航栏选中的标签
      search: "", // 搜索条件
      register: false, // 是否显示注册组件
      visible: false // 是否退出登录
    };
  },
  methods:{
    ...mapActions(["setUser", "setShowLogin", "setShoppingCart"]),
    // 接收注册子组件传过来的数据
    isRegister(val) {
      this.register = val;
    },
    searchClick() {
      if (this.search != "") {
        // 跳转到全部商品页面,并传递搜索条件
        this.$router.push({ path: "/goods", query: { search: this.search } });
        this.search = "";
      }
    }
  }
}
</script>

<style scoped>
/* 顶栏容器CSS */
.el-header .el-menu {
  max-width: 1225px;
  margin: 0 auto;
}
.el-header .logo {
  height: 60px;
  width: 189px;
  float: left;
  margin-right: 100px;
}
.el-header .so {
  margin-top: 10px;
  width: 300px;
  float: right;
}
/* 顶栏容器CSS END */
</style>