<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      :rules="dataRule"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="120px"
    >
      <el-form-item label="分类" prop="catId">
        <category-cascader></category-cascader>
      </el-form-item>
      <el-form-item label="品牌" prop="brandId">
        <brand-select></brand-select>
      </el-form-item>
      <el-form-item label="spu" prop="spuId">
        <spu-select></spu-select>
      </el-form-item>
      <el-form-item label="sku" prop="skuId">
        <sku-select></sku-select>
      </el-form-item>
      <el-form-item label="仓库" prop="wareId">
        <el-select v-model="dataForm.wareId" placeholder="请选择仓库" clearable>
          <el-option :label="w.name" :value="w.id" v-for="w in wareList" :key="w.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="库存数" prop="stock">
        <div>
          <el-input v-model="dataForm.stock" placeholder="库存数" ></el-input>
        </div>
      </el-form-item>
      <el-form-item label="锁定库存" prop="stockLocked">
        <el-switch
          v-model="dataForm.stockLocked"
          active-color="#13ce66"
          inactive-color="#ff4949">
        </el-switch>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>
<style>
.el-input{
  width: 300px;
}
</style>
<script>
import CategoryCascader from '../common/category-cascader'
import BrandSelect from '../common/brand-select'
import SpuSelect from '../common/spu-select'
import PubSub from 'pubsub-js'
import SkuSelect from '../common/sku-select'
export default {
  components: {SkuSelect, BrandSelect, CategoryCascader, SpuSelect},
  data () {
    return {
      visible: false,
      wareList: [],
      brandSub: null,
      catSub: null,
      spuSub: null,
      skuSub: null,
      dataForm: {
        catId: '',
        brandId: '',
        id: '',
        spuId: '',
        skuId: '',
        wareId: '',
        stock: '',
        skuName: '',
        stockLocked: 0
      },
      dataRule: {
        catId: [{ required: true, message: '分类不能为空', trigger: 'blur' }],
        brandId: [{ required: true, message: '品牌不能为空', trigger: 'blur' }],
        spuId: [{ required: true, message: 'spu不能为空', trigger: 'blur' }],
        skuId: [{ required: true, message: 'sku不能为空', trigger: 'blur' }],
        wareId: [
          { required: true, message: '仓库id不能为空', trigger: 'blur' }
        ],
        stock: [{ required: true, message: '库存数不能为空', trigger: 'blur' }]
      }
    }
  },
  created () {
    this.getWares()
  },
  methods: {
    getWares () {
      this.$http({
        url: this.$http.adornUrl('/ware/wareinfo/list'),
        method: 'get',
        params: this.$http.adornParams({
          page: 1,
          limit: 500,
          key: ''
        })
      }).then(({ data }) => {
        this.wareList = data.page
        console.log(this.wareList)
      })
    },
    init (id) {
      this.dataForm.id = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/ware/waresku/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            console.log(data)
            if (data && data.code === 0) {
              this.dataForm.skuId = data.wareSku.skuId
              this.dataForm.wareId = data.wareSku.wareId
              this.dataForm.stock = data.wareSku.stock
              this.dataForm.skuName = data.wareSku.skuName
              this.dataForm.stockLocked = data.wareSku.stockLocked
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      console.log(this.dataForm)
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/ware/waresku/${!this.dataForm.id ? 'save' : 'update'}`
            ),
            method: 'post',
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              skuId: this.dataForm.skuId,
              wareId: this.dataForm.wareId,
              stock: this.dataForm.stock,
              skuName: this.dataForm.skuName,
              stockLocked: this.dataForm.stockLocked ? 0:1
            })
          }).then(({ data }) => {
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
  },
  mounted () {
    this.brandSub = PubSub.subscribe('brandId', (msg, val) => {
      this.dataForm.brandId = val
    })
    this.catSub = PubSub.subscribe('catPath', (msg, val) => {
      this.dataForm.catId = val[val.length - 1]
    })
    this.spuSub = PubSub.subscribe('spuId', (msg, val) => {
      this.dataForm.spuId = val
    })
    this.skuSub = PubSub.subscribe('skuId', (msg, val) => {
      this.dataForm.skuId = val.skuId
      this.dataForm.skuName = val.skuName
      console.log(this.dataForm)
    })
  },
  beforeDestroy () {
    PubSub.unsubscribe(this.brandSub)
    PubSub.unsubscribe(this.catSub)
    PubSub.unsubscribe(this.spuSub)
    PubSub.unsubscribe(this.skuSub)
  }
}
</script>
