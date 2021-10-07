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

export async function UserCheck() {
    console.log("유저정보 check")
    let result = ''
    await axios
        .get(USER_URL + "/me", {
        headers: {
            Authorization: "Bearer " + store.state.user.accessToken
        }
    })
        .then(res => {
            console.log("데이터 : " + res.data.email);
            result = {
                email: res.data.email,
                name: res.data.name,
                belong : res.data.belong,
                phoneNumber : res.data.phoneNumber
            }
        })
    return result;
    
}

// 토큰 확인 함수
export function checkToken() {
    if (store.state.user.accessToken === null) {
        alert("로그인 해주세요.")
        router.push("/login")
    }
}

// User 테이블에서 belong 가져오기
export async function getUserBelong() {
    let result = ''
    await axios
        .get(USER_URL + "/me", {
            headers: {
                Authorization: "Bearer " + store.state.user.accessToken
            }
        })
        .then(res => {
            // id는 vuex에 저장
            store.commit("setUserId", res.data.id)
            result = {
                belong: res.data.belong,
                name: res.data.name
            }
        })
    return result;
}

export function getFinalEducation(personalInfoId) {
    return axios.get(
        INFO_URL + "/" + personalInfoId + "/myFinalEducation",
        {
            headers: {
                Authorization: "Bearer " + store.state.user.accessToken
            }
        }
    )
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
    console.log("신상정보 조회 결과:", res)

    result = {
      id: res.data[0].id,
      dateBirth: res.data[0].dateBirth,
      address: res.data[0].address,
      englishName: res.data[0].englishName,
      gender: res.data[0].gender,
      disabled: res.data[0].disabled,
      militaryService: res.data[0].militaryService,
      veteransAffairs: res.data[0].veteransAffairs,
    }
  })
  return result
}
