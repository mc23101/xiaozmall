/*
 * @Description: 用户登录状态模块
 * @Author: hai-27
 * @Date: 2020-02-19 17:42:11
 * @LastEditors: hai-27
 * @LastEditTime: 2020-02-26 23:14:32
 */
export default {
  namespace:true,
  state: {
    username: "", // 登录的用户
    jwt: "" // 用于控制是否显示登录组件
  },
  getters: {
    getUserName (state) {
      return state.username
    },
    getJWT (state) {
      return state.jwt
    }
  },
  mutations: {
    setUserName (state, data) {
      state.username = data;
    },
    setJWT (state, data) {
      state.jwt = data;
    }
  },
  actions: {
    setUser ({ commit }, data) {
      commit('setUserName', data);
    },
    setShowLogin ({ commit }, data) {
      commit('setJWT', data);
    }
  }
}