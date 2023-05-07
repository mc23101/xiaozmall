<template>
  <div class="mod-config">
    <el-form :inline="true" :model="brandDataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="brandDataForm.pageParamVo.key" placeholder="品牌名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchBrand">查询</el-button>
        <el-button
          type="primary"
          @click="addOrUpdateHandle()"
        >新增</el-button>
        <el-button
          type="danger"
          @click="deleteHandle()"
          :disabled="this.brandDataList.dataListSelections&&this.brandDataList.dataListSelections.length <= 0"
        >批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="brandDataList.list"
      border
      :load="getDataListLoading"
      @selection-change="brandSelectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="brandId" header-align="center" align="center" label="品牌id"></el-table-column>
      <el-table-column prop="name" header-align="center" align="center" label="品牌名"></el-table-column>
      <el-table-column prop="logo" header-align="center" align="center" label="品牌logo地址">
        <template slot-scope="scope">
          <el-image
              style="width: 106px; height: 32px"
              :src="scope.row.logo"
          fit="fill"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="descript" header-align="center" align="center" label="介绍"></el-table-column>
      <el-table-column prop="showStatus" header-align="center" align="center" label="显示状态">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.showStatus"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0"
            @change="updateBrand(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="firstLetter" header-align="center" align="center" label="检索首字母"></el-table-column>
      <el-table-column prop="sort" header-align="center" align="center" label="排序"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="250" label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="updateCatalogHandle(scope.row.brandId)">关联分类</el-button>
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.brandId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.brandId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="brandSizeChangeHandle"
      @current-change="brandCurrentChangeHandle"
      :current-page="brandDataForm.pageParamVo.pageIndex"
      :page-sizes="[5,10, 20, 50, 100]"
      :page-size="brandDataForm.pageParamVo.pageSize"
      :total="brandDataList.totalCount"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

    <el-dialog title="关联分类" :visible.sync="cateRelationDialogVisible" width="40%">
      <el-popover placement="right-end" v-model="popCatalogSelectVisible">
        <category-cascader :catalogPath.sync="catalogPath"></category-cascader>
        <div style="text-align: right; margin: 0">
          <el-button size="mini" type="text" @click="popCatalogSelectVisible = false">取消</el-button>
          <el-button type="primary" size="mini" @click="addCatalogSelect">确定</el-button>
        </div>
        <el-button slot="reference">新增关联</el-button>
      </el-popover>
      <el-table :data="brandCatalogRelationDataList.list" style="width: 100%">
        <el-table-column prop="id" label="#"></el-table-column>
        <el-table-column prop="brandName" label="品牌名"></el-table-column>
        <el-table-column prop="catalogName" label="分类名"></el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" label="操作">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="deleteCateRelationHandle(scope.row.id,scope.row.brandId)"
            >移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="relationSizeChangeHandle"
        @current-change="relationCurrentChangeHandle"
        :current-page="brandCatalogRelationDataForm.pageParamVo.pageIndex"
        :page-sizes="[5,10, 20, 50, 100]"
        :page-size="brandCatalogRelationDataForm.pageParamVo.pageSize"
        :total="brandCatalogRelationDataList.totalCount"
        layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cateRelationDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="cateRelationDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import AddOrUpdate from './brand-add-or-update'
