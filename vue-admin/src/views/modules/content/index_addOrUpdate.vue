<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
      <el-form-item label="推荐描述" prop="openUrl">
        <el-input v-model="dataForm.description" placeholder="转跳描述"></el-input>
      </el-form-item>
      <el-form-item label="展示图片" prop="imageUrl">
        <single-upload v-model="dataForm.imageUrl">
        </single-upload>
      </el-form-item>
      <el-form-item label="转跳地址" prop="openUrl">
        <el-input v-model="dataForm.openUrl" placeholder="转跳地址"></el-input>
      </el-form-item>
      <el-form-item label="打开方式" prop="openType">
        <el-select v-model="dataForm.openType" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import SingleUpload from '../../../components/upload/singleUpload'

export default {
  components: {SingleUpload},
  data () {
    return {
      visible: false,
      options: [{value: 0, label: '新窗口'}, {value: 1, label: '覆盖窗口'}],
      dataForm: {
        id: 0,
        imageUrl: '',
        openUrl: '',
        enable: 0,
        openType: '',
        description: ''
      },
      dataRule: {

      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/system/homerecommendation/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              console.log(data)
              this.dataForm.imageUrl = data.data.imageUrl
              this.dataForm.openUrl = data.data.openUrl
              this.dataForm.enable = data.data.enable
              this.dataForm.id = data.data.id
              this.dataForm.openType = data.data.openType
              this.dataForm.description = data.data.description
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/system/homerecommendation/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'imageUrl': this.dataForm.imageUrl,
              'openUrl': this.dataForm.openUrl,
              'enable': this.dataForm.enable,
              'openType': this.dataForm.openType,
              'description': this.dataForm.description
            })
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
        }
      })
    }
  }
}
</script>
