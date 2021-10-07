<template>
  <div style="background-color: #F9F7F7;">
    <!-- container -->
    <div class="p-grid p-jc-center"> <!-- 내부 요소를 가운데 정렬한다. -->
      <div class="p-col-6 p-flex-column"> <!-- 내부 요소의 너비를 col-4로 지정 -->
        <toast></toast>
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
              <div style="font-size: 30px;">{{ this.$store.state.user.username }}</div>
              <div><h5> 이메일 : {{ this.$store.state.user.userEmail }}</h5></div>
              <div><h5> 소속 : {{ this.$store.state.user.userbelong }}</h5></div>
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
          <div class="p-col" v-if="state.checkarmy">
            <div class="p-mb-2"><strong>구분 : </strong> {{state.checkarmy}} </div>
            <div class="p-mb-2"><strong>군별 : </strong> {{state.kind}} </div>
            <div class="p-mb-2"><strong>전역 사유 : </strong> {{state.discharge}} </div>
            <div class="p-mb-2"><strong>복무 기간 : </strong> {{state.start}} <span v-if="state.start">~</span> {{state.end}}</div>
          </div>
          <div class="p-col" v-else>병역 사항을 입력해주세요.</div>
        </div>

        <!-- 장애인 여부 기재 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">장애 여부</span>
            </div>
            <div class="p-col-2 edit-div">
              <Button icon="pi pi-plus" class="p-button-rounded p-button-text" @click="openDisabledModal" />
            </div>
          </div>
          <div v-if="state.personalInfo.disabled">
            <div class="p-col" v-if="state.Disabled_check">
              <div class="p-mb-2"><strong>여부 : </strong> {{state.personalInfo.disabled}} </div>
            <div class="p-mb-2"><strong>사유 : </strong> {{state.description}} </div>
            </div>
            <div class="p-col" v-else>
              <div class="p-mb-2"><strong>여부 : </strong> {{state.personalInfo.disabled}} </div>
            </div>
          </div>
          <div class="p-col" v-else>장애 여부를 입력해주세요.</div>
        </div>


      </div> <!-- end of vertical container -->
    </div> <!-- end of sub Container -->
  </div> <!-- end of top Container -->

  <!-- ============================== Modal ============================== -->

  <!-- 병역 사항 관련 Modal창 -->
  <Dialog header="병역 사항 수정" v-model:visible="state.displayArmyModal" :style="{width: '30vw'}" :modal="true">
    <div class="p-field">
      <label for="armyType">병역 여부*</label>
      <select name="armyType" id="armyType" class="select" v-model="state.checkarmy">
        <option value="군필">군필</option>
        <option value="미필">미필</option>
      </select>
    </div> 

    <div class="p-field">
      <label for="armyType2">군종*</label>
      <select name="armyType2" id="armyType2" class="select" v-model="state.kind">
        <option value="육군">육군</option>
        <option value="해군">해군</option>
        <option value="공군">공군</option>
        <option value="해병대">해병대</option>
        <option value="의경">의경</option>
      </select>
    </div>

    <div class="p-field">
      <label for="armyType3">제대 종류*</label>
      <select name="armyType3" id="armyType3" class="select" v-model="state.discharge">
        <option value="만기전역">만기전역</option>
        <option value="의가사 전역">의가사 전역</option>
        <option value="기타">기타</option>
      </select>
    </div>
    
    <div class="p-fluid p-grid p-formgrid">
      <div class="p-field p-col-6 p-md-6">
        <label for="certIcon">입대일*</label>
        <Calendar id="certIcon" class="calendar" v-model="state.start" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>

      <div class="p-field p-col-6 p-md-6">
        <label for="certIcon">전역일*</label>
        <Calendar id="certIcon" class="calendar" v-model="state.end" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>
    </div>

    <template #footer>
      <Button label="저장" icon="pi pi-check" @click="saveArmyModal" autofocus />
    </template>
  </Dialog>

  <!-- 장애 여부 관련 Modal 창-->
  <Dialog header="장애 여부 수정" v-model:visible="state.displayDisabledModal" :style="{width: '30vw'}" :modal="true">
    <div class="p-field">
      <label for="disabledType">장애 여부*</label>
      <select name="disabledType" id="disabledType" @change="change($event)" class="select" v-model="state.personalInfo.disabled">
        <option value="있음">있음</option>
        <option value="없음">없음</option>
      </select>
    </div> 

    <div class="p-field" v-if="state.Disabled_check">
      <label for="description">설명*</label>
      <InputText v-model="state.description" id="description" class="input-text" type="description" placeholder="설명을 적어 주세요." />
    </div>

    <template #footer>
      <Button label="저장" icon="pi pi-check" @click="saveDisabledModal" autofocus />
    </template>
  </Dialog>
