<template>
<div class="AttrFilter">
  <div class="row row-catalog" v-if="searchResult.catalogs!==undefined&&searchResult.catalogs.length!==0">
    <div class="head">
      <h4>
        <span>分类</span>
        :
      </h4>
    </div>
    <div class="body">
      <div class="items">
        <a class="item" v-for="(catalog,catalogIndex) in searchResult.catalogs" :key="catalogIndex">{{catalog.name}}</a>
      </div>
    </div>
    <div class="footer">

    </div>
  </div>
  <div class="row row-brand" v-if="searchResult.brands!==undefined&&searchResult.brands.length!==0">
    <div class="head">
      <h4>
        <span>品牌</span>
        :
      </h4>
    </div>
    <div class="body">
      <div class="items">
        <a class="item" v-for="(brand,brandIndex) in searchResult.brands" :key="brandIndex">{{brand.name}}</a>
      </div>
    </div>
  </div>
  <div class="row row-spuAttr" v-for="(spuAttr,spuAttrIndex) in spuAttrs" :key="spuAttrIndex">
    <div class="head">
      <h4>
        <span>{{spuAttr.attrName}}</span>
        :
      </h4>
    </div>
    <div class="body">
      <div class="items">
        <a class="item" v-for="(attrValue,valueIndex) in spuAttr.valueSelect.split(';')" :key="valueIndex" @click="pushSpuAttr(spuAttr,attrValue)">{{attrValue}}</a>
      </div>
    </div>
  </div>
  <div class="row row-skuAttr" v-for="(skuAttr,skuAttrIndex) in skuAttrs" :key="skuAttrIndex">
    <div class="head">
      <h4>
        <span>{{skuAttr.attrName}}</span>
        :
      </h4>
    </div>
    <div class="body">
      <div class="items">
        <a class="item" v-for="(attrValue,valueIndex) in skuAttr.valueSelect.split(';')" :key="valueIndex" @click="pushSkuAttr(skuAttr,attrValue)">{{attrValue}}</a>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import PubSub from "pubsub-js";

export default {
  props:{
    searchResult:{
      catalogs:[],
      brands:[],
      skuAttrs:[]
    }
  },
  watch:{
    searchResult(){
      this.getSpuAttrs()
      this.getSkuAttrs()
    }
  },
  data(){
    return{
      spuAttrs:Array,
      skuAttrs:Array,
      attrCount:0
    }
  },
  methods:{
    pushSkuAttr(attr,value){
      PubSub.publish("pushSkuAttr",{attrName:attr.attrName,attrId:attr.attrId,attrValue:value})
    },
    pushSpuAttr(attr,value){
      PubSub.publish("pushSpuAttr",{attrName:attr.attrName,attrId:attr.attrId,attrValue:value})
    },
    getSpuAttrs(){
      this.attrCount=5
      let result=[]
      if(this.searchResult.catalogs!==undefined&&this.searchResult.catalogs.length!==0){
        this.attrCount--
      }
      if(this.searchResult.brands!==undefined&&this.searchResult.brands.length!==0){
        this.attrCount--
      }
      if(this.searchResult.spuAttrs!==undefined){
        let val=Math.min(this.attrCount,this.searchResult.spuAttrs.length)
        for(let i=0;i<val;i++){
          result.push(this.searchResult.spuAttrs[i])
        }
        this.attrCount-=val
      }
      this.spuAttrs=result
    },
    getSkuAttrs(){
      let result=[]
      if(this.searchResult.skuAttrs!==undefined){
        let val=Math.min(this.attrCount,this.searchResult.skuAttrs.length)
        for(let i=0;i<val;i++){
          result.push(this.searchResult.skuAttrs[i])
        }
      }
      this.skuAttrs=result
    }
  }
}
</script>

<style scoped>
.AttrFilter{
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
  position: relative;
  display: block;
  width: 1225px;
  margin-left: auto;
  margin-right: auto;
  border: 1px solid #e8e8e8
}

.row{
  position: relative;
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:flex-start;
  border-top: 1px dashed #dedede;
  margin: 0 8px;
}
.AttrFilter .row .body .items{
  overflow: hidden;
}

.AttrFilter .row .body .items .item{
  position: relative;
  float: left;
  margin: 9px 40px 9px 0;
  height: 18px;
  color: #000;
  text-decoration: none;
}

.AttrFilter .row .body  .items .item:hover{
  color: red;
}

.row:first-child{
  border-top: none;
}
.row .body{
  font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
  position: relative;
  padding: 0 100px 0 25px;
}

.row .head{
  position: relative;
  width: 120px;
  left: 11px;
  top: 9px;
  color: #999;
}
div{
  display: block;
}
</style>