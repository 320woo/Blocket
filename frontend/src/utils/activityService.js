import vueConfig from '../../vue.config'
import store from '../store'
import axios from 'axios'

import * as pService from '@/utils/pService.js'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const INFO_URL = BASE_URL + "/recruit/personalinfo"

// 활동사항 가져오기. 
export async function getActivities() {
  let result = ''
  let pid = ''
  let uid = ''

  // 가장 먼저 pid를 불러온다.
  await pService.getMyInfo().then(res => {
    pid = res.id
    uid = res.user.id
  })

  await axios.get(INFO_URL + "/" + pid + "/myActivity", {
    headers: {
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
  .then(res => {
    // 데이터가 없는 경우에 res.data = []와 같이 나온다. 이 경우, 데이터가 있는 경우와 맞춰서 줘야 한다.
    if (res.data.length === 0) {
      result = {
        data: 'NoData',
        uid: uid,
        pid: pid,
      }
    }
    else {
      result = res.data
    }
  })
  return result
}

// 활동사항 등록하기
export async function createActivity(input, pid) {
  let result = ''
  await axios({
    url: INFO_URL + "/" + pid + "/activity",
    method: "POST",
    headers: {
      Authorization:"Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json',
    },
    data: input
    // 등록한 후, 모든 목록을 반환한다.
  }).then(res => {
    result = res
  })
  return result
}

// 활동사항 삭제하기
export async function deleteActivity(pid, id) {
  let result = ''
  await axios({
    url: INFO_URL + "/" + pid + "/" + id + "/ActDelete",
    method: "delete",
    headers: {
      Authorization:"Bearer "+ store.state.user.accessToken,
    }
  })
  .then(res => {
    // 데이터가 없는 경우
    if (res.data.length === 0) {
      result = {
        data: 'NoData',
        uid: null,
        pid: null,
      } 
    }
    // 데이터가 하나 이상 존재하는 경우
    else {
      result = res
    }
  })
  return result
}