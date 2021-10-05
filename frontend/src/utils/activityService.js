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

  // 가장 먼저 pid를 불러온다.
  await pService.getMyInfo().then(res => {
    pid = res.id
  })

  await axios.get(INFO_URL + "/" + pid + "/myActivity", {
    headers: {
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
  .then(resp => {
    // CASE 1. 데이터가 없는 경우
    if (resp.data.length === 0) {
      result = {
        pid: pid,
        uid: store.state.user.userId,
        isWritten: false
      }
    }
    // CASE 2. 1개 이상의 데이터가 있는 경우
    else {
      resp.data.isWritten = true
      result = resp.data
    }
  })
  return result
}

// 활동사항 등록하기
export async function createActivity() {

}

// 활동사항 수정하기
export async function updateActivity() {

}

// 활동사항 삭제하기
export async function deleteActivity() {

}