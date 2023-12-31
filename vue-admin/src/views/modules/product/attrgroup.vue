<template>
  <el-row>
    <el-col>
      <div class="mod-config">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
          <el-form-item>
            <category-cascader :catalog-path.sync="catalogPath"></category-cascader>
          </el-form-item>
          <el-form-item>
            <el-input v-model="dataForm.key" placeholder="组名" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="getDataLikeKey">查询</el-button>
            <el-button type="success" @click="getAllDataList()">查询全部</el-button>
            <el-button
              type="primary"
              @click="addOrUpdateHandle()"
            >新增</el-button>
            <el-button
              type="danger"
              @click="deleteHandle()"
              :disabled="dataListSelections.length <= 0"
            >批量删除</el-button>
          </el-form-item>
        </el-form>
        <el-table
          :data="dataList"
          border
          :load="getDataListLoading"
          @selection-change="selectionChangeHandle"
          style="width: 100%;"
        >
          <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
          <el-table-column prop="attrGroupId" header-align="center" align="center" label="分组id"></el-table-column>
          <el-table-column prop="attrGroupName" header-align="center" align="center" label="组名"></el-table-column>
          <el-table-column prop="sort" header-align="center" align="center" label="排序"></el-table-column>
          <el-table-column prop="descript" header-align="center" align="center" label="描述"></el-table-column>
          <el-table-column prop="icon" header-align="center" align="center" label="组图标"></el-table-column>
          <el-table-column prop="catalogId" header-align="center" align="center" label="所属分类id"></el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="relationHandle(scope.row.attrGroupId)">关联属性</el-button>
              <el-button
                type="text"
                size="small"
                @click="addOrUpdateHandle(scope.row.attrGroupId)"
              >修改</el-button>
              <el-button type="text" size="small" @click="deleteHandle(scope.row.attrGroupId)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[5,10, 20, 50, 100]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper"
        ></el-pagination>
        <!-- 弹窗, 新增 / 修改 -->
        <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

        <!-- 修改关联关系 -->
        <relation-update v-if="relationVisible" ref="relationUpdate" @refreshData="getDataList"></relation-update>
      </div>
    </el-col>
  </el-row>
</template>

<script>
/**
 * 父子组件传递数据
 * 1)、子组件给父组件传递数据，事件机制；
 *    子组件给父组件发送一个事件，携带上数据。
 * // this.$emit("事件名",携带的数据...)
 */
import Category from '../common/category'
import AddOrUpdate from './attrgroup-add-or-update'
import RelationUpdate from './attr-group-relation'
import CategoryCascader from '../common/category-cascader.vue'
import PubSub from 'pubsub-js'
export default {
  // import引入的组件需要注入到对象中才能使用
  components: {CategoryCascader, Category, AddOrUpdate, RelationUpdate },
  props: {},
  watch: {
    catalogPath (path) {
      this.catId = path[path.length - 1]
      this.getDataList()
    }
  },
  data () {
    return {
      catId: null,
      catalogPath: [],
      dataForm: {
        key: null
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      relationVisible: false
    }
  },
  activated () {
    this.getDataList()
  },
  methods: {
    getDataLikeKey () {
      this.pageIndex = 1
      this.getDataList()
    },
    getDataListLoading () {
      return this.dataListLoading
    },
    // 处理分组与属性的关联
    relationHandle (groupId) {
      this.relationVisible = true
      this.$nextTick(() => {
        this.$refs.relationUpdate.init(groupId)
      })
    },
    getAllDataList () {
      this.catId = 0
      this.dataForm.key = ''
      this.pageIndex = 1
      this.getDataList()
    },
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl(`/product/product/attrgroup/list`),
        method: 'post',
        data: this.$http.adornData(
          {
            attrGroupVo: {
              catalogId: this.catId
            },
            pageParams: {
              pageIndex: this.pageIndex,
              pageSize: this.pageSize,
              key: this.dataForm.key
            }
          }
        )
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list
          this.totalPage = data.data.totalCount
          console.log(this.dataList)
        } else {
          this.dataList = []
          this.totalPage = 0
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
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle (id) {
      var ids = id
        ? [id]
        : this.dataListSelections.map(item => {
          return item.attrGroupId
        })
      this.$confirm(
        `确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`,
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/product/attrgroup/delete'),
          method: 'delete',
          data: this.$http.adornData(ids, false)
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
      })
    }
  }
}
</script>
<style scoped>
</style>
