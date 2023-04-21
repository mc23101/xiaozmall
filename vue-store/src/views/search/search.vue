<template>
    <body>
    <!--头部-->
    <div class="header_head">
        <div class="header_head_box">
            <b class="header_head_p">
                <div style="overflow: hidden">
                    <a href="#/" class="header_head_p_a1" style="width:73px;">
                        首页
                    </a>
                </div>
            </b>
            <ul>
                <li>
                    <a href="#/login" class="li_2">你好，请登录</a>
                </li>
                <li>
                    <a href="#/register">免费注册</a>
                </li>
                <span>|</span>
                <li>
                    <a >我的订单</a>
                </li>
            </ul>
        </div>
    </div>

    <!--搜索导航-->
    <div class="header_sous">
        <div class="logo">
            <a ><img src="~@/assets/img/search/logo1.jpg" alt=""></a>
        </div>
        <div class="header_form">
            <input type="text" placeholder="请输入搜索内容" v-model="searchParam.keyword" />
            <a @click="search()">搜索</a>
        </div>
        <div class="header_ico">
            <div class="header_gw">
                <span><a >我的购物车</a></span>
                <img src="~@/assets/img/search/settleup-@1x.png"  alt=""/>
                <span>0</span>
            </div>
            <div class="header_ko">
                <p>购物车中还没有商品，赶紧选购吧！</p>
            </div>
        </div>
        <div class="header_form_nav">
            <ul>
                <li>
                    <a >小张商城之家</a>
                </li>
                <li>
                    <a >小张商城专卖店</a>
                </li>
                <li>
                    <a >平板</a>
                </li>
                <li>
                    <a >电脑</a>
                </li>
                <li>
                    <a >ipad</a>
                </li>
            </ul>
        </div>
        <nav>
            <ul>
                <li class="nav_li1">
                    <a >全部商品分类</a>
                </li>
                <li class="nav_li">
                    <a >服装城</a>
                </li>
                <li class="nav_li">
                    <a >没装馆</a>
                </li>
                <li class="nav_li">
                    <a >超市</a>
                </li>
                <li class="nav_li">
                    <a >生鲜</a>
                </li>
            </ul>
            <div class="spacer">|</div>
            <ul>
                <li class="nav_li">
                    <a >全球购</a>
                </li>
                <li class="nav_li">
                    <a >闪购</a>
                </li>
                <li class="nav_li">
                    <a >拍卖</a>
                </li>
            </ul>
            <div class="spacer">|</div>
            <ul>
                <li class="nav_li">
                    <a >金融</a>
                </li>
            </ul>
        </nav>
        <div class="header_main_left">
            <ul>
                <li>
                    <a  class="header_main_left_a"><b>家用电器</b></a>
                </li>
                <li class="header_li2">
                    <a  class="header_main_left_a"><b>手机</b> / <b>运营商</b> / <b>数码</b></a>
                    <div class="header_main_left_main">
                        <div class="header_sj">
                            <a  class="header_sj_a">玩3c</a>
                            <a  class="header_sj_a">手机频道</a>
                            <a  class="header_sj_a">网上营业厅</a>
                            <a  class="header_sj_a">配件选购中心</a>
                            <a  class="header_sj_a">企业购</a>
                            <a  class="header_sj_a">以旧换新</a>
                        </div>
                        <ol class="header_ol">
                            <a  style="color: #111;" class="aaa">手机通讯 ></a>
                            <li>
                                <a  style="color: #999;">手机</a>
                                <a  style="color: #999;">对讲机</a>
                                <a  style="color: #999;">手机维修</a>
                                <a  style="color: #999;">以旧换新</a>
                            </li>
                            <a  style="color: #111;" class="aaa">运营商 ></a>
                            <li>
                                <a  style="color: #999;">合约机</a>
                                <a  style="color: #999;">固话宽带</a>
                                <a  style="color: #999;">办套餐</a>
                                <a  style="color: #999;">从话费/流量</a>
                                <a  style="color: #999;">中国电信</a>
                                <a  style="color: #999;">中国移动</a>
                                <a  style="color: #999;">中国联通</a>
                                <a  style="color: #999;">小张商城通信</a>
                                <a  style="color: #999;">170选号</a>
                            </li>
                            <a style="color: #111;" class="aaa">手机配件 ></a>
                            <li style="height: 60px;">

                                <a  style="color: #999;">手机壳</a>
                                <a  style="color: #999;">贴膜</a>
                                <a  style="color: #999;">手机储存卡</a>
                                <a  style="color: #999;">数据线</a>
                                <a  style="color: #999;">存电器</a>
                                <a  style="color: #999;">手机耳机</a>
                                <a  style="color: #999;">创业配件</a>
                                <a  style="color: #999;">手机饰品</a>
                                <a  style="color: #999;">手机电池</a>
                                <a  style="color: #999;">苹果周边</a>
                                <a  style="color: #999;">移动电源</a>
                                <a  style="color: #999;">蓝牙耳机</a>
                                <a  style="color: #999;">手机支架</a>
                                <a  style="color: #999;">车载配件</a>
                                <a  style="color: #999;">拍照配件</a>

                            </li>
                            <a  style="color: #111;" class="aaa">摄影摄像 ></a>
                            <li style="height: 60px;">

                                <a  style="color: #999;">数码相机</a>
                                <a  style="color: #999;">单电/微单相机</a>
                                <a  style="color: #999;">单反相机</a>
                                <a  style="color: #999;">拍立得</a>
                                <a  style="color: #999;">运动相机</a>
                                <a  style="color: #999;">摄像机</a>
                                <a  style="color: #999;">镜头</a>
                                <a  style="color: #999;">户外器材</a>
                                <a  style="color: #999;">影棚器材</a>
                                <a  style="color: #999;">冲印服务</a>
                                <a  style="color: #999;">数码相框</a>
                            </li>
                            <a  style="color: #111;" class="aaa">数码配件 ></a>
                            <li style="height: 60px;">

                                <a  style="color: #999;">三脚架/云台</a>
                                <a  style="color: #999;">相机包</a>
                                <a  style="color: #999;">滤镜</a>
                                <a  style="color: #999;">散光灯/手柄</a>
                                <a  style="color: #999;">相机清洁</a>
                                <a  style="color: #999;">机身附件</a>
                                <a  style="color: #999;">镜头附件</a>
                                <a  style="color: #999;">读卡器</a>
                                <a  style="color: #999;">支架</a>
                                <a  style="color: #999;">电池/存电器</a>

                            </li>
                            <a  style="color: #111;" class="aaa">影音娱乐 ></a>
                            <li>

                                <a  style="color: #999;">耳机/耳麦</a>
                                <a  style="color: #999;">音箱/音响</a>
                                <a  style="color: #999;">智能音箱</a>
                                <a  style="color: #999;">便携/无线音箱</a>
                                <a  style="color: #999;">收音机</a>
                                <a  style="color: #999;">麦克风</a>
                                <a  style="color: #999;">MP3/MP4</a>
                                <a  style="color: #999;">专业音频</a>
                            </li>
                            <a  style="color: #111;" class="aaa">智能设备 ></a>
                            <li style="height: 60px;">

                                <a  style="color: #999;">智能手环</a>
                                <a  style="color: #999;">智能手表</a>
                                <a  style="color: #999;">智能眼镜</a>
                                <a  style="color: #999;">智能机器人</a>
                                <a  style="color: #999;">运动跟踪器</a>
                                <a  style="color: #999;">健康监测</a>
                                <a  style="color: #999;">智能配饰</a>
                                <a  style="color: #999;">智能家居</a>
                                <a  style="color: #999;">体感车</a>
                                <a  style="color: #999;">无人机</a>
                                <a  style="color: #999;">其他配件</a>
                            </li>
                            <a  style="color: #111;" class="aaa">电子教育 ></a>
                            <li>
                                <a  style="color: #999;">学生平板</a>
                                <a  style="color: #999;">点读机</a>
                                <a  style="color: #999;">早教益智</a>
                                <a  style="color: #999;">录音笔</a>
                                <a  style="color: #999;">电纸书</a>
                                <a  style="color: #999;">电子词典</a>
                                <a  style="color: #999;">复读机</a>
                            </li>
                        </ol>
                        <div class="header_r">
                            <div class="header_r_tu">
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                                <a ><img src="~@/assets/img/search/56b2f385n8e4eb051.jpg" /></a>
                            </div>
                            <div class="header_r_tu1">
                                <a ><img src="~@/assets/img/search/JD_ash7 - 副本.png" /></a>
                                <a ><img src="~@/assets/img/search/JD_ash6.png" /></a>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>电脑</b> / <b>办公</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>家居</b> / <b>家具</b> / <b>家装</b> / <b>厨具</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>男装</b> / <b>女装</b> / <b>童装</b> / <b>内衣</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>美妆个护 </b>/ <b>宠物</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>女鞋</b> / <b>箱包</b> / <b>钟表</b> / <b>珠宝</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>男鞋</b> / <b>运动</b> / <b>户外</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>汽车</b> / <b>汽车用品</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>母婴</b> / <b>玩具乐器</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>食品</b> / <b>酒类</b> / <b>生鲜</b> / <b>特产</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>礼品鲜花</b> / <b>农资绿植</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>医药保健</b> / <b>计生情趣</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>图书</b> / <b>音箱</b>/ <b>电子书</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>机票</b> / <b>酒店</b> / <b>旅游</b> / <b>生活</b></a>
                </li>
                <li>
                    <a  class="header_main_left_a"><b>理财</b> / <b>众筹</b> / <b>白条</b> / <b>保险</b></a>
                </li>
            </ul>
        </div>
    </div>

    <hr style="border: 1px solid red;margin-top: -7px;">

    <!--热卖促销-->
    <div class="JD_temai">
        <div class="JD_main">
            <div class="JD_left">
                <div class="hd">
                    热卖推荐
                </div>
                <div class="bd mc">
                    <ul class="mc">
                        <li v-for="(hotProduct,hotIndex) in ads.hot" :key="hotIndex">
                            <a  class="mc_a"><img :src="hotProduct.defaultImg" alt=""></a>
                            <div class="mc_div">
                                <a  class="mc_div_a1">
                                    <em>{{hotProduct.spuDescription}}</em>
                                </a>
                                <p>
                                    <strong>
                                        <em class="number J-p-5963064">￥{{hotProduct.price.toFixed(2)}}</em>
                                    </strong>
                                </p>
                                <a  class="mc_div_a2">立即抢购</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="JD_right">
                <div class="hd"> 促销活动</div>
                <div class="bd">
                    <ul>
                        <li v-for="(promotion,pIndex) in ads.promotion" :key="pIndex"> . <a :href="promotion.href">{{promotion.title}}</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!--面包屑-->
    <div class="JD_ipone">
        <div class="JD_ipone_bar">
            <div class="JD_ipone_one a">
                <a >全部结果</a>
            </div>
            <i><img src="~@/assets/img/search/right-@1x.png" alt=""></i>
            <div class="JD_ipone_one level1">
                <a  class="qqq">手机通讯录 <img src="~@/assets/img/search/down-@1x.png" alt=""></a>
                <div>
                    <a >手机通讯</a>
                    <a >运营商</a>
                    <a >手机配件</a>
                    <a >手机服务</a>
                </div>
            </div>
            <i><img src="~@/assets/img/search/right-@1x.png" alt=""></i>
            <div class="JD_ipone_one level2">
                <a  class="qqq">手机 <img src="~@/assets/img/search/down-@1x.png" alt=""></a>
                <div>
                    <a >手机</a>
                    <a >老人机</a>
                    <a >对讲机</a>
                    <a >女性手机</a>
                    <a >超续航手机</a>
                    <a >全面屏手机</a>
                    <a >拍照手机</a>
                    <a >游戏手机</a>
                </div>
            </div>
            <i><img src="~@/assets/img/search/right-@1x.png" alt=""></i>
            <div class="JD_ipone_one level3">
                <a  class="qqq">手机 <img src="~@/assets/img/search/down-@1x.png" alt=""></a>
                <div>
                    <a >手机</a>
                    <a >老人机</a>
                    <a >对讲机</a>
                    <a >女性手机</a>
                    <a >超续航手机</a>
                    <a >全面屏手机</a>
                    <a >拍照手机</a>
                    <a >游戏手机</a>
                </div>
            </div>
            <i><img src="~@/assets/img/search/right-@1x.png" alt="" v-if="searchParam.minPrice!==''&&searchParam.maxPrice!==''"></i>
            <div class="JD_ipone_one attr" v-if="searchParam.minPrice!==''&&searchParam.maxPrice!==''">
                <a>
                    <b>价格：</b>
                    <em>{{searchParam.minPrice}}-{{searchParam.maxPrice}}</em>
                    <i @click="searchParam.minPrice='';searchParam.maxPrice='';search()"></i>
                </a>
            </div>

        </div>
    </div>

