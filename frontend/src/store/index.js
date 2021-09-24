import { createStore } from "vuex";

export default createStore({
  state: {
    user: {
      userId: 0,
      walletAddress: null,
    }
  },
  mutations: {
    setUserId(state, id) {
      state.user.userId = id
    },
    setWalletAddress(state, address) {
      state.user.walletAddress = address
    },
    logout(state) {
      state.user.userId = 0
      state.user.walletAddress = null
    }
  },
  actions: {
    saveWalletInDB({ state }, payload) {
      console.log(state)
      console.log(payload)
    }

  },
  modules: {

  },
});
