<template>
<div class="main">
  <div class="listPanel" v-if="product.length!==0" >
    <div class="row" v-for="i in product.length" :key="i">
      <div class="r-slot" v-for="j in product[0].length" :key="j">
        <el-image style="width: 200px; height: 200px" src="https://g-search2.alicdn.com/img/bao/uploaded/i4/i2/2215120526589/O1CN01II4pix1yXoUppscXX_!!2215120526589.png_360x360Q90.jpg_.webp"></el-image>
      </div>
    </div>
  </div>
  <div class="notfound" v-if="product.length===0">
    <label>没有找到相关的宝贝</label>
  </div>
</div>
</template>

<script>
export default {
  props:{
    product:Array
  },
  data(){
    return{
    }
  }
}
</script>

<style scoped>
.listPanel{
  width: 1225px;
  height: 1450px;
  margin: auto;
  border: 2px solid #e1e1e8;
}

.notfound{
  width: 1225px;
  height: 200px;
  margin: auto;
  border: 2px solid #e1e1e8;
  font-size: 20px;
  text-align: center;
}

.row{
  height: 300px;
  margin: 20px 20px 10px 10px;
  display:flex;
  display:-webkit-flex;/*Safari*/
  justify-content:flex-start;
}

.r-slot{
  width: 200px;
  height: 300px;
  margin: auto;
  border: 1px solid #e1e1e8;
}
</style>