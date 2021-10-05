import axios from 'axios'
// useRouter, useStore를 사용하는 것과 어떤 차이가 있는지..
import router from '../router'
import store from '../store'
import vueConfig from '../../vue.config'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const USER_URL =  BASE_URL + "/recruit/users"
const INFO_URL = BASE_URL + "/recruit/personalinfo"

// User 테이블에서 belong 가져오기
export function getUserBelong() {

    console.log("Belong 가져옵니다.")
    return axios.get(USER_URL, {
        headers: {
            Authorization: "Bearer " + store.state.user.accessToken
        }
    })
}

export function UserDelete() {
    console.log("탈퇴 pService : " + store.state.user.accessToken);
    return axios.delete(USER_URL, {
        headers: {
            Authorization: "Bearer " + store.state.user.accessToken
        }
    })
}

export function UserCheck() {
    console.log("조회 pService : " + store.state.user.accessToken);
    return axios.get(USER_URL +  "/me", {
        headers: {
            Authorization: "Bearer " + store.state.user.accessToken
        }
    }).then(({data}) => {
        console.log(data)
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
  // 먼저 유저에 대한 personalInfo Id를 불러와야 한다. 
  return axios.get(USER_URL + "/getMyInfo?userEmail=" + store.state.user.userEmail,{
    headers:{
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
}

export function getFinalEducation(personalInfoId) {
    return axios.get(INFO_URL + "/" + personalInfoId + "/myFinalEducation", {
        headers: {
            Authorization: "Bearer " + store.state.user.accessToken
        }
    })
}
export function checkLogin() {

    if (store.state.user.accessToken !== null) {
        router.push("/")
    }
}