<!--    商品筛选和排序-->
    <div class="JD_banner w">
        <div class="JD_nav">
            <div class="JD_selector">
                <!--手机商品筛选-->
                <div class="title">
                    <h3><em>商品筛选</em></h3>
                    <div class="st-ext">共&nbsp;<span>{{searchResult.count}}</span>个商品 </div>
                </div>
                <div class="JD_nav_logo">
                    <!--品牌-->
                    <div class="JD_nav_wrap">
                        <div class="sl_key">
                            <span>品牌：</span>
                        </div>
                        <div class="sl_value">
                            <div class="sl_value_logo">
                                <ul>
                                    <li v-for="(brand,brandIndex) in searchResult.brands" :key="brandIndex">
                                        <a>
                                            <img :src="brand.logo" alt="">
                                            <div>
                                                {{brand.name}}
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="sl_ext">
                            <a >
                                更多
                                <i style='background: url("~@/assets/img/search/search.ele.png")no-repeat 3px 7px'></i>
                                <b style='background: url("~@/assets/img/search/search.ele.png")no-repeat 3px -44px'></b>
                            </a>
                            <a >
                                多选
                                <i>+</i>
                                <span>+</span>
                            </a>
                        </div>
                    </div>
                    <!--价格-->
                    <div class="JD_pre" v-if="searchParam.minPrice===''||searchParam.maxPrice===''">
                        <div class="sl_key">
                            <span>价格：</span>
                        </div>
                        <div class="sl_value">
                            <ul>
                                <li @click="searchParam.minPrice=0;searchParam.maxPrice=499;"><a>0-499</a></li>
                                <li @click="searchParam.minPrice=500;searchParam.maxPrice=999"><a>500-999</a></li>
                                <li @click="searchParam.minPrice=1000;searchParam.maxPrice=1699" ><a>1000-1699</a></li>
                                <li @click="searchParam.minPrice=100;searchParam.maxPrice=2799"><a>1700-2799</a></li>
                                <li @click="searchParam.minPrice=2800;searchParam.maxPrice=4499"><a>2800-4499</a></li>
                                <li @click="searchParam.minPrice=4500;searchParam.maxPrice=11999"><a>4500-11999</a></li>
                                <li @click="searchParam.minPrice=12000;searchParam.maxPrice=15000"><a>12000-15000</a></li>
                                <li class="sl_value_li">
                                    <input type="text" v-model="dataForm.minPrice">
                                    <p>-</p>
                                    <input type="text" v-model="dataForm.maxPrice">
                                    <a @click="searchParam.minPrice=dataForm.minPrice;searchParam.maxPrice=dataForm.maxPrice;search()">确定</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--spu属性-->
                    <div class="JD_pre" v-for="spuAttrIndex in getSpuAttrCount()" :key="spuAttrIndex">
                        <div class="sl_key">
                            <span>{{searchResult.spuAttrs[spuAttrIndex-1].attrName}}：</span>
                        </div>
                        <div class="sl_value">
                            <ul>
                                <li v-for="(value,valueIndex) in searchResult.spuAttrs[spuAttrIndex-1].valueSelect.split(';')" :key="valueIndex"><a>{{value}}</a></li>
                            </ul>
                        </div>
                    </div>
                    <!--sku属性-->
                    <div class="JD_pre" v-for="skuAttrIndex in getSkuAttrCount()" :key="skuAttrIndex">
                        <div class="sl_key">
                            <span>{{searchResult.skuAttrs[skuAttrIndex-1].attrName}}：</span>
                        </div>
                        <div class="sl_value">
                            <ul>
                                <li v-for="(value,valueIndex) in searchResult.spuAttrs[skuAttrIndex-1].valueSelect.split(';')" :key="valueIndex"><a>{{value}}</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--排序-->
            <div class="JD_banner_main">
                <!--商品精选-->
                <div class="JD_con_left">
                    <div class="JD_con_left_bar">
                        <div class="JD_con_one" v-for="(ad,adIndex) in ads.ads" :key="adIndex">
                            <div class="mt">
                                <h3>{{ad.title}}</h3>
                            </div>
                            <div class="mc">
                                <ul>
                                    <li v-for="(product,pIndex) in ad.product" :key="pIndex">
                                        <a><img :src="product.defaultImg" alt=""></a>
                                        <a>
                                            <em>{{product.spuDescription}}</em>
                                        </a>
                                        <div class="mc_price">
                                            <strong class="price">
                                                <span class="J-p-5963064">￥{{product.price.toFixed(2)}}</span>
                                            </strong>
                                            <span class="mc-ico" title="购买本商品送赠品">
                                            <i class="goods-icons">赠品</i>
                                        </span>
                                        </div>
                                        <div class="mc_rev">
                                            已有
                                            <a  class="number">{{product.comment}}</a>
                                            人评价
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--综合排序-->
                <div class="JD_con_right">
                    <div class="filter">
                        <!--综合排序-->
                        <div class="filter_top">
                            <div class="filter_top_left">
                                <a >综合排序</a>
                                <a >销量</a>
                                <a >价格</a>
                                <a >评论分</a>
                                <a >上架时间</a>
                            </div>
                            <div class="filter_top_right">
                            <span class="fp-text">
                               <b>1</b><em>/</em><i>169</i>
                           </span>
                                <a  class="prev"> 《 </a>
                                <a  class="next"> > </a>
                            </div>
                        </div>
                        <!--收货地址-->
                        <div class="filter_bottom">
                            <div class="filter_bottom_left">

                            </div>
                            <div class="filter_bottom_right">
                                <ul>
                                    <li>
                                        <a >
                                            <i></i>
                                            小张商城配送
                                        </a>
                                    </li>
                                    <li>
                                        <a >
                                            <i></i>
                                            京尊达                                    </a>
                                    </li>
                                    <li>
                                        <a >
                                            <i></i>
                                            货到付款
                                        </a>
                                    </li>
                                    <li>
                                        <a >
                                            <i></i>
                                            仅显示有货
                                        </a>
                                    </li>
                                    <li>
                                        <a >
                                            <i></i>
                                            可配送全球
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!--排序内容-->
                        <div class="rig_tab">
                            <div  v-for="(product,pIndex) in searchResult.products" :key="pIndex">
                                <div class="ico">
                                    <i class="iconfont icon-weiguanzhu"></i>
                                    <a >关注</a>
                                </div>
                                <p class="da">
                                    <a :title="product.spuDescription">
                                        <img :src="product.defaultImg" class="dim" alt="">
                                    </a>
                                </p>
                                <ul class="tab_im">
                                    <li v-for="imgIndex in Math.min(product.images.length,6)" :key="imgIndex">
                                        <a>
                                            <img :src="product.images[imgIndex-1].imgUrl">
                                        </a>
                                    </li>
                                </ul>
                                <p class="tab_R">
                                    <span>¥{{product.price.toFixed(2)}}</span>
                                </p>
                                <p class="tab_JE">
                                    <a  :title="product.spuDescription">
                                        {{product.spuDescription}}
                                    </a>
                                </p>
                                <p class="tab_PI">已有<span>11万+</span>热门评价
                                    <a >二手有售</a>
                                </p>
                                <p class="tab_CP"><a  title="小张商城Apple产品专营店">小张商城Apple产品...</a>
                                    <a href='#' title="联系供应商进行咨询">
                                        <img src="~@/assets/img/search/xcxc.png">
                                    </a>
                                </p>
                                <div class="tab_FO">
                                    <div class="FO_one">
                                        <p>自营
                                            <span>小张商城自营,品质保证</span>
                                        </p>
                                        <p>满赠
                                            <span>该商品参加满赠活动</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--分页-->
                        <div class="filter_page">
                            <div class="page_wrap">
                            <span class="page_span1">
                                <a >
                                     上一页
                                </a>
                                <a  style="border: 0;color:#ee2222;background: #fff">1</a>
                                <a >2</a>
                                <a >3</a>
                                <a  style="border: 0;font-size: 20px;color: #999;background: #fff">...</a>
                                <a >169</a>
                                <a >
                                    下一页 >
                                </a>
                            </span>
                                <span class="page_span2">
                                <em>共<b>169</b>页&nbsp;&nbsp;到第</em>
                                <input type="number" value="1">
                                <em>页</em>
                                <a >确定</a>
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
               </div>
        </div>
    </div>

