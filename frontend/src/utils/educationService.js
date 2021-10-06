import vueConfig from '../../vue.config'
import store from '../store'
import axios from 'axios'

import * as pService from '@/utils/pService.js'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const INFO_URL = BASE_URL + "/recruit/personalinfo"


export async function getFinalEducation() {
  let result = ''
  let pid = ''
  let uid = ''
  // 신상정보 PK 가져오기.
  await pService.getMyInfo().then(res => {
    pid = res.id
    uid = res.user.id
  })
  
  await axios.get(INFO_URL + "/" + pid + "/myFinalEducation", {
    headers:{
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
  .then(res => {
    // 데이터가 없으면 신상정보 PK와 유저 PK만 반환한다.
    if (res.data.length === 0) {
      result = {
        grades: '',
        id: '',
        name: '',
        sortation: '',
        personalinfo: {
          id: pid,
          user: {
            id: uid,
          }
        },
      }
    }
    else {
      result = res.data[0] // 위와 달리 학력에 대한 정보가 포함되어 있다.
    }
  })
  return result
}


export async function createFinalEducation(input, uid, pid) {
  let result = ''
  const temp = {
    "grades": input.grades,
    "userId": uid,
    "name": input.name,
    "sortation": input.sortation,
  }
  await axios({
    url: INFO_URL + "/" + pid + "/finaleducation",
    method: "POST", 
    headers: {
      Authorization: "Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json'
    },
    data: temp,
  })
  .then(res => {
    console.log("생성함!!", res)
    // 생성한 객체를 받아온다.
    result = res.data
  })
  return result
}


export async function updateFinalEducation(input, pid, id) {
  let result = ''
  const temp = {
    "grades": input.grades,
    "name": input.name,
    "sortation": input.sortation,
  }
  await axios({
    url: INFO_URL + "/" + pid + "/" + id + "/update",
    method: "PUT", 
    headers: {
      Authorization: "Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json'
    },
    data: temp,
  })
  .then(resp => {
    // 수정한 객체를 받아온다.
    result = resp.data
  })
  return result
}