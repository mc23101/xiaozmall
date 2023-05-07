<template>
  <div>
    <el-select placeholder="请选择" v-model="brandIdValue"  :disabled="this.enable" @click.native="BrandSelectClick" filterable clearable>
      <el-option
        v-for="(item,index) in brands"
        :key="index"
        :label="item.brandName"
        :value="item.brandId"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    brandId: {
      type: Number,
      default () {
        return undefined
      }
    },
    catId: {
      type: Number,
      default () {
        return undefined
      }
    }
  },
  data () {
    return {
      enable: true,
      brandIdValue: this.brandId,
      brands: [
        {
          label: 'a',
          value: 1
        }
      ]
    }
  },
  computed: {},
  // 监控data中的数据变化
  watch: {
    brandId (v) {
      this.brandIdValue = this.brandId
    },
    brandIdValue (v) {
      this.$emit('update:brandId', v)
    },
    catId (val) {
      if (this.catId && this.catId !== 0) {
        this.enable = false
      } else {
        this.brandIdValue = undefined
        this.enable = true
      }
      this.getCatBrands()
    }
  },
  // 方法集合
  methods: {
    BrandSelectClick () {
      console.log(this.catId)
    },
    getCatBrands () {
      this.$http({
        url: this.$http.adornUrl('/product/product/categorybrandrelation/list'),
        method: 'post',
        data: this.$http.adornData(
          {
            catalogBrandRelationVo: {
              catalogId: this.catId
            }
          }
        )
      }).then(({ data }) => {
        this.brands = data.data.list
        console.log(this.brands )
      })
    }
  },
  created () {
    this.getCatBrands()
  },
  mounted () {}
}
</script>
<style scoped>
</style>