<!--    &lt;!&ndash;商品精选&ndash;&gt;-->
<!--    <div class="JD_jx">-->
<!--        <div class="JD_jx_title">-->
<!--            <div class="mt">-->
<!--                <strong class="mt-title">商品精选</strong>-->
<!--                <img src="~@/assets/img/search/u-ad.gif" alt="">-->
<!--            </div>-->
<!--            <div class="mc">-->
<!--                <ul>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <img src="~@/assets/img/search/5a25ffc7N98b35d49.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <em>【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                            <span class="mc_ico" title="购买本商品送赠品">赠品</span>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >15930</a>-->
<!--                            <span>人好评</span>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <img src="~@/assets/img/search/5a25ffc7N98b35d49.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <em>【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                            <span class="mc_ico" title="购买本商品送赠品">赠品</span>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >15930</a>-->
<!--                            <span>人好评</span>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <img src="~@/assets/img/search/5a25ffc7N98b35d49.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <em>【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                            <span class="mc_ico" title="购买本商品送赠品">赠品</span>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >15930</a>-->
<!--                            <span>人好评</span>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <img src="~@/assets/img/search/5a25ffc7N98b35d49.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <em>【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                            <span class="mc_ico" title="购买本商品送赠品">赠品</span>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >15930</a>-->
<!--                            <span>人好评</span>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <img src="~@/assets/img/search/5a25ffc7N98b35d49.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待">-->
<!--                                <em>【预约版】华为 HUAWEI 畅享7S 全面屏双摄 4GB +64GB 黑色 移动联通电信4G手机 双卡双待</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                            <span class="mc_ico" title="购买本商品送赠品">赠品</span>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >15930</a>-->
<!--                            <span>人好评</span>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                </ul>-->


