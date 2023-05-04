<template>
  <el-card class="box-card" style="width:80%;margin:20px auto">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>选择销售属性</span>
        <el-form>
          <el-form-item
            :label="attr.attrName"
            v-for="(attr,aidx) in dataResp.saleAttrs"
            :key="attr.attrId"
          >
            <el-input
              v-model="saleAttrs[aidx].attrId"
              type="hidden"
              v-show="false"
            ></el-input>
            <el-checkbox-group v-model="saleAttrs[aidx].attrValues">
              <el-checkbox
                v-if="dataResp.saleAttrs[aidx].valueSelect !== ''"
                :label="val"
                v-for="val in dataResp.saleAttrs[aidx].valueSelect.split(';')"
                :key="val"
              ></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
      </div>
      <el-button type="primary" @click="PubSub.publish('stepChange',1)">上一步</el-button>
      <el-button type="success" @click="PubSub.publish('stepChange',3)">下一步：设置SKU信息</el-button>
    </el-card>
  </el-card>
</template>

<script>
import PubSub from 'pubsub-js'

export default {
  props: {
    baseInfo: '',
    step: ''
  },
  watch: {
    baseInfo (val) {
      this.showSaleAttr()
    },
    step (val) {
      if (val === 2) {
        PubSub.publish('saleAttrs', this.saleAttrs)
      }
    }
  },
  data () {
    return {
      saleAttrs: [],
      dataResp: {
        saleAttrs: []
      }
    }
  },
  methods: {
    showSaleAttr () {
      // 获取当前分类可以使用的销售属性
      this.$http({
        url: this.$http.adornUrl(
          `/product/attr/sale/list/${this.baseInfo.catalogId}`
        ),
        method: 'get',
        params: this.$http.adornParams({
          page: 1,
          limit: 500
        })
      }).then(({ data }) => {
        this.dataResp.saleAttrs = data.page.list
        data.page.list.forEach(item => {
          this.saleAttrs.push({
            attrId: item.attrId,
            attrValues: [],
            attrName: item.attrName
          })
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
