<template>
<!--
使用说明：
1）、引入category-cascader.vue
2）、语法：<category-cascader :catalogPath.sync="catalogPath"></category-cascader>
    解释：
      catalogPath：指定的值是cascader初始化需要显示的值，应该和父组件的catalogPath绑定;
          由于有sync修饰符，所以cascader路径变化以后自动会修改父的catalogPath，这是结合子组件this.$emit("update:catalogPath",v);做的
      -->
  <div>
    <el-cascader
      filterable
      clearable
      placeholder="试试搜索：手机"
      v-model="paths"
      :options="categories"
      :props="setting"
    ></el-cascader>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'

export default {
  // import引入的组件需要注入到对象中才能使用
  components: {},
  // 接受父组件传来的值
  props: {
    catalogPath: {
      type: Array,
      default () {
        return []
      }
    }
  },
  data () {
    // 这里存放数据
    return {
      setting: {
        value: 'catId',
        label: 'name',
        children: 'children'
      },
      categories: [],
      paths: this.catalogPath
    }
  },
  watch: {
    catalogPath (v) {
      this.paths = this.catalogPath
    },
    paths (v) {
      this.$emit('update:catalogPath', v)
      PubSub.publish('catPath', v)
    }
  },
  // 方法集合
  methods: {
    // 获取商品分类树形结构
    getCategories () {
      this.$http({
        url: this.$http.adornUrl('/product/product/category/list/tree'),
        method: 'get'
      }).then(({ data }) => {
        this.categories = data.data
      })
    }
  },
  created () {
    this.getCategories()
  }
}
</script>
<style scoped>
</style>
