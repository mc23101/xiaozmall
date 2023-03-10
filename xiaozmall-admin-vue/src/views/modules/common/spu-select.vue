<template>
  <div>
    <el-select placeholder="请选择" v-model="spuId" :disabled="this.enable"  filterable clearable>
      <el-option
        v-for="item in allSpu"
        :key="item.id"
        :label="item.spuName"
        :value="item.id"
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
    spuId (val) {
      PubSub.publish('spuId', val)
    }
  },
  data () {
    return {
      catId: 0,
      brandId: 0,
      enable: true,
      spuId: '',
      catSub: null,
      brandSub: null,
      allSpu: []
    }
  },
  methods: {
    getSpu () {
      this.$http({
        url: this.$http.adornUrl(
          `/product/spuinfo/list/${this.catId}/${this.brandId}`
        ),
        method: 'get'
      }).then(({ data }) => {
        this.allSpu = data.data
      })
    }
  },
  mounted () {
    this.catSub = PubSub.subscribe('catPath', (msg, val) => {
      this.catId = val[val.length - 1]
      if (val.length === 0) {
        this.brandId = null
        this.spuId = null
        this.allSpu = []
        this.enable = true
      }
    })
    this.brandSub = PubSub.subscribe('brandId', (msg, val) => {
      this.brandId = val
      if (val === null) {
        this.brandId = null
        this.spuId = null
        this.allSpu = []
        this.enable = true
        return
      }
      this.enable = false
      this.getSpu()
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.brandSub)
    PubSub.unsubscribe(this.catSub)
  }
}
</script>

<style scoped>

</style>
