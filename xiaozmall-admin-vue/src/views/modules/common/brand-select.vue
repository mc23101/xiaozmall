<template>
  <div>
    <el-select placeholder="请选择" v-model="brandId" :disabled="this.enable" @click.native="BrandSelectClick" filterable clearable>
      <el-option
        v-for="item in brands"
        :key="item.brandId"
        :label="item.name"
        :value="item.brandId"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'

export default {
  components: {},
  props: {
    enable: true,
  },
  data () {
    return {
      catId: 0,

      brands: [
        {
          label: 'a',
          value: 1
        }
      ],
      brandId: '',
      subscribe: null
    }
  },
  computed: {},
  // 监控data中的数据变化
  watch: {
    brandId (val) {
      PubSub.publish('brandId', val)
    }
  },
  // 方法集合
  methods: {
    BrandSelectClick () {
      console.log(this.catId)
    },
    getCatBrands () {
      this.$http({
        url: this.$http.adornUrl('/product/categorybrandrelation/brands/list'),
        method: 'get',
        params: this.$http.adornParams({
          catId: this.catId
        })
      }).then(({ data }) => {
        this.brands = data.data
        console.log(this.brands)
      })
    }
  },
  created () {
    this.getCatBrands()
  },
  mounted () {
    // 监听三级分类消息的变化
    this.subscribe = PubSub.subscribe('catPath', (msg, val) => {
      if (val.length === 0) {
        this.brands = []
        return
      }
      this.catId = val[val.length - 1]
      this.getCatBrands()
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.subscribe) // 销毁订阅
  }
}
</script>
<style scoped>
</style>
