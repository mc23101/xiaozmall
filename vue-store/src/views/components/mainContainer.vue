<template>
  <div class="main">
    <div class="banner">
        <div class="slider">
          <ul>
            <li :key="count" v-for="count in 10">
              <a>{{categoryList.length!==0?categoryList[count].name:""}}</a><i class="iconfont">》</i>
              <div class="slider-list">
                <el-tabs tab-position="left" style="height: 460px;">
                  <el-tab-pane :key="l2" v-for="l2 in categoryList[count].children" :label="l2.name">
                      <el-tag class="slider-tag" :key="l3" v-for="l3 in l2.children" @click="tagClick(categoryList[count],l2,l3)">{{l3.name}}</el-tag>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </li>
          </ul>
        </div>
        <div class="image">
          <el-carousel height="460px">
            <el-carousel-item v-for="item in carousel" :key="item.id">
              <el-image style="height:100%;width: 100%" :src="item.imageUrl" :alt="item.description" @click="imageClickHandle(item)"/>
            </el-carousel-item>
          </el-carousel>
        </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      carousel: "", // 轮播图数据
      categoryList: []
    };
  },
  created() {
    this.$axios
      .post(this.$target+"/system/homerecommendation/list", {})
      .then(res => {
        console.log(res)
        this.carousel = res.data.page.list;
      })
      .catch(err => {
        return Promise.reject(err);
      });

    this.$axios
        .post(this.$target+"/product/category/list/tree", {})
        .then(res => {
          this.categoryList = res.data.data;
          console.log(this.categoryList)
        })
        .catch(err => {
          return Promise.reject(err);
        });
  },
  methods: {
    tagClick(l1,l2,l3){
      this.$router.push({name:"Goods",params:{catalogId:l1.catId+","+l2.catId+","+l3.catId}})
    },
    imageClickHandle(item){
      if(item.openType===0){
        window.location.href=item.openUrl
      }
    },
  }
};
</script>
<style>
@import "~@/assets/css/mainContainer.css";
</style>