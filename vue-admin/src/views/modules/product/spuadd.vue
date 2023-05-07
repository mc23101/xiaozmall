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
          <sku-info :base-info="baseInfo" :spu-attr-group="this.spuAttrGroup" :sale-attrs="this.saleAttrs" :step="step"></sku-info>
      </el-col>
      <el-col :span="24" v-show="step===4">
        <el-card class="box-card" style="width:80%;margin:20px auto">
          <h1>保存成功</h1>
          <el-button type="primary" @click="addAgain()">继续添加</el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SpuInfo from './spu/spuInfo'
import ProductAttr from './spu/productAttr'
import PubSub from 'pubsub-js'
import saleAttr from './spu/saleAttr'
import skuInfo from './spu/skuInfo'

export default {
  // eslint-disable-next-line standard/object-curly-even-spacing
  components: {SpuInfo, ProductAttr, saleAttr, skuInfo},
  data () {
    return {
      spuAttrsSub: '',
      baseInfoSub: '',
      saleAttrSub: '',
      stepSub: '',
      step: 0,
      baseInfo: [],
      spuAttrGroup: [],
      saleAttrs: []
    }
  },
  methods: {
    addAgain () {
      location.reload()
      this.$router.go(0)
    }
  },
  mounted () {
    this.baseInfoSub = PubSub.subscribe('baseInfo', (msg, val) => {
      this.baseInfo = val
    })
    this.stepSub = PubSub.subscribe('stepChange', (msg, val) => {
      this.step = val
    })
    this.spuAttrsSub = PubSub.subscribe('spuAttrGroup', (msg, val) => {
      this.spuAttrGroup = val
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