import CategoryCascader from '../common/category-cascader'
import PubSub from 'pubsub-js'
export default {
  data () {
    return {
      // 品牌信息数据
      brandDataForm: {
        brandVo: {
          brandId: null,
          name: null,
          logo: null,
          descript: null,
          showStatus: null,
          firstLetter: null,
          sort: null
        },
        pageParamVo: {
          pageIndex: 1,
          pageSize: 20,
          key: null
        }
      },
      brandDataList: {
        list: [],
        totalPage: 0,
        totalCount: 0,
        dataListSelections: []
      },

      // 品牌分类关联数据
      // 查询参数
      brandCatalogRelationDataForm: {
        catalogBrandRelationVo: {
          id: null,
          brandId: null,
          catalogId: null,
          brandName: null,
          catalogName: null
        },
        pageParamVo: {
          pageIndex: 1,
          pageSize: 5,
          key: null
        }
      },
      // 返回结果
      brandCatalogRelationDataList: {
        list: [],
        totalPage: 0,
        totalCount: 0,
        dataListSelections: []
      },
      catalogPath: [],

      // 组件显示
      dataListLoading: false,
      addOrUpdateVisible: false,
      cateRelationDialogVisible: false,
      popCatalogSelectVisible: false
    }
  },
  components: {
    AddOrUpdate,
    CategoryCascader
  },
  methods: {
    searchBrand () {
      this.brandDataForm.pageParamVo.pageIndex = 1
      this.getDataList()
    },
    getDataListLoading () {
      return this.dataListLoading
    },
    addCatalogSelect () {
      this.popCatalogSelectVisible = false
      new Promise((resolve, reject) => {
        this.$http({
          url: this.$http.adornUrl('/product/product/categorybrandrelation/save'),
          method: 'post',
          data: this.$http.adornData({brandId: this.brandCatalogRelationDataForm.catalogBrandRelationVo.brandId, catalogId: this.catalogPath[this.catalogPath.length - 1]}, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message.success('关联成功')
          } else {
            this.$message.error(data.msg)
          }
          resolve()
        })
      }).then(() => {
        this.getCateRelation()
      })
    },
    deleteCateRelationHandle (id, brandId) {
      this.$http({
        url: this.$http.adornUrl('/product/product/categorybrandrelation/delete'),
        method: 'delete',
        data: this.$http.adornData([id], false)
      }).then(({ data }) => {
        this.getCateRelation()
      })
    },
    updateCatalogHandle (brandId) {
      this.cateRelationDialogVisible = true
      this.brandCatalogRelationDataForm.catalogBrandRelationVo.brandId = brandId
      console.log(this.brandCatalogRelationDataForm)
      this.getCateRelation()
    },
    getCateRelation () {
      this.$http({
        url: this.$http.adornUrl(`/product/product/categorybrandrelation/list`),
        method: 'post',
        data: this.$http.adornData(this.brandCatalogRelationDataForm)
      }).then(({ data }) => {
        this.brandCatalogRelationDataList = data.data
        console.log(data.data)
      })
    },
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/product/product/brand/list'),
        method: 'post',
        data: this.$http.adornData(this.brandDataForm, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.brandDataList = data.data
        } else {
          this.brandDataList =
          {
            list: [],
            totalPage: 0,
            totalCount: 0
          }
        }
        this.dataListLoading = false
      })
    },
    updateBrand (data) {
      this.$http({
        url: this.$http.adornUrl(`/product/product/brand/update`),
        method: 'post',
        data: data
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.visible = false
              this.$emit('refreshDataList')
            }
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    relationSizeChangeHandle (val) {
      this.brandCatalogRelationDataForm.pageParamVo.pageSize = val
      this.brandCatalogRelationDataForm.pageParamVo.pageIndex = 1
      this.getCateRelation()
    },
    relationCurrentChangeHandle (val) {
      this.brandCatalogRelationDataForm.pageParamVo.pageIndex = val
      this.getCateRelation()
    },
    // 每页数
    brandSizeChangeHandle (val) {
      this.brandDataForm.pageParamVo.pageSize = val
      this.brandDataForm.pageParamVo.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    brandCurrentChangeHandle (val) {
      this.brandDataForm.pageParamVo.pageIndex = val
      this.getDataList()
    },
    // 多选
    brandSelectionChangeHandle (val) {
      this.brandDataList.dataListSelections = val
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
      let ids = id
        ? [id]
        : this.brandDataList.dataListSelections.map(item => {
          return item.brandId
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
          url: this.$http.adornUrl('/product/product/brand/delete'),
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
  },
  activated () {
    this.getDataList()
  }
}
</script>
