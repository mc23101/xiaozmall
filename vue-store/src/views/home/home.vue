<template>
<div>
  <topbar></topbar>
  <top-container></top-container>
  <main-container></main-container>
  <footer-container></footer-container>
</div>
</template>

<script>
import topbar from "@/views/components/topbar";
import topContainer from "@/views/components/topContainer";
import mainContainer from "@/views/components/mainContainer";
import footerContainer from "@/views/components/footerContainer";

export default {
  components:{
    topbar,topContainer,mainContainer,footerContainer
  },
  created() {
    // 获取浏览器localStorage，判断用户是否已经登录
    if (localStorage.getItem("user")) {
      // 如果已经登录，设置vuex登录状态
      this.setUser(JSON.parse(localStorage.getItem("user")));
    }
  },
  beforeUpdate() {
    this.activeIndex = this.$route.path;
  }
}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
  border: 0;
  list-style: none;
}
#app .el-header {
  padding: 0;
}
#app .el-main {
  min-height: 300px;
  padding: 20px 0;
}
#app .el-footer {
  padding: 0;
}
a,
a:hover {
  text-decoration: none;
}
/* 全局CSS END */

.el-container{
  width: 100%;
  height: 100%;
}
</style>