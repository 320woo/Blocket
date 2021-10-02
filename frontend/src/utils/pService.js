import axios from 'axios'
// useRouter, useStore를 사용하는 것과 어떤 차이가 있는지..
import router from '../router'
import store from '../store'
import vueConfig from '../../vue.config'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const USER_URL =  BASE_URL + "/recruit/users/me"
const INFO_URL = BASE_URL + "/personalInfo"

// User 테이블에서 belong 가져오기
export function getUserBelong() {  

  console.log("Belong 가져옵니다.")
  return axios.get(USER_URL, {
    headers:{
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
}

// 토큰 확인 함수 
export function checkToken() {

  if (store.state.user.accessToken === null) {
    alert("로그인 해주세요.")
    router.push("/login")
  }
}

export function getMyInfo() {
  return axios.get()
}