<!--            </div>-->
<!--        </div>-->
<!--    </div>-->

<!--    &lt;!&ndash;猜你喜欢&ndash;&gt;-->
<!--    <div class="JD_cnxh">-->
<!--        <div class="JD_jx_title">-->
<!--            <div class="mt">-->
<!--                <strong class="mt-title">猜你喜欢</strong>-->
<!--                <a >-->
<!--                    <img src="~@/assets/img/search/update.png" alt="">-->
<!--                    换一批-->
<!--                </a>-->
<!--            </div>-->
<!--            <div class="mc">-->
<!--                <ul>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/59bf3c47n91d65c73.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <em>诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >已有80万+人评价</a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->

<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/5a28b5c6Ndec5088f.jpg" alt=""></a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <em>诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >已有80万+人评价</a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->

<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机"><img src="~@/assets/img/search/593e4de0n5ff878a4.jpg" alt=""></a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <em>诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >已有80万+人评价</a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->

<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机"><img src="~@/assets/img/search/593e4de0n5ff878a4.jpg" alt=""></a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <em>诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >已有80万+人评价</a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->

<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机"><img src="~@/assets/img/search/59c493a7N3f9b9c85 (1).jpg" alt=""></a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <em>诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >已有80万+人评价</a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->

<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机"><img src="~@/assets/img/search/59c493a7N3f9b9c85 (1).jpg" alt=""></a>-->
<!--                        </div>-->
<!--                        <div class="mc_name">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <em>诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机</em>-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_rev">-->
<!--                            <a >已有80万+人评价</a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥1999.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->

