import vueConfig from '../../vue.config'
import store from '../store'
import axios from 'axios'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const INFO_URL = BASE_URL + "/recruit/personalinfo"


export function getFinalEducation(personalInfoId) {
  return axios.get(INFO_URL + "/" + personalInfoId + "/myFinalEducation", {
    headers:{
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
}


export function createFinalEducation(myGrade) {
  const obj = {
    "grades": myGrade.grades + "/" + myGrade.totalScore,
    "id": myGrade.id,
    "name": myGrade.name,
    "sortation": myGrade.sortation,
    "userId": myGrade.userId
  }
  return axios({
    url: INFO_URL + "/" + myGrade.id + "/finaleducation",
    method: "POST", 
    headers: {
      Authorization: "Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json'
    },
    data: obj,
  })
}


export function updateFinalEducation(myGrade) {
  const obj = {
    "grades": myGrade.grades + "/" + myGrade.totalScore,
    "id": myGrade.pid,
    "name": myGrade.name,
    "sortation": myGrade.sortation,
    "userId": myGrade.userId
  }
  return axios({
    url: INFO_URL + "/" + myGrade.pid + "/" + myGrade.id + "/update",
    method: "PUT", 
    headers: {
      Authorization: "Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json'
    },
    data: obj,
  })
}