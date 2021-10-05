import vueConfig from '../../vue.config'
import store from '../store'
import axios from 'axios'

import * as pService from '@/utils/pService.js'

const BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
const INFO_URL = BASE_URL + "/recruit/personalinfo"


export async function getFinalEducation() {
  let result = ''
  let pid = ''
  // 여기서 아예 신상정보를 가져오자.
  await pService.getMyInfo().then(res => {
    pid = res.id
  })

  await axios.get(INFO_URL + "/" + pid + "/myFinalEducation", {
    headers:{
      Authorization:"Bearer "+ store.state.user.accessToken
    }
  })
  .then(res => {
    if (res.data.length === 0) {  // 만약 저장되어 있는 최종 학력이 존재하지 않는경우
      result = {
        userId: store.state.user.userId,
        pid: pid,
        isWritten: false, 
      }
    } 
    else {  // 저장되어 있는 최종학력이 존재하는 경우 ...
      const name = res.data[0].name.split(" ")
      const gradeInfo = res.data[0].grades.split("/")

      result = {
      id: res.data[0].id,
      pid: pid,
      isWritten: true, 
      userId: store.state.user.userId,
      grades: gradeInfo[0],
      totalScore: gradeInfo[1],
      schoolInfoObj: name[0],
      majorInfoObj: name[1],
      schoolName: name[0],
      majorName: name[1],
      sortation: res.data[0].sortation,
      }
    }      
  })
  return result
}


export async function createFinalEducation(myGrade) {
  const temp = {
    "grades": myGrade.grades + "/" + myGrade.totalScore,
    "id": myGrade.id,
    "userId": myGrade.userId,
    "name": myGrade.schoolName + " " + myGrade.majorName,
    "sortation": myGrade.sortation,
  }
  await axios({
    url: INFO_URL + "/" + myGrade.pid + "/finaleducation",
    method: "POST", 
    headers: {
      Authorization: "Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json'
    },
    data: temp,
  })
  .then(res => {
    console.log(res)
  })
}


export async function updateFinalEducation(myGrade) {
  
  let result = ''
  const temp = {
    "grades": myGrade.grades + "/" + myGrade.totalScore,
    "id": myGrade.pid,
    "userId": myGrade.userId,
    "name": myGrade.schoolInfoObj.schoolName + " " + myGrade.majorInfoObj.mClass,
    "sortation": myGrade.sortation,
  }
  await axios({
    url: INFO_URL + "/" + myGrade.pid + "/" + myGrade.id + "/update",
    method: "PUT", 
    headers: {
      Authorization: "Bearer "+ store.state.user.accessToken,
      'Content-Type': 'application/json'
    },
    data: temp,
  })
  .then(
    result = {
      schoolName: myGrade.schoolInfoObj.schoolName,
      majorName: myGrade.majorInfoObj.mClass,
    }
  )
  return result
}