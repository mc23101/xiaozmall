<template>
  <el-card class="box-card" style="width:80%;margin:20px auto">
    <el-tabs tab-position="left" style="width:98%">
      <el-tab-pane :label="group.attrGroupName" v-for="(group,gidx) in dataResp.attrGroups" :key="group.attrGroupId">
        <!-- 遍历属性,每个tab-pane对应一个表单，每个属性是一个表单项  spu.baseAttrs[0] = [{attrId:xx,val:}]-->
        <el-form ref="form" >
          <el-form-item :label="attr.attrName" v-for="(attr,aidx) in group.attrs" :key="attr.attrId">
            <el-input v-model="spuAttrs[gidx][aidx].attrId" type="hidden" v-show="false"></el-input>
            <el-select
              v-model="spuAttrs[gidx][aidx].attrValue"
              filterable
              allow-create
              default-first-option
              placeholder="请选择或输入值"
            >
              <el-option
                v-for="(val,vidx) in attr.valueSelect.split(';')"
                :key="vidx"
                :label="val"
                :value="val"
              ></el-option>
            </el-select>
<!--            <el-checkbox-->
<!--              v-model="baseAttrs[gidx][aidx].showDesc"-->
<!--              :true-label="1"-->
<!--              :false-label="0"-->
<!--            >快速展示</el-checkbox>-->
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <div style="margin:auto">
      <el-button type="primary" @click="PubSub.publish('stepChange',0)">上一步</el-button>
      <el-button type="success" @click="PubSub.publish('stepChange',2)">下一步：设置销售属性</el-button>
    </div>
  </el-card>
</template>

<script>
import PubSub from 'pubsub-js'

export default {
  props: {
    step: '',
    baseInfo: ''
  },
  watch: {
    baseInfo (val) {
      this.showBaseAttrs()
    },
    step (val) {
      if (val === 1) {
        PubSub.publish('spuAttrs', this.spuAttrs)
      }
    }
  },
  data () {
    return {
      spuAttrs: [],
      dataResp: {
        attrGroups: []
      }
    }
  },
  methods: {
    showBaseAttrs () {
      this.$http({
        url: this.$http.adornUrl(
          `/product/attrgroup/withattr/${this.baseInfo.catalogId}`
        ),
        method: 'get',
        params: this.$http.adornParams({})
      }).then(({ data }) => {
        // 先对表单的baseAttrs进行初始化
        console.log(data.data)
        data.data.forEach(item => {
          let attrArray = []
          item.attrs.forEach((attr) => {
            attrArray.push({
              attrId: attr.attrId,
              attrValue: ''
            })
          })
          this.spuAttrs.push(attrArray)
        })
        this.dataResp.attrGroups = data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