</template> <!-- end of HTML code -->

<script>
import { reactive } from 'vue'
import defaultImage from "~/images/test.png"
import defaultUserImage from "~/images/user.png"
import Toast from "@/components/Toast.vue";
import * as pService from '@/utils/pService.js' // default를 붙이면 중괄호 없이 가져올 수 있다..! 반대로 default가 없는 경우에는 중괄호 필수
// 자식 컴포넌트
import Activity from "./Activity.vue"
import Education from "./Education.vue"
import Certification from "./Certification.vue"

export default {
  name: 'PersonalInfo',
  components: { Activity, Education, Certification, Toast },
  setup() {
    pService.checkToken() // 토큰 정보 확인
    pService.getMyInfo().then(res => {  // 각 함수는 비동기 처리하였음
      state.personalInfo = res
    })
    pService.getUserBelong().then(res => {
      state.belong = res.belong
      state.name = res.name
    })
    
    const state = reactive({
      name: null,   // 사용자 이름
      belong: null, // 소속
      defaultImage: defaultImage,
      defaultUserImage: defaultUserImage,
      description : null,
      Disabled_check : '',
      //병역 사항
      checkarmy : null,
      kind : null,
      discharge : null,
      start : null,
      end : null,
      // Modal창 on, off
      displayArmyModal: false,
      displayDisabledModal : false,

      disabled_fake : '',

      // 개인 정보
      personalInfo: {
        address: null,          // 주소
        dateBirth: null,        // 생년월일
        englishName: null,      // 영문이름
        gender: null,           // 성별
        id: null,               // PK
        militaryService: null,  // 병역사항
        veteransAffairs: null,  // 보훈사항
        disabled: null,         // 장애여부
        
      },
    })
    return {
      state,
    }
  },

  methods: {
    change(value) {
        console.log(value);
        if(event.target.value === "있음"){
            this.state.Disabled_check = true;
        }
        if(event.target.value === "없음"){
            this.state.Disabled_check = false;
        }
    },

    changeImg() {
      
    },
    // 병역사항 모달창
    openArmyModal() {
      this.state.displayArmyModal = true
    },
    saveArmyModal() {
      var totalarmy = this.state.checkarmy + "/" + this.state.kind + "/" + this.state.discharge + "/" + this.state.start + " ~ " + this.state.end;
      console.log("병역 컬럼 : " + totalarmy)

      this.$toast.add({severity:'success', summary: '시스템 정보', group: 'center', detail:'병역 사항 등록완료', life: 1000});
      this.state.displayArmyModal = false
    },

    openDisabledModal() {
      this.state.displayDisabledModal = true;
    },

    saveDisabledModal() {
      if(this.state.Disabled_check == false)console.log("없을때 : " + this.state.personalInfo.disabled)
      if(this.state.Disabled_check == true)console.log("설명이 있을때 : " + this.state.personalInfo.disabled + "/" +this.state.description)
      
      this.$toast.add({severity:'success', summary: '시스템 정보', group: 'center', detail:'장애 여부 등록완료', life: 1000});
      this.state.displayDisabledModal = false;
    }
    
  },
}
</script>

<style lang="scss" scoped>
</style>