<!--                    </li>-->
<!--                </ul>-->


<!--            </div>-->
<!--        </div>-->
<!--    </div>-->

<!--    &lt;!&ndash;我的足迹&ndash;&gt;-->
<!--    <div class="JD_zuji">-->
<!--        <div class="JD_jx_title">-->
<!--            <div class="mt">-->
<!--                <strong class="mt-title">我的足迹</strong>-->
<!--                <a >-->
<!--                    更多浏览记录-->
<!--                </a>-->
<!--            </div>-->
<!--            <div class="mc">-->
<!--                <ul>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/59e58a11Nc38676d5.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥2998.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/5a28acccN73689386.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥88.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/5a1690ddN441b5dce.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥199.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/5a02bde7N7d4453b1.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥799.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/5a122dbeN044ebf19.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥599.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/59c493a7N3f9b9c85.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥699.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                        <div class="mc_img">-->
<!--                            <a  title="诺基亚 7 (Nokia 7) 4GB+64GB 黑色 全网通 双卡双待 移动联通电信4G手机">-->
<!--                                <img src="~@/assets/img/search/5a08f6f6N5bab2c1c.jpg" alt="">-->
<!--                            </a>-->
<!--                        </div>-->
<!--                        <div class="mc_price">-->
<!--                            <strong>-->
<!--                                <span>￥715.00</span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                    </li>-->
<!--                </ul>-->


