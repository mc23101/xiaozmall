<template>
    <body>
    <div id="max">
        <header>
            <div class="min">
                <ul class="header_ul_left">
                    <li class="glyphicon glyphicon-home"> <a href="#/" class="aa">首页</a></li>
                </ul>
                <ul class="header_ul_right">
                    <li><a href="javascript:;" class="aa">我的订单</a></li>
                    <li><a href="#/register" style="color: red;">免费注册</a></li>
                    <li><a href="#/login" class="aa">你好，请登录</a></li>
                </ul>
            </div>
        </header>
        <nav>
            <div class="nav_min">
                <div class="nav_top">
                    <div class="nav_top_one"><a href="#"><img src="~@/assets/img/detail/logo1.jpg" alt=""/></a></div>
                    <div class="nav_top_two"><input type="text"/><button>搜索</button></div>
                    <div class="nav_top_three"><a href="#/shopCart">我的购物车</a><span class="glyphicon glyphicon-shopping-cart"></span>
                        <div class="nav_top_three_1">
                            <img src="~@/assets/img/detail/44.png" alt=""/>购物车还没有商品，赶紧选购吧！
                        </div>
                    </div>
                </div>
                <div class="nav_down">
                    <ul class="nav_down_ul">
                        <li class="nav_down_ul_1" style="width: 24%;float: left;"><a href="javascript:;">全部商品分类</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
    <div class="crumb-wrap">
        <div class="w">
        </div>
    </div>
    <div class="Shop">
        <div class="box">
            <div class="box-one ">
                <div class="boxx">
                    <div class="imgbox">
                        <div class="probox">
                            <img ref="imgbox" @mouseover="zoomHoverMouseOver($event)" @mousemove="zoomHoverMouseMove($event)" @mouseout="zoomHoverMouseOut($event)" class="img1" alt="" :src="dataForm.sku.images[dataForm.showImageIndex]">
                            <div class="hoverbox" @mouseover="zoomHoverMouseOver($event)" @mousemove="zoomHoverMouseMove($event)" @mouseout="zoomHoverMouseOut($event)" ref="hoverbox"></div>
                        </div>
                        <div class="showbox">
                            <img ref="showbox" class="img1" alt="" :src="dataForm.sku.images[dataForm.showImageIndex].imgUrl">
                        </div>
                    </div>
                    <div class="box-lh">
                        <div class="box-lh-one">
                           <swiper :options="swiperOption" class="swiper-wrapper">
                               <swiper-slide class="swiper-slide" v-for="i in Math.ceil(dataForm.sku.images.length/5.0)" :key="i">
                                   <ul class="box-lh-one-ul">
                                       <li class="box-lh-one-li" v-for="j in Math.min(dataForm.sku.images.length-(i-1)*5,5)" :key="j">
                                           <img :src="dataForm.sku.images[(i-1)*5+j-1].imgUrl" style="border: 2px solid red" v-if="(i-1)*5+j-1===dataForm.showImageIndex" @mouseenter="dataForm.showImageIndex=(i-1)*5+j-1" alt="">
                                           <img :src="dataForm.sku.images[(i-1)*5+j-1].imgUrl" style="border: 1px solid grey" v-else alt="" @mouseenter="dataForm.showImageIndex=(i-1)*5+j-1"/>
                                       </li>
                                   </ul>
                               </swiper-slide>
                           </swiper>
                        </div>
                        <div class="swiper-button-prev box-lh-one-list">
                        </div>
                        <div class="swiper-button-next box-lh-one-list">
                        </div>
                        <div id="left">
                        </div>
                        <div id="right">
                        </div>
                    </div>

                    <div class="boxx-one">
                        <ul>
                            <li></li>
                            <li></li>
                        </ul>
                    </div>

                </div>
                <div class="box-two">
                    <div class="box-name">
                        {{dataForm.sku.skuTitle}}
                    </div>

                    <div class="box-yuyue">
                        <div class="yuyue-one">
                            热卖促销中
                        </div>
                        <div class="yuyue-two">
                            <ul>
                                <li>
                                </li>
                                <li>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="box-summary clear">
                        <ul>
                            <li>惊喜价</li>
                            <li>
                                <span>￥</span>
                                <span>{{dataForm.sku.price.toFixed(2)}}</span>
                            </li>
                            <li></li>
                            <li><a href=""></a></li>
                        </ul>
                    </div>
                    <div class="box-wrap clear">
                    </div>
                    <div class="box-stock">
                        <ul class="box-ul">
                            <li>库存:</li>
                            <li class="box-stock-li">
                                <div class="box-stock-one">
                                </div>
                            </li>
                            <li v-if="dataForm.sku.stock===0">
                                <span>无货</span>， 此商品暂时售完
                            </li>
                            <li v-else>
                                <span>{{dataForm.sku.stock}}</span>
                            </li>
                        </ul>
                    </div>
                    <div class="box-stock">
                        <ul class="box-ul">
                            <li>重  量:</li>
                            <li class="box-stock-li">
                                <div class="box-stock-one">
                                </div>
                            </li>
                            <li>
                                {{product.weight}} kg
                            </li>
                        </ul>
                    </div>

                    <div class="box-attr-3">
                        <br/>
                        <div class="box-attr-2 clear" v-for="(saleAttr,saleIndex) in product.saleAttrs" :key="saleIndex">
                            <dl>
                                <dt>{{saleAttr.attrName}}</dt>
                                <dd class="redborder" v-for="(value,valueIndex) in saleAttr.valueSelect.split(';')" :key="valueIndex" @click="selectSaleValue(saleIndex,value)">
                                    <div>
                                        {{value}}
                                    </div>
                                </dd>
                            </dl>
                        </div>
                        <br/>
                    </div>

                    <div class="box-btns clear">
                        <div class="box-btns-one">
                            <form id="itemForm">
                                <input type="text" name="num" id=""  v-model="dataForm.count"/>
                                <div class="box-btns-one1">

                                    <div>
                                        <button type="button" id="jia" @click="dataForm.count++">
                                            +
                                        </button>
                                    </div>
                                    <div>
                                        <button type="button" id="jian" @click="dataForm.count!==1?dataForm.count--:1">
                                            -
                                        </button>
                                    </div>

                                </div>
                            </form>
                        </div>
                        <div id="cartBtn"  style="cursor:pointer;" onclick="addToCart(this)" class="box-btns-two">
                            放入购物车
                        </div>
                        <div id="jieBtn"  style="cursor:pointer;" onclick="addToCart(this)" class="box-btns-two">
                            <span>直接结算</span>
                        </div>
                    </div>


                </div>

            </div>
        </div>


        <div class="ShopXiangqing">
            <div class="allLeft">
                <!--火热预约-->
                <div class="huoreyuyue">
                    <h3>火热预约</h3>
                </div>
                <div class="dangeshopxingqing">
                    <ul class="shopdange">
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/5a0afeddNb34732af.jpg" /></a>
                            <p>
                                <a href="##">OPPO R11s Plus 双卡双待全面屏拍照手机香槟色 全网通(6G RAM+64G ROM)标配</a>
                            </p>
                            <p><strong class="J-p-20015341974">￥3699.00</strong></p>
                        </li>
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/5a12873eN41754123.jpg" /></a>
                            <p>
                                <a target="_blank" title="詹姆士（GEMRY） R19plus全网通4G 智能手机 双卡双待 6+128GB 鳄鱼纹雅致版（新品预约）" href="/item.html.bak/item.jd.com/20348283521.html">詹姆士（GEMRY） R19plus全网通4G 智能手机 双卡双待 6+128GB 鳄鱼纹雅致版（新品预约）</a>
                            </p>
                            <p><strong class="J-p-20348283521">￥13999.00</strong></p>
                        </li>
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/59ec0131Nf239df75.jpg" /></a>
                            <p>
                                <a target="_blank" title="斐纳（TOMEFON） 德国家用无线无绳手持立式充电吸尘器 静音大吸力吸尘器TF-X60" href="/item.html.bak/item.jd.com/16683419775.html">斐纳（TOMEFON） 德国家用无线无绳手持立式充电吸尘器 静音大吸力吸尘器TF-X60</a>
                            </p>
                            <p><strong class="J-p-16683419775">￥1599.00</strong></p>
                        </li>
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/59015444N27317512.jpg" /></a>
                            <p>
                                <a target="_blank" title="斐纳（TOMEFON） 扫地机器人德国智能导航规划全自动超薄扫地机器人吸尘器TF-D60 香槟金" href="/item.html.bak/item.jd.com/12187770381.html">斐纳（TOMEFON） 扫地机器人德国智能导航规划全自动超薄扫地机器人吸尘器TF-D60 香槟金</a>
                            </p>
                            <p><strong class="J-p-12187770381">￥2599.00</strong></p>
                        </li>
                    </ul>
                </div>
                <!--看了又看-->
                <div class="huoreyuyue">
                    <h3>看了又看</h3>
                </div>
                <div class="dangeshopxingqing">
                    <ul class="shopdange">
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/59e55e01N369f98f2.jpg" /></a>
                            <p>
                                <a target="_blank" title="华为（HUAWEI） 华为 Mate10 4G手机  双卡双待 亮黑色 全网通(6GB RAM+128GB ROM)" href="/item.html.bak/item.jd.com/17931625443.html">华为（HUAWEI） 华为 Mate10 4G手机 双卡双待 亮黑色 全网通(6GB RAM+128GB ROM)</a>
                            <p><strong class="J-p-17931625443">￥4766.00</strong></p>
                        </li>
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/584fcc3eNdb0ab94c.jpg" /></a>
                            <p>
                                <a target="_blank" title="华为 Mate 9 Pro 6GB+128GB版 琥珀金 移动联通电信4G手机 双卡双待" href="/item.html.bak/item.jd.com/3749093.html">华为 Mate 9 Pro 6GB+128GB版 琥珀金 移动联通电信4G手机 双卡双待</a>
                            </p>
                            <p><strong class="J-p-3749093">￥4899.00</strong></p>
                        </li>
                        <li>
                            <!--shopjieshao-->
                            <a href="##"><img src="~@/assets/img/detail/59eb0df9Nd66d7585.jpg" /></a>
                            <p>
                                <a target="_blank" title="华为（HUAWEI） 华为 Mate10 手机 亮黑色 全网通(4+64G)标准版" href="/item.html.bak/item.jd.com/12306211773.html">华为（HUAWEI） 华为 Mate10 手机 亮黑色 全网通(4+64G)标准版</a>
                            </p>
                            <p><strong class="J-p-12306211773">￥4088.00</strong></p>
                        </li>
                        <li>
                            <a href="##"><img src="~@/assets/img/detail/5a002ba3N126c2f73.jpg" /></a>
                            <p>
                                <a target="_blank" title="斐纳（TOMEFON） 扫地机器人德国智能导航规划全自动超薄扫地机器人吸尘器TF-D60 香槟金" href="/item.html.bak/item.jd.com/12187770381.html">斐纳（TOMEFON） 扫地机器人德国智能导航规划全自动超薄扫地机器人吸尘器TF-D60 香槟金</a>
                            </p>
                            <p><strong class="J-p-12187770381">￥2599.00</strong></p>
                        </li>
                    </ul>
                    <img src="~@/assets/img/detail/5a084a1aNa1aa0a71.jpg" />
                </div>
            </div>
            <!--商品介绍-->
            <div class="allquanbushop">
                <ul class="shopjieshao">
                    <li class="jieshoa" style="background: #e4393c;cursor:pointer;" v-if="dataForm.state===1" @click="dataForm.state=1">
                        <a style="color: white;">商品介绍</a>
                    </li>
                    <li class="jieshoa" style="cursor:pointer;" v-else @click="dataForm.state=1">
                        <a>商品介绍</a>
                    </li>
                    <li class="baozhuang" style="background: #e4393c;cursor:pointer;"  v-if="dataForm.state===2" @click="dataForm.state=2">
                        <a style="color: white;">规格与包装</a>
                    </li>
                    <li class="baozhuang" style="cursor:pointer;" v-else @click="dataForm.state=2">
                        <a>规格与包装</a>
                    </li>
                    <li class="baozhang" style="background: #e4393c;cursor:pointer;"  v-if="dataForm.state===3" @click="dataForm.state=3">
                        <a style="color: white;">售后保障</a>
                    </li>
                    <li class="baozhang" style="cursor:pointer;" v-else @click="dataForm.state=3">
                        <a>售后保障</a>
                    </li>
                    <li class="pingjia" style="background: #e4393c;cursor:pointer;"  v-if="dataForm.state===4" @click="dataForm.state=4">
                        <a style="color: white;">商品评价(4万+)</a>
                    </li>
                    <li class="pingjia" style="cursor:pointer;" v-else @click="dataForm.state=4">
                        <a>商品评价(4万+)</a>
                    </li>
                    <li class="shuoming" style="background: #e4393c;cursor:pointer;"  v-if="dataForm.state===5" @click="dataForm.state=5">
                        <a style="color: white;">预约说明</a>
                    </li>
                    <li class="shuoming" style="cursor:pointer;" v-else @click="dataForm.state=5">
                        <a>预约说明</a>
                    </li>
                </ul>

                <!--商品详情-->
                <div class="huawei">
                    <ul class="xuanxiangka">
                        <li class="jieshoa actives" id="li1" v-if="dataForm.state===1">
                            <div class="shanpinsssss">
                                <p>
                                    <a>品牌:华为（HUAWEI）</a>
                                </p>
                                <div class="table">
                                    <table>
                                        <tr>
                                            <td>
                                                <a>商品名称：{{product.spuName}}</a>
                                            </td>
                                            <td>
                                                <a>商品毛重：{{product.weight}}kg</a>
                                            </td>
                                            <td>
                                                <a>商品编号：5544038</a>
                                            </td>
                                            <td>
                                                <a>商品产地：中国大陆</a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <img v-for="(img,index) in product.descript" :key="index" :src="img.imgUrl"  alt="">
                            </div>
                        </li>
                        <li class="baozhuang actives" id="li2"  v-if="dataForm.state===2">
                            <div class="guiGebox">
                                <div class="guiGe" v-for="(attrGroup,gIndex) in product.spuAttrGroup" :key="gIndex">
                                    <h3>{{attrGroup.attrGroupName}}</h3>
                                    <dl>
                                        <div v-for="(attr,aIndex) in attrGroup.attrs" :key="aIndex">
                                            <dt>{{attr.attrName}}</dt>
                                            <dd>{{attr.attrValue}}</dd>
                                        </div>
                                    </dl>
                                </div>
                            </div>
                        </li>
                        <!--包装-->
                        <li class="baozhang actives" id="li3"  v-if="dataForm.state===3">
                            <div class="oBox">
                                <div class="shuoHou">
                                    <div class="baoZhang">
                                        <h2>售后保障</h2>
                                    </div>
                                </div>
                                <!--厂家服务-->

                                <div class="lianBao">
                                </div>
                            </div>
                        </li>
                        <!--评价-->
                        <li class="PINgjia actives" id="li4"  v-if="dataForm.state===4">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="Fixedbian">
        <ul>
            <li class="li1"><a class="aaa" href="#">顶部</a></li>
        </ul>
    </div>
    <div class="gouwuchexiaguo">
        <img src="~@/assets/img/detail/44.png" />
        <span>购物车还没有商品，赶紧选购吧！</span>
    </div>
    </body>

