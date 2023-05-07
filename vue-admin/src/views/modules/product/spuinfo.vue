<template>
  <div class="mod-config">
    <el-table
      :data="dataList.list"
      border
      :load="getDataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="id" header-align="center" align="center" label="id"></el-table-column>
      <el-table-column prop="spuName" header-align="center" align="center" label="名称"></el-table-column>
      <el-table-column prop="spuDescription" header-align="center" align="center" label="描述"></el-table-column>
      <el-table-column prop="catalogId" header-align="center" align="center" label="分类"></el-table-column>
      <el-table-column prop="brandId" header-align="center" align="center" label="品牌"></el-table-column>
      <el-table-column prop="weight" header-align="center" align="center" label="重量"></el-table-column>
      <el-table-column prop="publishStatus" header-align="center" align="center" label="上架状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.publishStatus === 0">已下架</el-tag>
          <el-tag v-if="scope.row.publishStatus === 1">已上架</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" header-align="center" align="center" label="创建时间"></el-table-column>
      <el-table-column prop="updateTime" header-align="center" align="center" label="修改时间"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.publishStatus === 0"
            type="text"
            size="small"
            @click="setPublishStatus(scope.row.id,1)"
          >上架</el-button>
          <el-button
            v-if="scope.row.publishStatus === 1"
            type="text"
            size="small"
            @click="setPublishStatus(scope.row.id,0)"
          >下架</el-button>
          <el-button type="text" size="small" @click="attrUpdateShow(scope.row)">规格</el-button>
          <el-button type="text" size="small" @click="attrUpdateShow(scope.row)">新增sku</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="dataForm.pageParams.pageIndex"
      :page-sizes="[5,10, 20, 50, 100]"
      :page-size="dataForm.pageParams.pageSize"
      :total="dataList.totalCount"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
  </div>
</template>

<script>
import PubSub from 'pubsub-js'
export default {
  data () {
    return {
      dataSub: null,
      dataForm: {
        spuInfoVo: {
          id: undefined,
          catalogId: undefined,
          brandId: undefined
        },
        pageParams: {
          pageIndex: 1,
          pageSize: 5,
          key: ''
        }
      },
      dataList: {
        totalCount: 0,
        pageSize: 0,
        totalPage: 0,
        currPage: 0,
        list: []
      },
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      addOrUpdateVisible: false
    }
  },
  props: {
    dataListSelections: {
      type: Array,
      default: []
    }
  },
  components: {},
  activated () {
    this.getDataList()
  },
  methods: {
    handleCommand (event) {

    },
    getDataListLoading () {
      return this.dataListLoading
    },
    setPublishStatus (id, val) {
      console.log(val === 0 ? 'down' : 'up')
      this.$http({
        url: this.$http.adornUrl(`/product/product/spuinfo/${val === 0 ? 'down' : 'up'}/${id}`),
        method: 'post'
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.getDataList()
            }
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    attrUpdateShow (row) {
      this.$router.push({
        path: '/product-attrupdate',
        query: { spuId: row.id, catalogId: row.catalogId }
      })
    },
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/product/product/spuinfo/list'),
        method: 'post',
        data: this.$http.adornData(this.dataForm)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data
        } else {
          this.dataList = {}
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle (id) {}
  },
  mounted () {
    this.dataSub = PubSub.subscribe('spuList', (msg, val) => {
      this.dataForm = val
      this.pageIndex = 1
      this.getDataList()
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.dataSub)
  }
}
</script>
