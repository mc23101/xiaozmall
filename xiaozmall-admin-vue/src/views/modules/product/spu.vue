<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="dataForm">
          <el-form-item label="分类">
            <category-cascader :catalogPath.sync="catalogPath"></category-cascader>
          </el-form-item>
          <el-form-item label="品牌">
            <brand-select style="width:160px"></brand-select>
          </el-form-item>
          <el-form-item label="状态">
            <el-select style="width:160px" v-model="dataForm.status" clearable>
              <el-option label="下架" :value="0"></el-option>
              <el-option label="上架" :value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检索">
            <el-input style="width:160px" v-model="dataForm.key" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="searchSpuInfo">查询</el-button>
            <el-button
              v-if="isAuth('product:brand:delete')"
              type="danger"
              @click="deleteHandle()"
              :disabled="dataListSelections.length <= 0"
            >批量删除</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="24">
        <spuinfo :catId="catId"></spuinfo>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'
import CategoryCascader from '../common/category-cascader'
import BrandSelect from '../common/brand-select'
import Spuinfo from './spuinfo'
export default {
  // import引入的组件需要注入到对象中才能使用
  components: { CategoryCascader, Spuinfo, BrandSelect },
  props: {},
  data () {
    // 这里存放数据
    return {
      catId: 0,
      catalogPath: [],
      dataListSelections: [],
      dataForm: {
        status: '',
        key: '',
        brandId: 0,
        catalogId: 0
      },
      catPathSub: null,
      brandIdSub: null,
      selectSub: null
    }
  },
  computed: {},
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    deleteHandle () {
      let ids = []
      this.dataListSelections.forEach((data) => {
        ids.push(data.id)
      })
      this.$confirm('将要删除所选spu，删除后无法恢复，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/spuinfo/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          if (data.code === 0) {
            this.$message({
              type: 'success',
              message: '删除spu商品成功!'
            })
            PubSub.publish('dataForm', this.dataForm)
          } else {
            this.$message({
              type: 'error',
              message: '保存失败，原因【' + data.msg + '】'
            })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
    },
    searchSpuInfo () {
      console.log('搜索条件', this.dataForm)
      this.dataForm.catalogId = this.catalogPath[this.catalogPath.length - 1]
      PubSub.publish('dataForm', this.dataForm)
    }
  },
  created () {},
  mounted () {
    this.catPathSub = PubSub.subscribe('catPath', (msg, val) => {
      this.dataForm.catalogId = val[val.length - 1]
    })
    this.brandIdSub = PubSub.subscribe('brandId', (msg, val) => {
      this.dataForm.brandId = val
    })
    this.selectSub = PubSub.subscribe('selectionChange', (msg, val) => {
      this.dataListSelections = val
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.catPathSub)
    PubSub.unsubscribe(this.brandIdSub)
    PubSub.unsubscribe(this.selectSub)
  }
}
</script>
<style scoped>
</style>
