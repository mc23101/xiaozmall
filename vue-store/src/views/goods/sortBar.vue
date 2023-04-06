<template>
  <div class="sortBar">
    <div class="sort-row">
      <ul class="sorts">
        <li class="sort">
          <a :class="dataForm.state===0?'active':'common'" @click="dataForm.state=0">{{dataForm.state===0?'综合排序':'综合'}}</a>
        </li>
        <li class="sort">
          <a :class="dataForm.state===1?'active':'common'" @click="dataForm.state=1">{{dataForm.state===1?'销量从高到低':'销量'}}</a>
        </li>
      </ul>
      <div class="prices">
        <ul>
          <li class="item">
            <input placeholder="¥" v-model="sortData.min">
          </li>
          <li class="step">-</li>
          <li class="item">
            <input placeholder="¥" v-model="sortData.max">
          </li>
        </ul>
        <div class="input-hover">
          <ul>
            <li class="item">
              <input placeholder="¥" v-model="dataForm.min">
            </li>
            <li class="step">-</li>
            <li class="item">
              <input placeholder="¥" v-model="dataForm.max">
            </li>
            <li class="item">
              <button class="submit" @click="setMinMax()">确定</button>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="filter-row">
      <el-checkbox  v-model="sortData.BaoYou">包邮</el-checkbox>
      <el-checkbox v-model="sortData.XinPin">新品</el-checkbox>
      <el-checkbox v-model="sortData.YiWaiXian">赠送运费退货险</el-checkbox>
      <el-checkbox v-model="sortData.GongYi">公益宝贝</el-checkbox>
      <el-checkbox v-model="sortData.EShou">二手</el-checkbox>
      <el-checkbox v-model="sortData.ZhengPin">正品保证</el-checkbox>
      <el-checkbox v-model="sortData.QiTianTuiHuo">7+天内退货</el-checkbox>
      <el-checkbox v-model="sortData.HaiWai">海外商品</el-checkbox>
    </div>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'
export default {
  watch:{
    'dataForm.state':function (val){
      this.sortData.sortType=val
    }
  },
  data() {
    return {
      dataForm:{
        min: '',
        max: '',
        state:0,
      },
      sortData:{
        min:'',
        max:'',
        sortType:'',
        BaoYou: false,
        XinPin: false,
        YiWaiXian:false,
        GongYi:false,
        EShou:false,
        ZhengPin:false,
        QiTianTuiHuo:false,
        HaiWai:false
      }
    };
  },
  methods:{
    setMinMax(){
      this.sortData.min=this.dataForm.min
      this.sortData.max=this.dataForm.max
      PubSub.publish('sortUpdate',this.sortData)
    }
  }
}
</script>

<style scoped>
.sortBar{
  position: relative;
  margin: 15px auto;
  height: 80px;
  width: 1225px;
  background-color: #2f2f2f;
}
.sort-row{
  height: 40px;
  background-color: #f5f5f5;
  border: solid 1px #e8e8e8;
}
.filter-row{
  height: 40px;
  background-color: #FFFFFF;
  border: 1px solid #E8E8E8;
  border-top: none;
}
.sorts{
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:space-between;
  float: left;
  line-height:40px;
  font-size: 14px;
}

.sort{
  float: left;
}

.sort a{
  float: left;
  display: block;
  height: 40px;
  margin-left: -1px;
  padding: 0 19px;
  border-left: solid 1px #f5f5f5;
  border-right: solid 1px #f5f5f5;
  text-decoration: none;
  color: #6d6d6d;
}

.sort a:active,.sort a:hover {
  position: relative;
  z-index: 1;
  background-color: #fff;
  border-color: #e5e5e5;
  color: #f50;
}

.sort .active{
  position: relative;
  z-index: 1;
  background-color: #fff;
  border-color: #e5e5e5;
  color: #f50;
}

.prices{
  float: left;
  height: 40px;
  width: 130px;
}

ul{
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:flex-start;
}

.prices .item{
  padding: 8px 0 0 10px;
}

.prices .item>input{
  float: left;
  width: 40px;
  padding: 0 5px;
  height: 20px;
  border: solid 1px #dfdfdf;
  line-height: normal;
  font-family: arial,serif;
}

.prices>.input-hover{
  height: 40px;
  width: 195px;
  position: relative;
  border: 1px solid #e0e0e0;
  background-color: #FFFFFF;
  border-left: none;
  box-sizing: border-box;
  top: -29px;
  display: none;
}

.prices:hover>.input-hover{
  display: block;
}

.step{
  float: left;
  width: 10px;
  padding: 8px 0 0 5px;
  text-align: center;
}

.submit{
  float: left;
  height: 20px;
  width: 40px;
  background-color: red;
  border: 1px solid #e0e0e0;
  color: #FFFFFF;
}

.submit:hover{
  background: #b31d28;
}

.el-checkbox{
  margin-top: auto;
  margin-bottom: auto;
}

.filter-row{
  padding: 0 20px;
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:flex-start;
}

</style>