<template>
  <el-card class="box-card" style="width:80%;margin:20px auto">
    <el-table :data="skus" style="width: 100%">
      <el-table-column label="属性组合">
        <el-table-column :label="item.attrName" v-for="(item,index) in dataResp.tableAttrColumn" :key="item.attrId">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.attr[index].attrValue }}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="商品名称" prop="skuName">
        <template slot-scope="scope">
          <el-input v-model="scope.row.skuName"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="标题" prop="skuTitle">
        <template slot-scope="scope">
          <el-input v-model="scope.row.skuTitle"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="副标题" prop="skuSubtitle">
        <template slot-scope="scope">
          <el-input v-model="scope.row.skuSubtitle"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="价格" prop="price">
        <template slot-scope="scope">
          <el-input v-model="scope.row.price"></el-input>
        </template>
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="scope">
<!--          <el-row>-->
<!--            <el-col :span="24">-->
<!--              <label style="display:block;float:left">选择图集 或</label>-->
<!--              <multi-upload-->
<!--                style="float:left;margin-left:10px;"-->
<!--                :showFile="false"-->
<!--                :listType="'text'"-->
<!--                v-model="uploadImages"-->
<!--              ></multi-upload>-->
<!--            </el-col>-->
<!--            <el-col :span="24">-->
<!--              <el-divider></el-divider>-->
<!--            </el-col>-->
<!--            <el-col :span="24">-->
<!--              <el-card style="width:170px;float:left;margin-left:15px;margin-top:15px;"-->
<!--                :body-style="{ padding: '0px' }"-->
<!--                v-for="(img,index) in baseInfo.images"-->
<!--                :key="index"-->
<!--              >-->
<!--                <img :src="img" style="width:160px;height:120px" />-->
<!--                <div style="padding: 14px;">-->
<!--                  <el-row>-->
<!--                    <el-col :span="12">-->
<!--                      <el-checkbox-->
<!--                        v-model="scope.row.images[index].imgUrl"-->
<!--                        :true-label="img"-->
<!--                        false-label="true"-->
<!--                      ></el-checkbox>-->
<!--                    </el-col>-->
<!--                    <el-col :span="12">-->
<!--                      <el-tag v-if="scope.row.images[index].defaultImg === 1">-->
<!--                        <input-->
<!--                          type="radio"-->
<!--                          checked-->
<!--                          :name="scope.row.skuName"-->
<!--                          @change="checkDefaultImg(scope.row,index,img)"-->
<!--                        />设为默认-->
<!--                      </el-tag>-->
<!--                      <el-tag v-else>-->
<!--                        <input-->
<!--                          type="radio"-->
<!--                          :name="scope.row.skuName"-->
<!--                          @change="checkDefaultImg(scope.row,index,img)"-->
<!--                        />设为默认-->
<!--                      </el-tag>-->
<!--                    </el-col>-->
<!--                  </el-row>-->
<!--                </div>-->
<!--              </el-card>-->
<!--            </el-col>-->
<!--          </el-row>-->
          <!-- 折扣，满减，会员价 -->
          <el-form :model="scope.row">
            <el-row>
              <el-col :span="24">
                <el-form-item label="设置折扣">
                  <label>满</label>
                  <el-input-number
                    style="width:160px"
                    :min="0"
                    controls-position="right"
                    v-model="scope.row.fullCount"
                  ></el-input-number>
                  <label>件</label>

                  <label style="margin-left:15px;">打</label>
                  <el-input-number
                    style="width:160px"
                    v-model="scope.row.discount"
                    :precision="2"
                    :max="1"
                    :min="0"
                    :step="0.01"
                    controls-position="right"
                  ></el-input-number>
                  <label>折</label>
                  <el-checkbox
                    v-model="scope.row.countStatus"
                    :true-label="1"
                    :false-label="0"
                  >可叠加优惠</el-checkbox>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="设置满减">
                  <label>满</label>
                  <el-input-number
                    style="width:160px"
                    v-model="scope.row.fullPrice"
                    :step="100"
                    :min="0"
                    controls-position="right"
                  ></el-input-number>
                  <label>元</label>
                  <label style="margin-left:15px;">减</label>
                  <el-input-number
                    style="width:160px"
                    v-model="scope.row.reducePrice"
                    :step="10"
                    :min="0"
                    controls-position="right"
                  ></el-input-number>
                  <label>元</label>
                  <el-checkbox
                    v-model="scope.row.priceStatus"
                    :true-label="1"
                    :false-label="0"
                  >可叠加优惠</el-checkbox>
                </el-form-item>
              </el-col>

