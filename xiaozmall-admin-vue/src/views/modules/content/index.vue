<template>
  <el-row :gutter="20">
    <el-col :span="6">
    </el-col>
    <el-col :span="24">
      <div class="mod-config">
        <el-form>
        </el-form>
        <el-table
          border
          style="width: 100%;"
        >
          <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
          <el-table-column prop="attrGroupId" header-align="center" align="center" label="推荐id"></el-table-column>
          <el-table-column prop="attrGroupName" header-align="center" align="center" label="图片"></el-table-column>
          <el-table-column prop="sort" header-align="center" align="center" label="转跳页面"></el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作"
          >
          </el-table-column>
        </el-table>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {

}
</script>

<style scoped>

</style>
