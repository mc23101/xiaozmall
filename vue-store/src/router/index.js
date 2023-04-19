/*
 * @Description: 路由配置
 * @Author: hai-27
 * @Date: 2020-02-07 16:23:00
 * @LastEditors: hai-27
 * @LastEditTime: 2020-02-27 13:58:48
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [

  {
    path: '/search',
    name: 'Search',
    component: () => import('../views/search/search.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/register/register.vue')
  },
  {
    path: '/detail',
    name: 'Detail',
    component: () => import('../views/detail/detail.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/login.vue')
  },
  {
    path: '/',
    name: 'index',
    component: () => import('../views/index/home.vue')
  },
  {
    path: '*',
    name: '*',
    component: () => import('../views/index/home.vue')
  },
  {
    path: '/shopCart',
    name: 'shopCart',
    component: () => import('../views/shopCart/shopCart.vue')
  },
  {
    path: '/error',
    name: 'Error',
    component: () => import('../components/Error.vue')
  }
]

const router = new Router({
  // base: '/dist',
  // mode: 'history',
  routes
})

/* 由于Vue-router在3.1之后把$router.push()方法改为了Promise。所以假如没有回调函数，错误信息就会交给全局的路由错误处理。
vue-router先报了一个Uncaught(in promise)的错误(因为push没加回调) ，然后再点击路由的时候才会触发NavigationDuplicated的错误(路由出现的错误，全局错误处理打印了出来)。*/
// 禁止全局路由错误处理打印
const originalPush = Router.prototype.push;
Router.prototype.push = function push (location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default router
