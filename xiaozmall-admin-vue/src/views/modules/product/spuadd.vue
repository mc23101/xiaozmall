<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-steps :active="step" finish-status="success">
          <el-step title="基本信息"></el-step>
          <el-step title="规格参数"></el-step>
          <el-step title="销售属性"></el-step>
          <el-step title="SKU信息"></el-step>
          <el-step title="保存完成"></el-step>
        </el-steps>
      </el-col>
      <el-col :span="24" v-show="step===0">
        <spu-info></spu-info>
      </el-col>
      <el-col :span="24" v-show="step===1">
        <product-attr :base-info="baseInfo" :step="step"></product-attr>
      </el-col>
      <el-col :span="24" v-show="step===2">
        <sale-attr :base-info="baseInfo" :step="step"></sale-attr>
      </el-col>
      <el-col :span="24" v-show="step===3">
          <sku-info :base-info="baseInfo" :spu-attrs="this.spuAttrs" :sale-attrs="this.saleAttrs" :step="step"></sku-info>
      </el-col>
      <el-col :span="24" v-show="step===4">
        <el-card class="box-card" style="width:80%;margin:20px auto">
          <h1>保存成功</h1>
          <el-button type="primary" @click="addAgian">继续添加</el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SpuInfo from './spuadd/spuInfo'
import ProductAttr from './spuadd/productAttr'
import PubSub from 'pubsub-js'
import saleAttr from './spuadd/saleAttr'
import skuInfo from './spuadd/skuInfo'

export default {
  // eslint-disable-next-line standard/object-curly-even-spacing
  components: {SpuInfo, ProductAttr, saleAttr, skuInfo},
  watch: {
    // step (val) {
    //   console.log('step:', val)
    // },
    // baseInfo (val) {
    //   console.log('baseInfo', val)
    // },
    // spuAttrs (val) {
    //   console.log('spuAttrs', val)
    // }
    saleAttrs (val) {
      console.log('saleAttrs', val)
    }
  },

  data () {
    return {
      spuAttrsSub: '',
      baseInfoSub: '',
      saleAttrSub: '',
      stepSub: '',
      step: 0,
      baseInfo: [],
      spuAttrs: [],
      saleAttrs: []
    }
  },
  mounted () {
    this.baseInfoSub = PubSub.subscribe('baseInfo', (msg, val) => {
      this.baseInfo = val
    })
    this.stepSub = PubSub.subscribe('stepChange', (msg, val) => {
      this.step = val
    })
    this.spuAttrsSub = PubSub.subscribe('spuAttrs', (msg, val) => {
      this.spuAttrs = val
    })
    this.saleAttrSub = PubSub.subscribe('saleAttrs', (msg, val) => {
      this.saleAttrs = val
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.baseInfoSub)
    PubSub.unsubscribe(this.stepSub)
    PubSub.unsubscribe(this.spuAttrsSub)
    PubSub.unsubscribe(this.saleAttrSub)
  }
}
</script>

<style scoped>

</style>
