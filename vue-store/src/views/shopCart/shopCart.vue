<template>
    <body>
    <header>
        <a name="mao1">
            <div class="header">

                <ul class="header-left">
                    <li>

                        <a href="">首页</a>
                    </li>

                </ul>
                <ul class="header-right">
                    <li v-if="this.$store.getters.getJWT===''"><a href="#/login" style="color: red;">你好，请登录</a></li>
                    <li v-else><a>你好，{{this.$store.getters.getUserName}}</a></li>
                    <li v-if="this.$store.getters.getJWT===''"><a href="#/register">免费注册</a></li>
                    <li class="spacer"></li>
                    <li><a href="">我的订单</a></li>
                    <li class="spacer"></li>

                </ul>
                <div style="clear: both;"></div>
            </div>

        </a>
    </header>

    <div class="one_search">

        <div class="one_sousuo">
            <div class="one_search_top">
                <div class="one_top_left">
                    <a href="../" class="one_left_logo"><img src="~@/assets/img/shopCart/logo1.jpg" alt=""></a>
                    <a href="#" class="one_left_link">购物车</a>
                </div>
                <div class="one_top_right">
                    <input type="text" class="one_right_txt" placeholder="" onfocus="this.placeholder=''" onblur="this.placeholder='' ">
                    <input type="button" value="搜索" class="one_right_btn">
                </div>
            </div>
            <div class="one_search_load" v-if="this.$store.getters.getJWT===''">
                <img src="~@/assets/img/shopCart/shop_07.jpg" class="one_load_wraing" alt="">
                <span>您还没有登录！登录后购物车的商品将保存到您账号中</span>
                <a href="#/login"><input type="button" value="立即登录" class="one_load_btn"></a>
            </div>
            <div class="one_search_load" v-else>
                <img src="~@/assets/img/shopCart/shop_07.jpg" class="one_load_wraing" alt="">
                <span>结算前请确保支付环境安全，检查商品数量是否正确。</span>
            </div>
        </div>
    </div>
    <div class="One_BdyShop">
        <div class="OneBdy_box">
            <div class="One_tabTop">
                <div class="One_Topleft">
                    <span>全部商品</span>
                </div>
            </div>
            <div class="One_ShopTop">
                <ul>
                    <li>商品</li>
                    <li>单价</li>
                    <li>数量</li>
                    <li>小计</li>
                    <li>操作</li>
                </ul>
            </div>

            <div class="One_ShopCon">
                <ul>
                    <li v-for="(product,productIndex) in cartProducts" :key="productIndex">
                        <div> </div>
                        <div>
                            <ol>
                                <li><input type="checkbox" class="check" v-model="product.checked" @change="select"></li>
                                <li>
                                    <dt><img :src="product.defaultImgUrl" alt=""></dt>
                                    <dd>
                                        <p>
                                            <span>{{product.spuDescription}}</span>
                                        </p>
                                    </dd>
                                </li>
                                <li>
                                    <p class="dj">{{product.price.toFixed(2)}}</p>
                                </li>
                                <li>
                                    <p>
                                        <span class="set_native" @mouseover="$('.set_native').css('cursor', 'pointer')" @click="product.count>1?product.count--:null">-</span>
                                        <span>{{product.count}}</span>
                                        <span class="set_native" @mouseover="$('.set_native').css('cursor', 'pointer')" @click="product.count++">+</span>
                                    </p>
                                </li>
                                <li style="font-weight:bold">
                                    <p class="zj">￥{{(product.price*product.count).toFixed(2)}}</p>
                                </li>
                                <li>
                                    <p>删除</p>
                                </li>
                            </ol>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="One_ShopFootBuy fix1">
                <div>
                    <ul>
                        <li><input type="checkbox" class="allCheck" v-model="checkAll" @change="selectAll($event)"><span>全选</span></li>
                        <li>删除选中的商品</li>
                        <li>移到我的关注</li>
                        <li>清除下柜商品</li>
                    </ul>
                </div>
                <div>
                    <font style="color:#e64346;font-weight:bold;" class="sumNum"> </font>&nbsp;

                    <ul>
                        <li><img src="~@/assets/img/shopCart/buyNumleft.png" alt=""></li>
                        <li><img src="~@/assets/img/shopCart/buyNumright.png" alt=""></li>
                    </ul>
                </div>
                <div>
                    <ol>
                        <li>总价:<span style="color:#e64346;font-weight:bold;font-size:16px;" class="fnt">￥0.00</span> </li>
                    </ol>
                </div>
                <div><button onclick="toTrade()" type="button">去结算</button></div>
            </div>
        </div>
    </div>

    <div class="One_isDel">
        <p>
            <span>删除</span><span><img src="~@/assets/img/shopCart/错误.png" alt=""></span>
        </p>
        <div>
            <dl>
                <dt><img src="~@/assets/img/shopCart/感叹三角形 (2).png" alt=""></dt>
                <dd>
                    <li>删除商品？</li>
                    <li>您可以选择移到关注，或删除商品。</li>
                </dd>
            </dl>
        </div>
        <div>
            <button type="button">删除</button>

        </div>
    </div>
    <div class="One_moveGzIfNull">
        <p>
            <span>删除</span><span><img src="~@/assets/img/shopCart/错误.png" alt=""></span>
        </p>
        <dl>
            <dt><img src="~@/assets/img/shopCart/感叹三角形 (2).png" alt=""></dt>
            <dd>请至少选中一件商品！</dd>
        </dl>
    </div>
    <div class="One_moveMyGz">
        <p>
            <span>删除</span><span><img src="~@/assets/img/shopCart/错误.png" alt=""></span>
        </p>
        <div>
            <dl>
                <dt><img src="~@/assets/img/shopCart/感叹三角形 (2).png" alt=""></dt>
                <dd>
                    <li>移到关注</li>
                    <li>移动后选中商品将不再购物车中显示</li>
                </dd>
            </dl>
        </div>
        <div>
            <button type="button">确定</button>
            <button type="button">取消</button>
        </div>
    </div>
    <div class="One_clearShop">
        <p>
            <span>提示</span><span><img src="~@/assets/img/shopCart/错误.png" alt=""></span>
        </p>
        <dl>
            <dt><img src="~@/assets/img/shopCart/感叹三角形 (2).png" alt=""></dt>
            <dd>没有下柜商品!</dd>
        </dl>
    </div>
    <!--底部-->

    <div class="one_footer">

    </div>
    </body>
</template>

<script>
import $ from "jquery";

export default {
    data(){
        return{
            checkAll:false,
            cartProducts:[
                {
                    skuId:6,
                    defaultImgUrl:require("@/assets/img/shopCart/shop1.jpg"),
                    spuDescription:"TCL 55A950C 55英寸32核",
                    count: 5,
                    price:5499000.00,
                    checked:false
                }
            ],
        }
    },
    methods:{
        $,
        removeProduct(skuId){
            console.log(skuId)
        },
        select(){
            let count=0
            this.cartProducts.forEach(product=>{
                if(product.checked){
                    count++
                }
            })
            if(count===this.cartProducts.length){
                this.checkAll=true
            }else {
                this.checkAll=false
            }
        },
        selectAll(val){
            this.cartProducts.forEach(product=>{
                product.checked=val.target.checked
            })
            this.checkAll=val.target.checked
        }
    }
}
</script>

<style src="@/assets/css/shopCart/index.css" scoped>
</style>