</template>

<script>
import $ from 'jquery'
import {swiper,swiperSlide} from "vue-awesome-swiper";
import 'swiper/dist/css/swiper.css'
function  zoomImg(imgbox, hoverbox, l, t, x, y, h_w, h_h, showbox) {
    let moveX = x - l - (h_w/2);
    //鼠标区域距离
    let moveY = y - t - (h_h/2);
    //鼠标区域距离
    if(moveX < 0) {
        moveX = 0
    }
    if(moveY < 0) {
        moveY = 0
    }
    if(moveX > imgbox.offsetWidth - h_w) {
        moveX = imgbox.offsetWidth - h_w
    }
    if(moveY > imgbox.offsetHeight - h_h) {
        moveY = imgbox.offsetHeight - h_h
    }
    let zoomX = showbox.offsetWidth / imgbox.offsetWidth
    //求图片比例
    let zoomY = showbox.offsetHeight / imgbox.offsetHeight
    showbox.style.left=(-(moveX * zoomX))+"px"
    showbox.style.top=(-(moveY * zoomY))+"px"
    hoverbox.style.left=(moveX)+"px"
    hoverbox.style.top=(moveY)+"px"
    //确定位置
}
export default {
    components: {
        swiper,
        swiperSlide
    },
    data(){
        return{
            swiperOption: {
                loop: false,
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev"
                }
            },
            dataForm:{
                showImageIndex:0,
                count: 1,
                state:1,
                saleAttrValue:[],
                sku:{
                    skuTitle:"华为 HUAWEI Mate 10 6GB+128GB 亮黑色 移动联通电信4G手机 双卡双待",
                    price:4499,
                    stock:1000,
                    images:[
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626Ne3c69b70.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                        require("@/assets/img/detail/59ded626N7c4cb0a3.jpg"),
                    ],
                }
            },
            product:{
                id:1,
                spuName:"超级手机",
                spuDescription:"华为 HUAWEI Mate 10 6GB+128GB 亮黑色 移动联通电信4G手机 双卡双待",
                catalogId:225,
                brandId:1,
                weight: 10,
                images:[],
                descript:[],
                spuAttrGroup:[
                    {
                        attrGroupName:"主体",
                        attrs:[
                            {
                                attrName:"品牌",
                                attrValue: "华为"
                            },
                            {
                                attrName:"型号",
                                attrValue: "华daw为"
                            },
                            {
                                attrName:"入网型号",
                                attrValue: "dawda"
                            },
                            {
                                attrName:"CPU型号",
                                attrValue: "dadaw"
                            },
                            {
                                attrName:"擦擦我的品牌",
                                attrValue: "华为dawd"
                            }
                        ]
                    }
                ],
                bounds:{},
                skus:[],
                saleAttrs:[
                    {attrId:1,attrName:"运行内存",valueSelect: "4GB;6GB;8GB;16GB;32GB"}
                ],
            }
        }
    },
    methods:{
        getProduct(){
            this.$axios
                .post(this.$target+`/product/spuinfo/getProduct/17`, {})
                .then(res => {

                    this.product=res.data.data
                    this.dataForm.sku=this.product.skus[0]
                    console.log(this.product)
                })
                .catch(err => {
                    return Promise.reject(err);
                });
        },
        selectSaleValue(saleIndex,value){
            this.dataForm.saleAttrValue[saleIndex]={
                attrId:this.product.saleAttrs[saleIndex].attrId,
                attrValue: value
            }
            this.selectSku()
        },
        selectSku(){
        },
        zoomHoverMouseOver(e){
            let imgbox=this.$refs.imgbox
            let hoverbox= this.$refs.hoverbox
            let showbox= this.$refs.showbox
            let x = e.pageX;
            let y = e.pageY;
            let l = imgbox.getBoundingClientRect().left
            let t = imgbox.getBoundingClientRect().top
            console.log()
            let w = hoverbox.offsetWidth
            let h = hoverbox.offsetHeight
            $(".hoverbox,.showbox").show();
            hoverbox.style.opacity="0.3"
            setTimeout(function() {
                zoomImg(imgbox, hoverbox, l, t, x, y, w, h, showbox)
            }, 1)
        },
        zoomHoverMouseMove(e){
            let imgbox=this.$refs.imgbox
            let hoverbox= this.$refs.hoverbox
            let showbox= this.$refs.showbox
            let x = e.pageX;
            let y = e.pageY;
            let l = imgbox.getBoundingClientRect().left
            let t = imgbox.getBoundingClientRect().top
            let w = hoverbox.offsetWidth
            let h = hoverbox.offsetHeight
            setTimeout(function() {
                zoomImg(imgbox, hoverbox, l, t, x, y, w, h, showbox)
            }, 1)
        },
        zoomHoverMouseOut(){
            let hoverbox= this.$refs.hoverbox
            let showbox= this.$refs.showbox
            showbox.parentElement.style.display="none"
            hoverbox.style.display="none"
        }
    }
}
</script>

<style src="@/assets/css/detail/detail.css" scoped>

</style>