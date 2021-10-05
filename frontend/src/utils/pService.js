import axios from 'axios'
// useRouter, useStore를 사용하는 것과 어떤 차이가 있는지..
import router from '../router'
import store from '../store'
import vueConfig from '../../vue.config'

const BASE_URL = vueConfig
    .devServer
    .proxy['/blocket']
    .target + "/api"
const USER_URL = BASE_URL + "/recruit/users"
const INFO_URL = BASE_URL + "/recruit/personalinfo"

export function UserDelete() {
  console.log("탈퇴 pService : " + store.state.user.accessToken);
  return axios.delete(USER_URL + "/me", {
      headers: {
          Authorization: "Bearer " + store.state.user.accessToken
      }
  })
}

export function UserCheck() {
  console.log("유저정보 check")
  return axios.get(USER_URL + "/me", {
      headers: {
          Authorization: "Bearer " + store.state.user.accessToken
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


export function checkLogin() {

    if (store.state.user.accessToken !== null) {
        router.push("/")
    }
}

// 신상정보 불러오기
export async function getMyInfo() {
  let result = ''
  // 먼저 유저에 대한 personalInfo Id를 불러와야 한다. 
  await axios.get(USER_URL + "/getMyInfo?userEmail=" + store.state.user.userEmail,
    {
      headers:{
        Authorization:"Bearer "+ store.state.user.accessToken
    }
  }).then(res => {
    // 최초 등록 시, 주소-영문이름-성별-생년월일은 반드시 한번에 등록되어야 한다.
    result = res.data[0]
  })
  console.log(result)
  return result
}

export async function saveInfoModal(pid, input) {
  await axios({
    url: INFO_URL + "/" + pid + "/updatePersonalInfo",
    method: "PUT",
    headers: {
      'Content-Type': 'application/json'
    },
    data: JSON.stringify(input)
  })
  input.dateBirth = JSON.stringify(input.dateBirth).slice(1, 11)
  return input
}