<!--            </div>-->
<!--        </div>-->
<!--    </div>-->

<!--    <div style="width: 1210px;margin: 0 auto;margin-bottom: 10px"><img src="~@/assets/img/search/5a33a2e0N9a04b4af.jpg" alt=""></div>-->
<!--    &lt;!&ndash;底部&ndash;&gt;-->
<!--    <footer class="footer">-->
<!--        <div class="footer_top">-->
<!--            <ul>-->
<!--                <li>-->
<!--                    <span></span>-->
<!--                    <h3>品类齐全，轻松购物</h3>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <span></span>-->
<!--                    <h3>多仓直发，极速配发</h3>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <span></span>-->
<!--                    <h3>正品行货，精致服务</h3>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <span></span>-->
<!--                    <h3>天天低价，畅选无忧</h3>-->
<!--                </li>-->
<!--            </ul>-->
<!--        </div>-->
<!--        <div class="footer_center">-->
<!--            <ol>-->
<!--                <li>购物指南</li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">购物流程</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">会员介绍</a>-->
<!--                </li>-->
<!--                <li><a >生活旅行</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">常见问题</a>-->
<!--                </li>-->
<!--                <li><a >大家电</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">联系客服</a>-->
<!--                </li>-->
<!--            </ol>-->
<!--            <ol>-->
<!--                <li>配送方式</li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">上门自提</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">211限时达</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">配送服务查询</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">配送费收取标准</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">海外配送</a>-->
<!--                </li>-->
<!--            </ol>-->
<!--            <ol>-->
<!--                <li>支付方式</li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">货到付款</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">在线支付</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">分期付款</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">邮局汇款</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">公司转账</a>-->

