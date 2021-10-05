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
            
            <div class="p-col-4">
              <div style="font-size: 30px;">{{ state.user.name }}</div>
              <div><h5> 이메일 : {{ state.user.email }}</h5></div>
              <div><h5> 소속 : {{ state.user.belong }}</h5></div>
            </div>
            <div class="p-col-7 p-text-right">
              <span style="cursor: pointer; font-weight: bold; color: #3F72AF;" @click="goToModify">수정하기</span>
            </div>
          </div>
        </div> <!-- end of 사용자 프로필 -->
        

        <!-- 생년월일, 주소, 영문이름, 성별 기재 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">기본정보</span>
            </div>
            <div class="p-col-2 edit-div">
              <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" @click="openInfoModal" />
            </div>
          </div>
          <div class="p-ml-3" >
            <div class="p-mb-2"><strong>성별</strong>
             <div v-if="state.personalInfo.gender !== null">
               {{ state.personalInfo.gender }}
             </div>
             <div v-else>
               입력사항 없음
             </div>
            </div>

            <div class="p-mb-2"><strong>영문 이름</strong> 
              <div v-if="state.personalInfo.englishName !== null">
                {{ state.personalInfo.englishName }}
              </div>
              <div v-else>
                입력사항 없음
              </div>
            </div>

            <div class="p-mb-2"><strong>생년월일</strong>
              <div v-if="state.personalInfo.dateBirth !== null">
                {{ state.personalInfo.dateBirth }}
              </div>
              <div v-else>
                입력사항 없음
              </div>
            </div>

            <div class="p-mb-2"><strong>주소</strong>
              <div v-if="state.personalInfo.address !== null">
                {{ state.personalInfo.address }}
              </div>
              <div v-else>
                입력사항 없음
              </div>
            </div>

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

  <!-- 신상정보 Modal 창 -->
  <Dialog header="기본 정보 수정" v-model:visible="state.displayInfoModal" :style="{width: '30vw'}" :modal="true">
    <div class="p-fluid p-grid p-formgrid">
      <div class="p-field p-col-12">
        <label for="gender">성별</label>
        <select name="gender" id="gender" class="select" v-model="state.input.gender">
          <option value="남성">남성</option>
          <option value="여성">여성</option>
        </select>
      </div>

      <div class="p-field p-col-12">
        <label for="englishName">영문이름</label>
        <InputText id="englishName" class="input-text" type="englishName" style="width: 100%;" v-model="state.input.englishName" placeholder="ex) SSAFY, Kim" />
      </div>

      <div class="p-field p-col-12">
        <label for="dateBirth">생년월일</label>
        <Calendar id="dateBirth" class="calendar" v-model="state.input.dateBirth" :showIcon="true" />
      </div>

      <div class="p-field p-col-12">
        <label for="address">주소</label>
        <InputText id="address" class="input-text" type="address" style="width: 100%;" v-model="state.input.address" />
      </div>
    </div>
    <template #footer>
      <Button label="저장" icon="pi pi-check" @click="saveInfoModal" autofocus />
    </template>
  </Dialog>

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
import router from '../../router'
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
    // Created
    pService.checkToken() // 토큰 정보 확인
    pService.getMyInfo().then(res => {
      
      // 신상정보 PK 저장하기
      state.pid = res.id

      // 사용자 이름, 소속, 이메일 설정
      state.user.name = res.user.name
      state.user.belong = res.user.belong
      state.user.email = res.user.email

      // 영문이름, 성별, 생년월일, 주소 설정
      state.personalInfo.englishName = res.englishName
      state.personalInfo.gender = res.gender
      state.personalInfo.address = res.address
      state.personalInfo.dateBirth = res.dateBirth

      // 만약 위의 4가지가 null이 아닌경우에는 input에도 넣어준다.
      if (res.englishName !== null && res.gender !== null && res.address !== null && res.dateBirth !== null) {
        state.input.englishName = res.englishName
        state.input.gender = res.gender
        state.input.address = res.address
        state.input.dateBirth = res.dateBirth
      }
    })
    
    const state = reactive({
      pid: null,
      user: {
        name: '',
        belong: '',
        email: '',
      },
      defaultImage: defaultImage,
      defaultUserImage: defaultUserImage,
      // Modal창 on, off
      displayInfoModal: false,
      displayArmyModal: false,
      
      // DB에서 받아올 때 사용.
      personalInfo: {
        englishName: '',
        address: '',
        dateBirth: '',
        gender: '',
      },
      // 개인 정보. 새로 값을 입력하거나 수정할 때 사용한다.
      input: {
        englishName: '',
        address: '',
        dateBirth: '',
        gender: '',
      },
    })
    return {
      state,
    }
  },

  methods: {
    changeImg() {
      
    },
    goToModify() {
      router.push("Modify")
    },

    openInfoModal() {
      this.state.displayInfoModal = true
    },
    saveInfoModal() {
      // 신상정보 변경사항 저장. (영문이름, 성별, 주소, 생년월일)
      pService.saveInfoModal(this.state.pid, this.state.input).then(res => {
        // 다시 불러오기
        this.state.personalInfo.englishName = res.englishName
        this.state.personalInfo.gender = res.gender
        this.state.personalInfo.address = res.address
        this.state.personalInfo.dateBirth = res.dateBirth
        // 모달 창 띄울 때 입력한 값 보이기 위해
        this.state.input.englishName = res.englishName
        this.state.input.gender = res.gender
        this.state.input.address = res.address
        this.state.input.dateBirth = res.dateBirth          
      })
      this.state.displayInfoModal = false
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