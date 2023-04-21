<template>
<body class="mainIndex">
    <header>
        <!--头部广告-->
<!--        <div class="head">-->
<!--            <a href="#"><img src="~@/assets/img/index/img_01.png"  alt=""/></a>-->
<!--            <p>X</p>-->
<!--        </div>-->
        <!--头部-->
        <div class="header_head">
            <div class="header_head_box">
                <b class="header_head_p" @mouseenter="openAreaSelector(true)" @mouseleave="openAreaSelector(false)">
                    <a href="#">
                        <img src="~@/assets/img/index/img_05.png" style="border-radius: 50%;" alt=""/>
                        北京
                    </a>
                    <div class="header_head_p_cs">
                        <a href="#" v-for="(area,areaIndex) in dataResp.areas" :key="areaIndex">{{area}}</a>
                    </div>
                </b>
                <ul>
                    <li >
                        <a v-if="$store.getters.getJWT===''" href="#/login">你好，请登录</a>
                        <a v-else href="#">你好，{{$store.getters.getUserName}}</a>
                    </li>
                    <li v-if="$store.getters.getJWT===''">
                        <a href="#/register" class="li_2">免费注册</a>
                    </li>
                    <span>|</span>
                    <li>
                        <a href="#">我的订单</a>
                    </li>
                </ul>
            </div>
        </div>
        <!--搜索导航-->
        <div class="header_sous">
            <!--搜索框-->
            <div class="header_form">
                <input id="searchText" type="text" placeholder="" v-model="searchKeyword" />
                <span style="background: url(~@/assets/img/index/img_12.png) 0 -1px;"></span>
                <!--<button><i class="glyphicon"></i></button>-->
                <a href="#" ><img src="~@/assets/img/index/img_09.png" @click="search()"  alt=""/></a>
            </div>
            <!--购物车-->
            <div class="header_ico" @mouseenter="openShopCart(true)" @mouseleave="openShopCart(false)">
                <div class="header_gw" >
                    <img src="~@/assets/img/index/img_15.png"  alt=""/>
                    <span><a href="#/shopCart">我的购物车</a></span>
                    <span>0</span>
                </div>
                <div class="header_ko">
                    <div v-if="$store.getters.getJWT===''">
                        <div class="no_login">
                            <p>无法查看购物车，请先<a href="#/login">登录</a>！</p>
                        </div>
                    </div>
                    <div v-else>
                        <div class="cart_no_product" v-if="dataResp.shopCart.length===0">
                            <p>购物车中还没有商品，赶紧选购吧！</p>
                        </div>
                        <div class="cart_have_product" v-else>

                        </div>
                    </div>
                </div>
            </div>
            <!--搜索框下方的热词-->
            <div class="header_form_nav">
                <ul>
                    <li v-for="(hotKey,hotKeyIndex) in dataResp.searchHotKeys" :key="hotKeyIndex">
                        <a href="#">{{hotKey}}</a>
                    </li>
                </ul>
            </div>
            <nav>
                <ul>
                    <li>
                        <a href="#">秒杀</a>
                    </li>
                    <li>
                        <a href="#">优惠券</a>
                    </li>
                    <li>
                        <a href="#">闪购</a>
                    </li>
                    <li>
                        <a href="#">拍卖</a>
                    </li>
                </ul>
                <div class="spacer">|</div>
                <ul>
                    <li>
                        <a href="#">服饰</a>
                    </li>
                    <li>
                        <a href="#">超市</a>
                    </li>
                    <li>
                        <a href="#">生鲜</a>
                    </li>
                    <li>
                        <a href="#">全球购</a>
                    </li>
                </ul>
                <div class="spacer">|</div>
                <ul>
                    <li>
                        <a href="#">金融</a>
                    </li>
                </ul>
            </nav>
            <!--搜索框右侧图标-->