<!--                </li>-->
<!--            </ol>-->
<!--            <ol>-->
<!--                <li>售后服务</li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">售后政策</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">价格保护</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">退款说明</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">返修/退换货</a>-->
<!--                </li>-->
<!--                <li><a >取消订单</a>-->
<!--                </li>-->
<!--            </ol>-->
<!--            <ol>-->
<!--                <li>特色服务</li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">夺宝岛</a>-->
<!--                </li>-->
<!--                <li><a >DIY装机</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">延保服务</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">小张商城E卡</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">小张商城通信</a>-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">小张商城gulimall+</a>-->
<!--                </li>-->
<!--            </ol>-->
<!--            <ol>-->
<!--                <li>小张商城自营覆盖区域</li>-->
<!--                <li>-->
<!--                    小张商城已向全国2661个区县提供自<br> 营配送服务，支持货到付款、-->
<!--                    <br> POS机刷卡和售后上门服务。-->
<!--                </li>-->
<!--                <li><a  style="color: rgb(114, 114, 114);">查看详情&gt;</a>-->
<!--                </li>-->
<!--            </ol>-->
<!--        </div>-->
<!--        <div class="footer_foot">-->
<!--            <p class="footer_p p1">-->
<!--                <a >关于我们</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">联系我们</a>-->
<!--                <span></span>-->
<!--                <a >联系客服</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">合作招商</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">商家帮助</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">营销中心</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">手机小张商城</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">友情链接</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">销售联盟</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">小张商城社区</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">风险监测</a>-->
<!--                <span></span>-->
<!--                <a >隐私政策</a>-->
<!--                <span></span>-->
<!--                <a >小张商城公益</a>-->
<!--                <span></span>-->
<!--                <a  style="color: rgb(114, 114, 114);">English Site</a>-->
<!--                <span></span>-->
<!--                <a >media &amp; IR</a>-->
<!--            </p>-->
<!--            <p class="footer_p">-->
<!--                <a >京公网安备 11000002000088号</a>-->
<!--                <span></span>-->
<!--                <a >京ICP证070359号</a>-->
<!--                <span></span>-->
<!--                <a >互联网药品信息服务资格证编号(京)-经营性-2014-0008</a>-->
<!--                <span></span>-->
<!--                <a >新出发京零 字第大120007号</a>-->
<!--            </p>-->
<!--            <p class="footer_p">-->
<!--                <a >互联网出版许可证编号新出网证(京)字150号</a>-->
<!--                <span></span>-->
<!--                <a >出版物经营许可证</a>-->
<!--                <span></span>-->
<!--                <a >网络文化经营许可证京网文[2014]2148-348号</a>-->
<!--                <span></span>-->
<!--                <a >违法和不良信息举报电话：4006561155</a>-->
<!--            </p>-->
<!--            <p class="footer_p">-->
<!--                <a >Copyright © 2004 - 2017  小张商城JD.com 版权所有</a>-->
<!--                <span></span>-->
<!--                <a >消费者维权热线：4006067733</a>-->
<!--                <a >经营证照</a>-->
<!--            </p>-->
<!--            <p class="footer_p">-->
<!--                <a >小张商城旗下网站:</a>-->
<!--                <a >小张商城支付</a>-->
<!--                <span></span>-->
<!--                <a >小张商城云</a>-->
<!--            </p>-->
<!--            <ul>-->
<!--                <li></li>-->
<!--                <li></li>-->
<!--                <li></li>-->
<!--                <li></li>-->
<!--                <li></li>-->
<!--                <li></li>-->
<!--            </ul>-->
<!--        </div>-->
<!--    </footer>-->

<!--    &lt;!&ndash;右侧侧边栏&ndash;&gt;-->
<!--    <div class="header_bar">-->
<!--        <div class="header_bar_box">-->
<!--            <ul>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/wo.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >小张商城会员</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/gouwuche.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >购物车</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/taoxin.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >我的关注</a>-->
<!--                    </div>-->
<!--                </li>-->

<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/shi.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >我的足迹</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/xinxi.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >我的消息</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/qianbao.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >资讯JIMI</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--            </ul>-->
<!--            <ul>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/fa3f24a70d38bd439261cb7439e517a5.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >顶部</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--                <li>-->
<!--                    <a ><img src="~@/assets/img/search/xinxi.png" /></a>-->
<!--                    <div class="div">-->
<!--                        <a >反馈</a>-->
<!--                    </div>-->
<!--                </li>-->
<!--            </ul>-->
<!--        </div>-->
<!--    </div>-->

    </body>
