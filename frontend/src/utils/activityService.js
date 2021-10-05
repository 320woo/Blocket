import vueConfig from '../../vue.config'
import store from '../store'
import axios from 'axios'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const INFO_URL = BASE_URL + "/recruit/personalinfo"

// 활동사항 가져오기. 
export async function getActivity() {

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