<!--            <div class="right">-->
<!--                <a href="#"><img src="~@/assets/img/index/img_21.png" /></a>-->
<!--            </div>-->
        </div>
        <!--轮播主体内容-->
        <div class="header_main">
            <div class="header_banner">
                <!--分类表-->
                <div class="header_main_left">
                    <ul>
                        <li>
                            <a href="#" class="header_main_left_a" ctg-data="3"><b>家用电器</b></a>
                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="2,4"><b>手机</b> / <b>运营商</b> / <b>数码</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="6"><b>电脑</b> / <b>办公</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="5,7"><b>家居</b> / <b>家具</b> / <b>家装</b> / <b>厨具</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a"  ctg-data="9"><b>男装</b> / <b>女装</b> / <b>童装</b> / <b>内衣</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a"  ctg-data="8"><b>美妆个护 </b>/ <b>宠物</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a"  ctg-data="11,13,10,15"><b>鞋</b> / <b>箱包</b> / <b>钟表</b> / <b>珠宝</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a"  ctg-data="17"> <b>运动</b> / <b>户外</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="16,21"><b>汽车</b> / <b>汽车用品</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="12,18"><b>母婴</b> / <b>玩具乐器</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="14,20"><b>食品</b> / <b>酒类</b> / <b>生鲜</b> / <b>特产</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ><b>礼品鲜花</b> / <b>农资绿植</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a"><b>医药保健</b> / <b>计生情趣</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="1"><b>图书</b> / <b>音像</b>/ <b>电子书</b></a>

                        </li>
                        <li class="header_li2">
                            <a href="#" class="header_main_left_a" ctg-data="19"><b>彩票</b> / <b>酒店</b> / <b>旅游</b> / <b>生活</b></a>

                        </li>

                    </ul>

                </div>
                <div class="header_main_center">
                    <!--轮播图-->
                    <div class="swiper-container swiper1">
                        <swiper :options="swiperOption" class="swiper-wrapper">
                            <swiper-slide class="swiper-slide" v-for="(swiperImg,swiperImgIndex) in dataResp.swiperImages" :key="swiperImgIndex">
                                <a :href="swiperImg.href"><img :src="swiperImg.imgUrl"  alt=""/></a>
                            </swiper-slide>
                            <div class="swiper-pagination"  slot="pagination"></div>
                            <div class="swiper-button-prev" slot="button-prev"></div>
                            <div class="swiper-button-next" slot="button-next"></div>
                            <div class="swiper-scrollbar"   slot="scrollbar"></div>
                        </swiper>
                    </div>
                    <!--轮播图下方两张图片-->
                    <div class="header_main_center_b">
                        <a :href="dataResp.swiperBottomImages[0].href"><img :src="dataResp.swiperBottomImages[0].imgUrl"  alt=""/></a>
                        <a :href="dataResp.swiperBottomImages[1].href"><img :src="dataResp.swiperBottomImages[1].imgUrl" alt=""/></a>
                    </div>
                </div>
                <div class="header_main_right">
                    <div class="header_main_right_user">
                        <div class="user_info">
                            <div class="user_info_tou">
                                <a href="#"><img class="" src="~@/assets/img/index/touxiang.png" alt=""></a>
                            </div>
                            <div class="user_info_show">
                                <p class="">Hi, 欢迎来到!</p>
                                <p>
                                    <a href="#/login" v-if="$store.getters.getJWT===''">登录</a>
                                    <a href="#/register" v-if="$store.getters.getJWT===''">注册</a>
                                </p>
                            </div>
                        </div>
                        <div class="user_info_hide">
                            <a href="#">新人福利</a>
                            <a href="#">PLUS会员</a>
                        </div>
                    </div>
                    <div class="header_main_right_new">
                        <div class="header_new">
                            <div class="header_new_t" v-if="DomState.promotionState">
                                <p class="active">
                                    <a href="#" @mouseenter="DomState.promotionState=true">促销</a>
                                </p>
                                <p>
                                    <a href="#" @mouseenter="DomState.promotionState=false">公告</a>
                                </p>
                                <a href="#">更多</a>
                            </div>
                            <div class="header_new_t" v-else>
                                <p>
                                    <a href="#" @mouseenter="DomState.promotionState=true">促销</a>
                                </p>
                                <p class="active">
                                    <a href="#" @mouseenter="DomState.promotionState=false">公告</a>
                                </p>
                                <a href="#">更多</a>
                            </div>
                            <div class="header_new_connter">
                                <div class="header_new_connter_1" v-if="DomState.promotionState">
                                    <ul>
                                        <li v-for="(salePromotion,index) in dataResp.salePromotions" :key="index">
                                            <a :href="salePromotion.href">{{salePromotion.description}}</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="header_new_connter_1" v-else>
                                    <ul>
                                        <li v-for="(notice,index) in dataResp.announcements" :key="index">
                                            <a :href="notice.href">{{notice.description}}</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="header_main_right_ser">
                        <div class="ser_box">
                            <ul>
                                <li class="ser_box_item">
                                    <a href="#">
                                        <img src="~@/assets/img/index/huafei.png"  alt=""/>
                                        <span>话费</span>
                                    </a>
                                </li>
                                <li class="ser_box_item">
                                    <a href="#">
                                        <img src="~@/assets/img/index/jipiao.png"  alt=""/>
                                        <span>机票</span>
                                    </a>
                                </li>
                                <li class="ser_box_item">
                                    <a href="#">
                                        <img src="~@/assets/img/index/jiudian.png"  alt=""/>
                                        <span>酒店</span>
                                    </a>
                                </li>
                                <li class="ser_box_item">
                                    <a href="#">
                                        <img src="~@/assets/img/index/youxi.png"  alt=""/>
                                        <span>游戏</span>
                                    </a>
                                </li>
                                <li class="ser_box_item1">
                                    <a href="#">
                                        <img src="~@/assets/img/index/qiyegou.png"  alt=""/>
                                        <span>企业购</span>
                                    </a>
                                </li>
                                <li class="ser_box_item1">
                                    <a href="#">
                                        <img src="~@/assets/img/index/jiayouka.png"  alt=""/>
                                        <span>加油卡</span>
                                    </a>
                                </li>
                                <li class="ser_box_item1">
                                    <a href="#">
                                        <img src="~@/assets/img/index/dianyingpiao.png"  alt=""/>
                                        <span>电影票</span>
                                    </a>
                                </li>
                                <li class="ser_box_item1">
                                    <a href="#">
                                        <img src="~@/assets/img/index/huochepiao.png" style="height:20px;"  alt=""/>
                                        <span>火车票</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class="section_second">
        <!-- 第一层 -->
        <div class="section_second_header">
            <p class="section_second_header_img"></p>
            <div class="section_second_header_left">
                <p></p>
                <span class="">秒杀</span>
                <span>总有你想不到的低价</span>
                <span>
        </span>
            </div>
            <div class="section_second_header_right">
                <p>当前场次</p>
                <span class="section_second_header_right_hours">00</span>
                <span class="section_second_header_right_mao">：</span>
                <span class="section_second_header_right_minutes">00</span>
                <span class="section_second_header_right_mao">：</span>
                <span class="section_second_header_right_second">00</span>
                <p>后结束</p>
            </div>
        </div>
        <div class="section_second_list">
            <div class="swiper-container swiper_section_second_list_left" @mouseenter="onMsProductHover(true)" @mouseleave="onMsProductHover(false)">
                <swiper :options="msSwiperOption" class="swiper-wrapper">
                    <swiper-slide class="swiper-slide"  v-for="msIndex in Math.ceil(dataResp.msProducts.length/5.0)" :key="msIndex">
                        <ul>
                            <li v-for="productIndex in Math.min(dataResp.msProducts.length-(msIndex-1)*5,5)" :key="productIndex" >
                                <img :src="dataResp.msProducts[(msIndex-1)*5+productIndex-1].defaultImg" alt="">
                                <p>{{dataResp.msProducts[(msIndex-1)*5+productIndex-1].spuDescription}}</p>
                                <span>¥{{dataResp.msProducts[(msIndex-1)*5+productIndex-1].disPrice}}</span><s>¥{{dataResp.msProducts[(msIndex-1)*5+productIndex-1].price}}</s>
                            </li>
                        </ul>
                        <div class="swiper-button-prev second_list">
                            <p></p>
                        </div>
                        <div class="swiper-button-next second_list">
                            <p></p>
                        </div>
                    </swiper-slide>
                </swiper>

            </div>
            <ul class="section_second_list_right">
                <li>
                    <img src="~@/assets/img/index/section_second_list_right_img.jpg" alt="">
                </li>
                <li>
                    <img src="~@/assets/img/index/section_second_list_right_img.png" alt="">
                </li>
                <div class="section_second_list_right_button">
                    <p class="section_second_list_right_button_active"></p>
                    <p></p>
                </div>
            </ul>
        </div>


    </div>
