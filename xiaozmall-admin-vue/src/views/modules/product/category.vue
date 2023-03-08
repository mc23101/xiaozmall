<template>
  <div>
    <el-button type="danger" @click="removeAll">
      批量删除
    </el-button>
    <el-tree ref="trees" :data="data" :props="defaultProps" show-checkbox node-key="catId" :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level<=2" type="text" size="mini" @click="() => append(data)">
            添加
          </el-button>
          <el-button v-if="node.childNodes.length===0" type="text" size="mini" @click="() => remove(node, data)">
            删除
          </el-button>
          <el-button type="text" size="mini" @click="() => edit(node, data)">
            编辑
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog title="添加子分类" :visible.sync="appendDialogVisible" width="60%">
      <el-form :model="category" label-width="150px">
        <el-form-item label="父分类ID">
        <el-input v-model="category.parentCid" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="分类层级">
          <el-input v-model="category.catLevel" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="分类名称">
          <el-input v-model="category.name"></el-input>
        </el-form-item>
        <el-form-item label="是否显示">
          <el-input v-model="category.showStatus"></el-input>
        </el-form-item>
        <el-form-item label="分类图标">
          <el-input v-model="category.icon"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit"></el-input>
        </el-form-item>
        <el-form-item label="排序优先级">
          <el-input v-model="category.sort"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="appendDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCategory">添加</el-button>
      </span>
    </el-dialog>


    <el-dialog title="编辑分类" :visible.sync="editDialogVisible " width="60%">
      <el-form :model="category" label-width="150px">
        <el-form-item label="父分类ID">
          <el-input v-model="category.parentCid" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="分类层级">
          <el-input v-model="category.catLevel" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="分类名称">
          <el-input v-model="category.name"></el-input>
        </el-form-item>
        <el-form-item label="是否显示">
          <el-input v-model="category.showStatus"></el-input>
        </el-form-item>
        <el-form-item label="分类图标">
          <el-input v-model="category.icon"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit"></el-input>
        </el-form-item>
        <el-form-item label="排序优先级">
          <el-input v-model="category.sort"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editCategory">添加</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      category: {
        name: null,
        parentCid: null,
        catLevel: null,
        showStatus: null,
        sort: null,
        productUnit: null,
        icon: null,
        catId: null,
        children: null
      },
      appendDialogVisible: false,
      editDialogVisible: false,
      data: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  methods: {
    getMenus () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        console.log(data.data)
        this.data = data.data
      })
    },
    remove (node, data) {
      // console.log(data);
      this.category = {
        name: data.name,
        parentCid: data.parentCid,
        catLevel: data.catLevel,
        showStatus: data.showStatus,
        sort: data.sort,
        productUnit: data.productUnit,
        icon: data.icon,
        catId: data.catId,
        children: data.children
      }
      this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.removeCategory()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    removeCategory () {
      this.$http({
        url: this.$http.adornUrl('/product/category/delete'),
        method: 'post',
        data: this.$http.adornData([this.category.catId], false)
      }).then(({data}) => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        this.getMenus()
      })
    },
    append (data) {
      this.category = {
        name: null,
        parentCid: null,
        catLevel: null,
        showStatus: null,
        sort: null,
        productUnit: null,
        icon: null,
        catId: null,
        children: null
      }
      this.appendDialogVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
    },
    addCategory () {
      this.appendDialogVisible = false
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
        this.getMenus()
      })
    },
    edit (node, data) {
      this.editDialogVisible = true
      this.category = {...data}
    },
    editCategory () {
      this.editDialogVisible = false
      this.$http({
        url: this.$http.adornUrl('/product/category/update'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          message: '修改成功',
          type: 'success'
        })
        this.getMenus()
      })
    },
    removeAll () {
      let checkedNodes = this.$refs.trees.getCheckedNodes(false, false)
      let Nodes = []
      for (let i = 0; i < checkedNodes.length; i++) {
        Nodes.push(checkedNodes[i].catId)
      }
      this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(Nodes, false)
        }).then(({data}) => {
          this.$message({
            type: 'success',
            message: '批量删除成功!'
          })
          this.getMenus()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  },
  created () {
    this.getMenus()
  }
}
</script>
