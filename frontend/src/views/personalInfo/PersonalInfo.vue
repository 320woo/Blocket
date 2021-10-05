<template>
  <div style="background-color: #F9F7F7;">
    <!-- container -->
    <div class="p-grid p-jc-center"> <!-- 내부 요소를 가운데 정렬한다. -->
      <div class="p-col-6 p-flex-column"> <!-- 내부 요소의 너비를 col-4로 지정 -->
        
        <!-- 사용자 프로필 -->
        <div class="p-col profile" style="padding: 0px; height: 430px;">
          <!-- 배경 이미지 부분 -->
          <img class="bg-img" :src="state.defaultImage" alt="">
          <!-- 사용자 프로필 이미지 부분 -->
          <div class="user-img-frame">
            <div class="sub-frame">
              <img class="user-img" :src="state.defaultUserImage" alt="">
            </div>
          </div>
          <!-- 사용자 프로필 이미지 수정 버튼 -->
          <div class="user-img-mod-frame">
            <div class="mod-sub-frame">
              <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" @click="changeImg" />
            </div>
          </div>
          <!-- 인적 사항 부분 -->
          <div class="p-grid user-info" style="margin-top:50px;">
            <div class="p-col-4 ">
              <div style="font-size: 30px;">조웅현</div>
              <div><h4>{{ this.state.belong }}</h4></div>
              <div><h5>{{ this.$store.state.user.userEmail }}</h5></div>
            </div>
            <div class="p-col-8">
            </div>
          </div>
        </div> <!-- end of 사용자 프로필 -->
        

        <!-- 생년월일, 주소, 영문이름, 성별 기재 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">정보</span>
            </div>
            <div class="p-col-2 edit-div">
              <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" />
            </div>
          </div>
          <div class="p-ml-3" >
            <div class="p-mb-2"><strong>성별</strong> {{ state.personalInfo.gender }}</div>
            <div class="p-mb-2"><strong>영문 이름</strong> {{ state.personalInfo.englishName }}</div>
            <div class="p-mb-2"><strong>생년월일</strong> {{ state.personalInfo.dateBirth }}</div>
            <div class="p-mb-2"><strong>주소</strong> {{ state.personalInfo.address }}</div>
          </div>
        </div> 

        <Education />

        <Activity />

        <Certification />

        <!-- 병역사항 기재 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">병역 사항</span>
            </div>

            <div class="p-col-2 edit-div">
              <Button icon="pi pi-plus" class="p-button-rounded p-button-text" @click="openArmyModal" />
            </div>
          </div>
          <div class="p-col">내용내용</div>
        </div>


        <!-- 보훈사항 기재 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">보훈 사항</span>
            </div>

            <div class="p-col-2 edit-div">
              <Button icon="pi pi-plus" class="p-button-rounded p-button-text" />
            </div>
          </div>
          <div class="p-col">내용내용</div>
        </div>


        <!-- 장애인 여부 기재 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">장애 여부</span>
            </div>
            <div class="p-col-2 edit-div">
              <Button icon="pi pi-plus" class="p-button-rounded p-button-text" />
            </div>
          </div>
          <div class="p-col">내용내용</div>
        </div>


      </div> <!-- end of vertical container -->
    </div> <!-- end of sub Container -->
  </div> <!-- end of top Container -->

  <!-- ============================== Modal ============================== -->

  <!-- 병역 사항 관련 Modal창 -->
  <Dialog header="병역 사항 수정" v-model:visible="state.displayArmyModal" :style="{width: '30vw'}" :modal="true">
    <div class="p-field">
      <label for="armyType">병역 여부*</label>
      <select name="armyType" id="armyType" class="select">
        <option value="1">필</option>
        <option value="2">무</option>
      </select>
    </div> 

    <div class="p-field">
      <label for="armyType2">군종*</label>
      <select name="armyType2" id="armyType2" class="select">
        <option value="1">육군</option>
        <option value="2">해군</option>
        <option value="3">공군</option>
        <option value="4">해병대</option>
        <option value="5">의경</option>
      </select>
    </div>

    <div class="p-field">
      <label for="armyType3">제대 종류*</label>
      <select name="armyType3" id="armyType3" class="select">
        <option value="1">만기전역</option>
        <option value="2">의가사 전역</option>
        <option value="3">기타</option>
      </select>
    </div>
    
    <div class="p-fluid p-grid p-formgrid">
      <div class="p-field p-col-6 p-md-6">
        <label for="certIcon">입대일*</label>
        <Calendar id="certIcon" class="calendar" v-model="state.acquisitionDate" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>

      <div class="p-field p-col-6 p-md-6">
        <label for="certIcon">전역일*</label>
        <Calendar id="certIcon" class="calendar" v-model="state.acquisitionDate" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>
    </div>

    <template #footer>
      <Button label="저장" icon="pi pi-check" @click="saveArmyModal" autofocus />
    </template>
  </Dialog>
</template> <!-- end of HTML code -->

<script>
import { reactive } from 'vue'
import defaultImage from "~/images/test.png"
import defaultUserImage from "~/images/user.png"

import * as pService from '@/utils/pService.js' // default를 붙이면 중괄호 없이 가져올 수 있다..! 반대로 default가 없는 경우에는 중괄호 필수
// 자식 컴포넌트
import Activity from "./Activity.vue"
import Education from "./Education.vue"
import Certification from "./Certification.vue"

export default {
  name: 'PersonalInfo',
  components: { Activity, Education, Certification },
  setup() {
    pService.checkToken() // 토큰 정보 확인
    pService.getMyInfo().then(res => {  // 각 함수는 비동기 처리하였음
      state.personalInfo = res
    })
    pService.getUserBelong().then(res => {
      state.belong = res
    })
    
    const state = reactive({
      belong: null, // 소속
      defaultImage: defaultImage,
      defaultUserImage: defaultUserImage,

      // Modal창 on, off
      displayArmyModal: false,
      
      // 개인 정보
      personalInfo: {
        id: '',           // PK
        dateBirth: '',    // 생년월일
        address: '',      // 주소
        englishName: '',  // 영문이름
        gender: '',       // 성별
        repProfile: '',   // 대표 프로필 경로
      },
    })
    return {
      state,
    }
  },

  methods: {
    changeImg() {
      
    },
    // 병역사항 모달창
    openArmyModal() {
      this.state.displayArmyModal = true
    },
    saveArmyModal() {
      this.state.displayArmyModal = false
    },


  },
}
</script>

<style lang="scss" scoped>
</style>