</body>
</template>

<script>
import $ from 'jquery'
import {swiper,swiperSlide} from "vue-awesome-swiper";
import 'swiper/dist/css/swiper.css'
export default {
    components: {
        swiper,
        swiperSlide
    },
    data(){
        return{
            searchKeyword:"",
            swiperOption: {
                loop: true,
                autoplay: {
                    delay: 3000,
                    stopOnLastSlide: false,
                    disableOnInteraction: false
                },
                // 显示分页
                pagination: {
                    el: ".swiper-pagination",
                    clickable: true //允许分页点击跳转
                },
                // 设置点击箭头
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev"
                }
            },
            msSwiperOption: {
                loop: true,
                navigation: {
                    nextEl: ".swiper-button-next",
                    prevEl: ".swiper-button-prev"
                }
            },
            dataResp:{
                areas:['北京','上海','天津','重庆', '河北', '山西', '河南', '辽宁', '吉林', '黑龙江',
                    '内蒙古', '江苏', '山东', '安徽', '浙江', '福建', '湖北', '湖南', '广东', '广西',
                    '江西', '四川', '海南', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏',
                    '新疆', '港澳', '台湾', '钓鱼岛', '海外'],
                searchHotKeys:["满999减300","金立S11","农用物资","保暖特惠","洗衣机节","七度空间卫生巾","自动波箱油","超市"],
                swiperImages:[
                    {href:'#',imgUrl:require("@/assets/img/index/lunbo1.png")},
                    {href:'#',imgUrl:require("@/assets/img/index/lunbo2.png")},
                    {href:'#',imgUrl:require("@/assets/img/index/lunbo3.png")},
                    {href:'#',imgUrl:require("@/assets/img/index/lunbo4.png")},
                    {href:'#',imgUrl:require("@/assets/img/index/lunbo5.png")},
                    {href:'#',imgUrl:require("@/assets/img/index/lunbo6.png")}
                ],
                swiperBottomImages:[
                    {href:'#',imgUrl:require("@/assets/img/index/5a13bf0bNe1606e58.jpg")},
                    {href:'#',imgUrl:require("@/assets/img/index/5a154759N5385d5d6.jpg")}
                ],
                msProducts: [
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img1.jpg"),
                        spuDescription:"花王 (Merries) 妙而舒 纸尿裤 大号 L54片 尿不湿（9-14千克） （日本官方直采） 花王 (Merries) 妙而舒 纸尿裤 大号 L54片 尿不湿（9-14千克)",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img2.jpg"),
                        spuDescription:"华为mate9 4GB+32GB版 月光银 移动联通电信4G手机 双卡",
                        price:6349.90,
                        disPrice:4349.90
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img3.jpg"),
                        spuDescription:"超能 植翠低泡洗衣液（鲜艳亮丽）2kg/袋装（新老包装随机",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img4.jpg"),
                        spuDescription:"长城（GreatWall）红酒 特选5年橡木桶解百纳干红葡萄酒 整",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img5.jpg"),
                        spuDescription:"惠普(HP)暗影精灵2代Pro 15.6英寸游戏笔记本电脑(i5-7300H",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img6.jpg"),
                        spuDescription:"Apple iMac 21.5英寸一体机（2017新款四核Core i5 处理器/8GB内存/1TB/RP555显卡/4K屏 MNDY2CH/A） Apple iMac 21.5英寸一体机（2017新款四核Core i5 处理",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img7.jpg"),
                        spuDescription:"飞利浦（PHILIPS）电动牙刷HX6761/03亮白型成人充电式声波震动牙刷粉色 飞利浦（PHILIPS）电动牙刷HX6761/03亮白型成人充电式声波",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img8.jpg"),
                        spuDescription:"美的(Midea) 258升 变频智能三门冰箱 一级能效 风冷无霜 中门",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img9.jpg"),
                        spuDescription:"【第二件减50元】蒙羊 内蒙古羔羊羊肋排 2.4斤",
                        price:99.9,
                        disPrice:83.9
                    },
                    {
                        defaultImg:require("@/assets/img/index/section_second_list_img9.jpg"),
                        spuDescription:"【第二件减50元】蒙羊 内蒙古羔羊羊肋排 2.4斤",
                        price:99.9,
                        disPrice:83.9
                    },
                ],
                shopCart:[],
                salePromotions:[
                    {href:"#",description:"全民纸巾大作战"},
                    {href:"#",description:"家具建材满999减300元"},
                    {href:"#",description:"黑科技冰箱，下单立减千元"},
                    {href:"#",description:"抢102减101神券！"},
                    {href:"#",description:"抢102减101神券！"},
                    {href:"#",description:"抢102减101神券！"},
                ],
                announcements:[
                    {href:"#",description:"关于召回普利司通（天津）轮胎有限公司2个规格乘用车轮胎的公告"},
                    {href:"#",description:"物流推出配送员统一外呼电话\"95056\""},
                    {href:"#",description:"天府大件运营中心开仓公告"},
                    {href:"#",description:"大件物流“送装一体”服务全面升级！"},
                ]
            },
            DomState:{
                //促销公告选项卡状态
                promotionState:true
            }
        }
    },
    methods:{
        search(){
          this.$router.push({name:"Search",params:{keyword:this.searchKeyword}})
        },
        /*地区选择框*/
        openAreaSelector(flag){
            if(flag){
                $('.header_head_p').css({
                    background: "#fff"
                }).children(".header_head_p_cs").stop(true).show(100)
            }else{
                $('.header_head_p').css({
                    background: "#E3E4E5"
                }).children(".header_head_p_cs").stop(true).hide(100)
            }
        },
        openShopCart(flag){
            if(flag){
                $('.header_gw').next('.header_ko').stop(true).fadeIn(100)
            }else {
                $('.header_gw').next('.header_ko').stop(true).fadeOut(100)
            }
        },
        onMsProductHover(flag){
            if(flag){
                $(".second_list").css("display", "block")
            }else{
                $(".second_list").css("display", "none")
            }
        }
    }
}
</script>

<style src="@/assets/css/index/GL.css" scoped>
</style>