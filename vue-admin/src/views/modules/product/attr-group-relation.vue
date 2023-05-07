<template>
  <div>
    <el-dialog :close-on-click-modal="false" width="60%"  :visible.sync="visible" @closed="dialogClose">
      <el-dialog width="50%" title="选择属性" :visible.sync="innerVisible" append-to-body>
        <div>
          <el-form :inline="true" :model="NoRelationAttrDataForm" @keyup.enter.native="getNoRelationAttrDataList()">
            <el-form-item>
              <el-input v-model="NoRelationAttrDataForm.key" placeholder="属性名" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="getNoRelationAttrDataList()">查询</el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="NoRelationAttrDataList.list"
            border
            v-loading="NoRelationAttrDataListLoading"
            @selection-change="NoRelationAttrSelectionChangeHandle"
            style="width: 100%;"
          >
            <el-table-column type="selection" header-align="center" align="center"></el-table-column>
            <el-table-column prop="attrId" header-align="center" align="center" label="属性id"></el-table-column>
            <el-table-column prop="attrName" header-align="center" align="center" label="属性名"></el-table-column>
            <el-table-column prop="icon" header-align="center" align="center" label="属性图标"></el-table-column>
            <el-table-column prop="valueSelect" header-align="center" align="center" label="可选值列表"></el-table-column>
          </el-table>
          <el-pagination
            @size-change="NoRelationAttrSizeChangeHandle"
            @current-change="NoRelationAttrCurrentChangeHandle"
            :current-page="NoRelationAttrDataForm.pageIndex"
            :page-sizes="[5,10, 20, 50, 100]"
            :page-size="NoRelationAttrDataForm.pageSize"
            :total="NoRelationAttrDataList.totalCount"
            layout="total, sizes, prev, pager, next, jumper"
          ></el-pagination>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="innerVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitAddRelation">确认新增</el-button>
        </div>
      </el-dialog>
      <el-row>
        <el-col>
          <el-button type="primary" @click="addRelation">新建关联</el-button>
          <el-button
            type="danger"
            @click="batchDeleteRelation"
            :disabled="relationAttrDataList.dataListSelections&&relationAttrDataList.dataListSelections.length <= 0"
          >批量删除</el-button>
          <!--  -->
          <el-table
            :data="relationAttrDataList.list"
            style="width: 100%"
            v-loading="RelationAttrDataListLoading"
            @selection-change="RelationAttrSelectionChangeHandle"
            border
          >
            <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
            <el-table-column prop="attrId" label="#"></el-table-column>
            <el-table-column prop="attrName" label="属性名"></el-table-column>
            <el-table-column prop="valueSelect" label="可选值">
              <template slot-scope="scope">
                <el-tooltip v-if="scope.row.valueSelect" placement="top">
                  <div slot="content">
                    <span v-for="(i,index) in scope.row.valueSelect.split(';')" :key="index">
                      {{i}}
                      <br />
                    </span>
                  </div>
                  <el-tag>{{scope.row.valueSelect.split(";")[0]+" ..."}}</el-tag>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column fixed="right" header-align="center" align="center" label="操作">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="relationRemove(scope.row.attrId)">移除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="RelationAttrSizeChangeHandle"
            @current-change="RelationAttrCurrentChangeHandle"
            :current-page="relationAttrDataForm.pageIndex"
            :page-sizes="[5,10, 20, 50, 100]"
            :page-size="relationAttrDataForm.pageSize"
            :total="relationAttrDataList.totalCount"
            layout="total, sizes, prev, pager, next, jumper"
          ></el-pagination>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data () {
    // 这里存放数据
    return {
      attrGroupId: 0,
      relationAttrDataForm: {
        pageIndex: 1,
        pageSize: 5,
        key: null
      },
      relationAttrDataList: {
        dataListSelections: [],
        totalCount: 0,
        pageSize: 0,
        totalPage: 0,
        currPage: 0,
        list: []
      },

      NoRelationAttrDataForm: {
        pageIndex: 1,
        pageSize: 5,
        key: null
      },
      NoRelationAttrDataList: {
        dataListSelections: [],
        totalCount: 0,
        pageSize: 0,
        totalPage: 0,
        currPage: 0,
        list: []
      },
      visible: false,
      innerVisible: false,
      RelationAttrDataListLoading: false,
      NoRelationAttrDataListLoading: false
    }
  },
  computed: {},
  // 监控data中的数据变化
  watch: {},
  // 方法集合
  methods: {
    RelationAttrSelectionChangeHandle (val) {
      this.relationAttrDataList.dataListSelections = val
    },
    NoRelationAttrSelectionChangeHandle (val) {
      this.NoRelationAttrDataList.dataListSelections = val
    },
    addRelation () {
      this.getNoRelationAttrDataList()
      this.innerVisible = true
    },
    batchDeleteRelation (val) {
      let postData = []
      this.relationAttrDataList.dataListSelections.forEach(item => {
        postData.push({ attrId: item.attrId, attrGroupId: this.attrGroupId })
      })
      this.$http({
        url: this.$http.adornUrl('/product/product/attrgroup/attr/relation/delete'),
        method: 'delete',
        data: this.$http.adornData(postData, false)
      }).then(({ data }) => {
        if (data.code === 0) {
          this.$message({ type: 'success', message: '删除成功' })
          this.init(this.attrGroupId)
        } else {
          this.$message({ type: 'error', message: data.msg })
        }
      })
    },
    // 移除关联
    relationRemove (attrId) {
      let data = []
      data.push({ attrId, attrGroupId: this.attrGroupId })
      this.$http({
        url: this.$http.adornUrl('/product/product/attrgroup/attr/relation/delete'),
        method: 'delete',
        data: this.$http.adornData(data, false)
      }).then(({ data }) => {
        if (data.code === 0) {
          this.$message({ type: 'success', message: '删除成功' })
          this.init(this.attrGroupId)
        } else {
          this.$message({ type: 'error', message: data.msg })
        }
      })
    },
    submitAddRelation () {
      this.innerVisible = false
      // 准备数据
      console.log('准备新增的数据', this.NoRelationAttrDataList.dataListSelections)
      if (this.NoRelationAttrDataList.dataListSelections.length > 0) {
        let postData = []
        this.NoRelationAttrDataList.dataListSelections.forEach(item => {
          postData.push({ attrId: item.attrId, attrGroupId: this.attrGroupId })
        })
        console.log(postData)
        this.$http({
          url: this.$http.adornUrl('/product/product/attrgroup/attr/relation/add'),
          method: 'post',
          data: this.$http.adornData(postData, false)
        }).then(({ data }) => {
          if (data.code === 0) {
            this.$message({ type: 'success', message: '新增关联成功' })
          }
          this.$emit('refreshData')
          this.init(this.attrGroupId)
        })
      } else {
      }
    },
    init (id) {
      this.attrGroupId = id || 0
      this.visible = true
      this.getRelationAttrDataList()
    },
    dialogClose () {},

    getRelationAttrDataList () {
      this.RelationAttrDataListLoading = true
      this.$http({
        url: this.$http.adornUrl(
          `/product/product/attrgroup/attr/relation/${this.attrGroupId}`
        ),
        method: 'post',
        data: this.$http.adornData({
          pageIndex: this.relationAttrDataForm.pageIndex,
          pageSize: this.relationAttrDataForm.pageSize,
          key: this.relationAttrDataForm.key
        }, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.relationAttrDataList = data.data
        } else {
          this.relationAttrDataList = null
        }
        this.RelationAttrDataListLoading = false
      })
    },
    // ========
    // 获取未关联属性数据列表
    getNoRelationAttrDataList () {
      this.NoRelationAttrDataListLoading = true
      this.$http({
        url: this.$http.adornUrl(
          `/product/product/attrgroup/noattr/relation/${this.attrGroupId}`
        ),
        method: 'post',
        data: this.$http.adornData(
          {
            pageIndex: this.NoRelationAttrDataForm.pageIndex,
            pageSize: this.NoRelationAttrDataForm.pageSize,
            key: this.NoRelationAttrDataForm.key
          }
        , false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.NoRelationAttrDataList = data.data
        } else {
          this.NoRelationAttrDataList = null
        }
        this.NoRelationAttrDataListLoading = false
      })
    },
    RelationAttrSizeChangeHandle (val) {
      this.relationAttrDataForm.pageSize = val
      this.relationAttrDataForm.pageIndex = 1
      this.getRelationAttrDataList()
    },
    RelationAttrCurrentChangeHandle (val) {
      this.relationAttrDataForm.pageIndex = val
      this.getRelationAttrDataList()
    },
    // 每页数
    NoRelationAttrSizeChangeHandle (val) {
      this.NoRelationAttrDataForm.pageSize = val
      this.NoRelationAttrDataForm.pageIndex = 1
      this.getNoRelationAttrDataList()
    },
    // 当前页
    NoRelationAttrCurrentChangeHandle (val) {
      this.NoRelationAttrDataForm.pageIndex = val
      this.getNoRelationAttrDataList()
    }
  }
}
</script>
<style scoped>
</style>