</template>

<script>
export default {
    data(){
        return{
            dataForm:{
              level1:"",
              level2:"",
              minPrice:"",
              maxPrice:""
            },
            ads:{
              hot:[
                  {
                      defaultImg:require("@/assets/img/search/5a28b5a1n8a5c095f.jpg"),
                      spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                      price:2999
                  },
                  {
                      defaultImg:require("@/assets/img/search/5a28b5a1n8a5c095f.jpg"),
                      spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                      price:2999
                  },
                  {
                      defaultImg:require("@/assets/img/search/5a28b5a1n8a5c095f.jpg"),
                      spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                      price:2999
                  },
              ],
              promotion:[
                  {
                      href:"#",
                      title:"红米千元全面屏手机上市"
                  },
                  {
                      href:"#",
                      title:"红米千元全面屏手机上市"
                  },
                  {
                      href:"#",
                      title:"红米千元全面屏手机上市"
                  },
                  {
                      href:"#",
                      title:"红米千元全面屏手机上市"
                  },
                  {
                      href:"#",
                      title:"红米千元全面屏手机上市"
                  }
              ],
              ads:[
                  {
                      title:"商品精选",
                      product:[
                          {
                              defaultImg:require("@/assets/img/search/59bf3c47n91d65c73.jpg"),
                              spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                              price:2699,
                              comment:1258
                          },

                          {
                              defaultImg:require("@/assets/img/search/59bf3c47n91d65c73.jpg"),
                              spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                              price:2699,
                              comment:1258
                          },
                      ]
                  },
                  {
                      title:"达人选购",
                      product:[
                          {
                              defaultImg:require("@/assets/img/search/59bf3c47n91d65c73.jpg"),
                              spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                              price:2699,
                              comment:1258
                          },

                          {
                              defaultImg:require("@/assets/img/search/59bf3c47n91d65c73.jpg"),
                              spuDescription:"华为 HUAWEI nova 2S 全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待",
                              price:2699,
                              comment:1258
                          },
                      ]
                  }
              ]
            },
            searchParam:{
                keyword:"",
                brandId:"",
                minPrice:"",
                maxPrice:"",
                sortState:"",
                pageIndex:"",
                pageSize:"",
                spuAttrs:[],
                skuAttrs:[]
            },
            searchResult:{
                count:1,
                products:[
                    {
                        id:"",
                        spuName:"",
                        spuDescription:"",
                        catalogId:"",
                        defaultImg:"",
                        price:1,
                        images:[]
                    }
                ],
                catalogs:[],
                brands:[
                    {
                        brandId:1,
                        name:"华为(HUAWEI)",
                        logo:require("@/assets/img/search/598033b4nd6055897.jpg"),
                    },
                    {
                        brandId:1,
                        name:"华为(HUAWEI)",
                        logo:require("@/assets/img/search/598033b4nd6055897.jpg"),
                    },
                    {
                        brandId:1,
                        name:"华为(HUAWEI)",
                        logo:require("@/assets/img/search/598033b4nd6055897.jpg"),
                    },
                    {
                        brandId:1,
                        name:"华为(HUAWEI)",
                        logo:require("@/assets/img/search/598033b4nd6055897.jpg"),
                    }
                ],
                spuAttrs:[
                    {
                        attrName:"内存",
                        valueSelect:"8GB;12GB;16GB"
                    },
                    {
                        attrName:"内存",
                        valueSelect:"8GB;12GB;16GB"
                    },
                    {
                        attrName:"内存",
                        valueSelect:"8GB;12GB;16GB"
                    },
                    {
                        attrName:"内存",
                        valueSelect:"8GB;12GB;16GB"
                    },
                    {
                        attrName:"内存",
                        valueSelect:"8GB;12GB;16GB"
                    }
                ],
                skuAttrs:[]
            }
        }
    },
    methods:{
        getSpuAttrCount(){
            let count=5
            if(this.searchParam.brandId===""){
                count--
            }
            if(this.searchParam.minPrice===""&&this.searchParam.maxPrice===""){
                count--
            }
            return Math.min(this.searchResult.spuAttrs.length,count)
        },
        getSkuAttrCount(){
            let count=5
            if(this.searchParam.brandId===""){
                count--
            }
            if(this.searchParam.minPrice===""&&this.searchParam.maxPrice===""){
                count--
            }
            return count-Math.min(this.searchResult.spuAttrs.length,count)
        },
        search(){

        }
    },
    created() {
      if(this.$route.params.keyword){
          this.searchParam.keyword=this.$route.params.keyword
      }
    }
}
</script>

<style src="@/assets/css/search/search.css" scoped>

</style>