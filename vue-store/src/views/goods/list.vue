<template>
<div class="list">
  <div  v-if="SearchResult.products!==undefined">
    <div class="listPanel" v-if="SearchResult.products.length!==0" >
      <div class="row" v-for="i in Math.min(row,Math.ceil(SearchResult.products.length/col))" :key="i">
        <div class="r-slot" v-for="j in Math.min(SearchResult.products.length-((i-1)*col),col)" :key="j">
          <div class="imageBox">
            <el-image :src="SearchResult.products[(i-1)*row+j-1].images[0].imgUrl"></el-image>
          </div>
          <div class="textBox">
            <div class="text-row">
              <div class="price">
                <span>¥</span>
                <strong>{{SearchResult.products[(i-1)*row+j-1].price.toFixed(2)}}</strong>
              </div>
              <div class="sellCount">
                <a>300+人付款</a>
              </div>
            </div>
            <div class="descript">
              {{SearchResult.products[(i-1)*row+j-1].spuDescription}}
            </div>
          </div>
        </div>

      </div>
      <div class="page">
        <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            layout="prev, pager, next"
            :total="SearchResult.count"
            :page-size="this.pageSize"
            :current-page="this.pageIndex"
        >
        </el-pagination>
      </div>
    </div>
    <div class="notfound" v-if="SearchResult.products.length===0">
      <label>没有找到相关的宝贝</label>
    </div>
  </div>
</div>
</template>

<script>
import PubSub from "pubsub-js";

export default {
  props:{
    SearchResult:{
    }
  },
  data(){
    return{
      pageSize:20,
      pageIndex:1,
      row:5,
      col:4
    }
  },
  methods:{
    handleSizeChange(){
      PubSub.publish("pageChange",{pageSize:this.pageSize,pageIndex:this.pageIndex})
    },
    handleCurrentChange(){
      PubSub.publish("pageChange",{pageSize:this.pageSize,pageIndex:this.pageIndex})
    }
  }
}
</script>

<style scoped>
.list{
  position: relative;
}
.listPanel{
  width: 1225px;
  margin: auto;
  border: 2px solid #e1e1e8;
}

.notfound{
  width: 1225px;
  height: 600px;
  margin: auto;
  border: 2px solid #e1e1e8;
  font-size: 20px;
  text-align: center;
}

.page{
  margin-top: 20px;
  margin-bottom: 20px;
  text-align:center
}

.row{
  height: 320px;
  margin: 20px 20px 10px 10px;
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:flex-start;
}

.imageBox{
  height: 200px;
  width: 200px;
}

.textBox{
  width: 200px;
  height: 100px;
}


.r-slot{
  float: left;
  width: 200px;
  height: 320px;
  margin-left: 20px;
  border: 2px solid #e1e1e8;
}

.r-slot:hover{
  border: 2px solid red;
}

.price{
  float: left;
  font-size: 18px;
}
.sellCount{
  float: right;
  margin-top: auto;
}
.sellCount a{
  font-size: 10px;
  color: #888;
}

.text-row{
  margin-top: 10px;
  margin-left: 5px;
  width: 190px;
  height: 20px;
  zoom: 1;
  line-height: 22px;
}

.price span{
  color: #F40;
}
.price strong{
  color: #F40;
  font-weight: 700;
  font-family: arial,serif;
}
.descript{
  word-break:break-all;
  overflow: hidden;
  width: 170px;
  height: 40px;
  margin-left: 10px;
  margin-right: 10px;
  padding-top: 15px;
  font-size: 14px;
}
</style>