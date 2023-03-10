<template>
  <div>
    <el-select placeholder="请选择" v-model="sku" :disabled="this.enable"  filterable clearable>
      <el-option
        v-for="item in allSku"
        :key="item.skuId"
        :label="item.skuName"
        :value="item"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'

export default {
  props: {
  },
  watch: {
    sku (val) {
      PubSub.publish('skuId', val)
    }
  },
  data () {
    return {
      catId: '',
      brandId: '',
      enable: true,
      spuId: '',
      sku: '',
      catSub: null,
      brandSub: null,
      spuSub: null,
      allSku: []
    }
  },
  methods: {
    getSku () {
      this.$http({
        url: this.$http.adornUrl(
          `/product/skuinfo/list/${this.catId}/${this.brandId}/${this.spuId}`
        ),
        method: 'get'
      }).then(({ data }) => {
        this.allSku = data.data
      })
    }
  },
  mounted () {
    this.catSub = PubSub.subscribe('catPath', (msg, val) => {
      this.catId = val[val.length - 1]
      if (val.length === 0) {
        this.brandId = null
        this.spuId = null
        this.skuId = null
        this.allSku = []
        this.enable = true
      }
    })
    this.brandSub = PubSub.subscribe('brandId', (msg, val) => {
      this.brandId = val
      if (val === null) {
        this.spuId = null
        this.skuId = null
        this.allSku = []
        this.enable = true
      }
    })
    this.spuSub = PubSub.subscribe('spuId', (msg, val) => {
      this.spuId = val
      if (val === null) {
        this.skuId = null
        this.allSku = []
        this.enable = true
        return
      }
      this.enable = false
      this.getSku()
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.brandSub)
    PubSub.unsubscribe(this.catSub)
    PubSub.unsubscribe(this.spuSub)
  }
}
</script>

<style scoped>

</style>
