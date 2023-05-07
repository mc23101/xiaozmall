<template>
  <el-card class="box-card" style="width:80%;margin:20px auto">
    <el-form ref="spuBaseForm" :model="baseInfo" label-width="120px" :rules="spuBaseInfoRules">
      <el-form-item label="商品名称" prop="spuName">
        <el-input v-model="baseInfo.spuName"></el-input>
      </el-form-item>
      <el-form-item label="商品描述" prop="spuDescription">
        <el-input v-model="baseInfo.spuDescription"></el-input>
      </el-form-item>
      <el-form-item label="选择分类" prop="catalogId">
        <category-cascader :catalog-path.sync="catalogPath"></category-cascader>
      </el-form-item>
      <el-form-item label="选择品牌" prop="brandId">
        <brand-select :cat-id.sync="baseInfo.catalogId" :brand-id.sync="baseInfo.brandId"></brand-select>
      </el-form-item>
      <el-form-item label="商品重量(Kg)" prop="weight">
        <el-input-number v-model.number="baseInfo.weight" :min="0" :precision="3" :step="0.1"></el-input-number>
      </el-form-item>
      <el-form-item label="设置积分" prop="bounds">
        <label>金币</label>
        <el-input-number
          style="width:130px"
          placeholder="金币"
          v-model="baseInfo.bounds.buyBounds"
          :min="0"
          controls-position="right"
        ></el-input-number>
        <label style="margin-left:15px">成长值</label>
        <el-input-number
          style="width:130px"
          placeholder="成长值"
          v-model="baseInfo.bounds.growBounds"
          :min="0"
          controls-position="right"
        >
          <template slot="prepend">成长值</template>
        </el-input-number>
      </el-form-item>
      <el-form-item label="默认图片" prop="defaultImg">
        <single-upload v-model="baseInfo.defaultImg"></single-upload>
      </el-form-item>
      <el-form-item label="商品介绍" prop="descript">
        <multi-upload v-model="baseInfo.descript"></multi-upload>
      </el-form-item>

      <el-form-item label="商品图集" prop="images">
        <multi-upload v-model="baseInfo.images"></multi-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="collectSpuBaseInfo">下一步：设置基本参数</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import CategoryCascader from '../../common/category-cascader'
import BrandSelect from '../../common/brand-select'
import MultiUpload from '../../../../components/upload/multiUpload'
import PubSub from 'pubsub-js'
import SingleUpload from '../../../../components/upload/singleUpload.vue'

export default {
  components: { CategoryCascader, BrandSelect, MultiUpload, SingleUpload },
  data () {
    return {
      catPathSub: '',
      brandIdSub: '',
      catalogPath: [],

      baseInfo: {
        spuName: '',
        spuDescription: '',
        catalogId: 0,
        brandId: undefined,
        weight: '',
        defaultImg: '',
        bounds: {
          buyBounds: '',
          growBounds: ''
        },
        descript: [],
        images: []
      },
      spuBaseInfoRules: {
        spuName: [
          { required: true, message: '请输入商品名字', trigger: 'blur' }
        ],
        spuDescription: [
          { required: true, message: '请编写一个简单描述', trigger: 'blur' }
        ],
        catalogId: [
          { required: true, message: '请选择一个分类', trigger: 'blur' }
        ],
        brandId: [
          { required: true, message: '请选择一个品牌', trigger: 'blur' }
        ],
        descript: [
          { required: true, message: '请上传商品详情图集', trigger: 'blur' }
        ],
        defaultImg: [
          {required: true, message: '请上传商品默认图片', trigger: 'blur'}
        ],
        images: [
          { required: true, message: '请上传商品图片集', trigger: 'blur' }
        ],
        weight: [
          {
            type: 'number',
            required: true,
            message: '请填写正确的重量值',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  watch: {
    catalogPath (val) {
      this.baseInfo.catalogId = val[val.length - 1]
    }
  },
  methods: {
    collectSpuBaseInfo () {
      // spuBaseForm
      this.$refs.spuBaseForm.validate(valid => {
        if (valid) {
          PubSub.publish('baseInfo', this.baseInfo)
          PubSub.publish('stepChange', 1)
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
