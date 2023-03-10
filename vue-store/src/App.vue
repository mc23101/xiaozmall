<!--
 * @Description: 项目根组件
 * @Author: hai-27
 * @Date: 2020-02-07 16:23:00
 * @LastEditors: hai-27
 * @LastEditTime: 2020-04-05 13:14:48
 -->
<template>
  <div id="app" name="app">
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>
<script>


export default {

};
</script>

<style>
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


</style>