<!--              <el-col :span="24">-->
<!--                <el-form-item label="设置会员价" v-if="scope.row.memberPrice.length>0">-->
<!--                  <br />-->
<!--                  &lt;!&ndash;   @change="handlePriceChange(scope,mpidx,$event)" &ndash;&gt;-->
<!--                  <el-form-item v-for="(mp,mpidx) in scope.row.memberPrice" :key="mp.id">-->
<!--                    {{mp.name}}-->
<!--                    <el-input-number-->
<!--                      style="width:160px"-->
<!--                      v-model="scope.row.memberPrice[mpidx].price"-->
<!--                      :precision="2"-->
<!--                      :min="0"-->
<!--                      controls-position="right"-->
<!--                    ></el-input-number>-->
<!--                  </el-form-item>-->
<!--                </el-form-item>-->
<!--              </el-col>-->
            </el-row>
          </el-form>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="PubSub.publish('stepChange',2)">上一步</el-button>
    <el-button type="success" @click="submitSkus()">下一步：保存商品信息</el-button>
  </el-card>
</template>

<script>
import PubSub from 'pubsub-js'

export default {
  props: {
    step: 0,
    baseInfo: '',
    spuAttrGroup: '',
    saleAttrs: ''
  },
  watch: {
    step (val) {
      if (val === 3) {
        this.generateSkus()
        PubSub.publish('skus', this.skus)
      }
    }
  },
  data () {
    return {
      skus: [],
      dataResp: {
        tableAttrColumn: []
      },
      spu: {
        descript: [],
        images: [],
        spuAttrGroup:[],
        skus: []
      }
    }
  },
  methods: {
    submitSkus () {
      console.log(this.spu)
      this.generateSpu()
      this.$confirm('将要提交商品数据，需要一小段时间，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl('/product/spuinfo/save'),
            method: 'post',
            data: this.$http.adornData(this.spu, false)
          }).then(({ data }) => {
            if (data.code === 0) {
              this.$message({
                type: 'success',
                message: '新增商品成功!'
              })
              PubSub.publish('stepChange', 4)
            } else {
              this.$message({
                type: 'error',
                message: '保存失败，原因【' + data.msg + '】'
              })
            }
          })
        })
        .catch(e => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    },
    generateSpu () {
      this.spu.spuName = this.baseInfo.spuName
      this.spu.spuDescription = this.baseInfo.spuDescription
      this.spu.catalogId = this.baseInfo.catalogId
      this.spu.brandId = this.baseInfo.brandId
      this.spu.weight = this.baseInfo.weight
      this.spu.publishStatus = 0
      this.baseInfo.descript.forEach(item => {
        this.spu.descript.push({imgUrl: item})
      })
      this.baseInfo.images.forEach(item => {
        this.spu.images.push({imgUrl: item})
      })
      this.spu.spuAttrGroup=this.spuAttrGroup
      this.spu.skus = this.skus
    },
    generateSkus () {
      // 根据笛卡尔积运算进行生成sku
      let selectValues = []
      this.dataResp.tableAttrColumn = []
      this.saleAttrs.forEach(item => {
        if (item.attrValues.length > 0) {
          selectValues.push(item.attrValues)
          this.dataResp.tableAttrColumn.push(item)
        }
      })

      let descartes = this.descartes(selectValues)
      // [["黑色","6GB","移动"],["黑色","6GB","联通"],["黑色","8GB","移动"],["黑色","8GB","联通"],
      // ["白色","6GB","移动"],["白色","6GB","联通"],["白色","8GB","移动"],["白色","8GB","联通"],
      // ["蓝色","6GB","移动"],["蓝色","6GB","联通"],["蓝色","8GB","移动"],["蓝色","8GB","联通"]]
      // 有多少descartes就有多少sku
      let skus = []

      descartes.forEach((descar, descaridx) => {
        let attrArray = [] // sku属性组
        descar.forEach((de, index) => {
          // 构造saleAttr信息
          let saleAttrItem = {
            attrId: this.dataResp.tableAttrColumn[index].attrId,
            attrName: this.dataResp.tableAttrColumn[index].attrName,
            attrValue: de
          }
          attrArray.push(saleAttrItem)
        })
        // 先初始化几个images，后面的上传还要加
        let imgs = []
        this.baseInfo.images.forEach((img, idx) => {
          // eslint-disable-next-line standard/object-curly-even-spacing
          imgs.push({ imgUrl: ''})
        })

        // // 会员价，也必须在循环里面生成，否则会导致数据绑定问题
        // let memberPrices = []
        // if (this.dataResp.memberLevels.length > 0) {
        //   for (let i = 0; i < this.dataResp.memberLevels.length; i++) {
        //     if (this.dataResp.memberLevels[i].priviledgeMemberPrice === 1) {
        //       memberPrices.push({
        //         id: this.dataResp.memberLevels[i].id,
        //         name: this.dataResp.memberLevels[i].name,
        //         price: 0
        //       })
        //     }
        //   }
        // }
        // ;descaridx，判断如果之前有就用之前的值;
        let res = this.hasAndReturnSku(this.skus, descar)
        if (res === null) {
          skus.push({
            attr: attrArray,
            skuName: this.baseInfo.spuName + ' ' + descar.join(' '),
            price: 0,
            skuTitle: this.baseInfo.spuName + ' ' + descar.join(' '),
            skuSubtitle: '',
            images: imgs,
            fullCount: 0,
            discount: 0,
            countStatus: 0,
            fullPrice: 0.0,
            reducePrice: 0.0,
            priceStatus: 0
            // memberPrice: [].concat(memberPrices)
          })
        } else {
          skus.push(res)
        }
      })
      this.skus = skus
    },
    // 笛卡尔积运算
    descartes (list) {
      // parent上一级索引;count指针计数
      var point = {}

      var result = []
      var pIndex = null
      var tempCount = 0
      var temp = []

      // 根据参数列生成指针对象
      for (var index in list) {
        if (typeof list[index] === 'object') {
          point[index] = { parent: pIndex, count: 0 }
          pIndex = index
        }
      }

      // 单维度数据结构直接返回
      if (pIndex == null) {
        return list
      }

      // 动态生成笛卡尔积
      while (true) {
        for (var index in list) {
          tempCount = point[index]['count']
          temp.push(list[index][tempCount])
        }

        // 压入结果数组
        result.push(temp)
        temp = []

        // 检查指针最大值问题
        while (true) {
          if (point[index]['count'] + 1 >= list[index].length) {
            point[index]['count'] = 0
            pIndex = point[index]['parent']
            if (pIndex == null) {
              return result
            }

            // 赋值parent进行再次检查
            index = pIndex
          } else {
            point[index]['count']++
            break
          }
        }
      }
    },
    hasAndReturnSku (skus, descar) {
      let res = null
      if (skus.length > 0) {
        for (let i = 0; i < skus.length; i++) {
          if (skus[i].descar.join(' ') === descar.join(' ')) {
            res = skus[i]
          }
        }
      }
      return res
    }
  }
}
</script>

<style scoped>

</style>
