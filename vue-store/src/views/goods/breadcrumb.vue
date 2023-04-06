<template>
<div class="breadcrumb">
  <div class="crumb-item">
    <a>全部结果</a>
  </div>
  <i class="crumb-arrow" v-if="searchParam.catalogId!=null">></i>

  <!-- 分类-->
  <div class="crumb-item" v-if="searchParam.catalogId!=null">
    <div class="menu-drop">
      <div class="trigger">
        <a>{{dataResp.catalogMap[searchParam.catalogId].name}}</a>
        <div class="menu-drop-arrow"></div>
      </div>
      <div class="menu-drop-main">
        <ul>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li><li>电脑</li>
          <li>电脑</li><li>电脑</li><li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li><li>电脑</li><li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
          <li>电脑</li><li>电脑</li><li>电脑</li>
          <li>电脑</li><li>电脑</li>
          <li>电脑</li>
          <li>电脑</li><li>电脑</li>
          <li>电脑</li>
          <li>电脑</li>
        </ul>
      </div>
    </div>
  </div>

  <div v-for="(spuAttr,spuAttrIndex) in searchParam.spuAttrs" :key="spuAttrIndex">
    <i class="crumb-arrow">></i>

    <a class="crumb-select-item">
      <b>{{spuAttr.attrName}}:</b>
      <em>{{spuAttr.attrValue}}</em>
      <i @click="removeSpuAttr(spuAttr)"></i>
    </a>
  </div>

  <div v-for="(skuAttr,skuAttrIndex) in searchParam.skuAttrs" :key="skuAttrIndex">
    <i class="crumb-arrow">></i>

    <a class="crumb-select-item">
      <b>{{skuAttr.attrName}}:</b>
      <em>{{skuAttr.attrValue}}</em>
      <i @click="removeSkuAttr(skuAttr)"></i>
    </a>
  </div>


  <div v-if="searchParam.keyword">
    <i class="crumb-arrow">></i>
    <a>{{searchParam.keyword}}</a>
  </div>
</div>
</template>

<script>
import PubSub from "pubsub-js";

export default {
  props:{
    dataResp:{},
    searchParam:{}
  },
  methods:{
    removeSkuAttr(attr){
      let skuAttrs=[]
      this.searchParam.skuAttrs.forEach(attrIn=>{
        if(attr.attrId!==attrIn.attrId && attr.attrName !== attrIn.attrName && attr.attrValue!==attrIn.attrValue){
          skuAttrs.push(attrIn)
        }
        this.searchParam.skuAttrs=skuAttrs
        PubSub.publish("changeParam",this.searchParam)
      })
    },
    removeSpuAttr(attr){
      let spuAttrs=[]
      this.searchParam.spuAttrs.forEach(attrIn=>{
        if(attr.attrId!==attrIn.attrId && attr.attrName !== attrIn.attrName && attr.attrValue!==attrIn.attrValue){
          spuAttrs.push(attrIn)
        }
        this.searchParam.spuAttrs=spuAttrs
        PubSub.publish("changeParam",this.searchParam)
      })
    }
  }
}
</script>

<style scoped>
em {
  font-style: normal;
  color: red;
}
a:-webkit-any-link {
  color: -webkit-link;
  cursor: pointer;
  text-decoration: underline;
}
a{
  margin-top: auto;
  padding: 0;
  float: left;
  font-family: "Helvetica Neue","Hiragino Sans GB",SimSun,serif;
  font-style: normal;
  font-size: 10px;
  color: #666;
  text-decoration: none;
}
/*crumb-item样式*/
.crumb-item{
  float: left;
  height: 20px;
  margin-right: 5px;
  margin-top: auto;
}
.crumb-arrow{
  float: left;
  margin-right: 5px;
  margin-top: auto;
  font-family: "Helvetica Neue","Hiragino Sans GB",SimSun,serif;
  font-style: normal;
}
.breadcrumb{
  position: relative;
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:flex-start;
  width: 1225px;
  height: 20px;
  line-height: 20px;
  margin: auto;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 25px;
}

.menu-drop{
  height: 20px;
}
.menu-drop-arrow{
  display: inline-block;
  width: 20px;
  height: 20px;
  vertical-align: top;
  background: url("//storage.jd.com/retail-mall/search_list/pc/product/search/0.0.3/css/i/search.ele.png") no-repeat 4px 7px;
}
.trigger:hover .menu-drop-arrow{
  background-position: 4px -44px;
}
.trigger:hover>a{
  color: red;
}
.menu-drop-main .trigger:hover{
  display: block;
}
.trigger{
  display: block;
  padding: 0 4px 0 8px;
  border: 1px solid #ddd;
  line-height: 20px;
  vertical-align: top;
  background-color: #fff;
}
.trigger:hover +.menu-drop-main{
  display: inline-block;
}
.menu-drop-main ul{
  display: inline-block;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0;
  margin-inline-end: 0;
  padding-inline-start: 10px;
}
.menu-drop-main ul li{
  display: list-item;
  text-align: -webkit-match-parent;
  padding: 10px;
  float: left;
  font-family: "Helvetica Neue","Hiragino Sans GB",SimSun,serif;
  font-style: normal;
  font-size: 10px;
  color: #666;
  text-decoration: none;
}
.menu-drop-main{
  display: none;
  width: 300px;
  left: auto;
  padding: 0;
  position: absolute;
  border: 1px solid #ddd;
  background-color: #fff;
  z-index: 6;
}
/*crumb-item样式结束*/



/*crumb-select-item样式开始*/
.crumb-select-item {
  position: relative;
  float: left;
  height: 22px;
  line-height: 22px;
  border: 1px solid #DDD;
  font-size: 12px;
  vertical-align: top;
  margin: 0 5px 5px 0;
  padding: 0 26px 0 4px;
  cursor: pointer;
  background: #f3f3f3;
}
.crumb-select-item b {
  font-weight: 400;
  color: #333;
}
.crumb-select-item i {
  display: block;
  position: absolute;
  width: 25px;
  height: 22px;
  right: 0;
  top: 0;
  background: url(//storage.jd.com/retail-mall/search_list/pc/product/search/0.0.3/css/i/search.ele.png) no-repeat 7px -140px;
}
.crumb-select-item:hover{
  border: 1px solid red;
}
.crumb-select-item:hover i{
  background: red url(//storage.jd.com/retail-mall/search_list/pc/product/search/0.0.3/css/i/search.ele.png) no-repeat 7px -158px;
}
</style>