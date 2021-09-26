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
            <div><h4>SSAFY 교육생</h4></div>
            <div><h5>tofan@naver.com</h5></div>
          </div>
          <div class="p-col-8">
            내용내용내용
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
          <div class="p-mb-2">성별: {{ }}</div>
          <div class="p-mb-2">영문 이름: {{ }}</div>
          <div class="p-mb-2">생년월일: {{ }}</div>
          <div class="p-mb-2">주소: {{ }}</div>
        </div>
      </div> 

      <!-- 자기소개 문항 1번 -->
      <div class="p-col profile">
        <Panel :header="state.question1" :toggleable="true" >
          <template #icons>
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" @click="openQ1Modal"/>
          </template>
          <!-- :model="items" 를 통해 부가 메뉴를 생성할 수 있다. -->
          <!-- <Menu id="config_menu" ref="menu" :popup="true" /> -->
          <!-- 질문에 대한 내용 -->
          <p> {{ state.answer1 }} </p>
          
        </Panel>

        <!-- 자기소개 문항 2번 -->
        <Panel :header="state.question2" :toggleable="true" >
          <template #icons>
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" @click="openQ2Modal" />
          </template>
          
          <!-- 질문에 대한 내용 -->  
          <p> {{ state.answer2 }} </p>
        </Panel>

        <!-- 자기소개 문항 3번 -->
        <Panel :header="state.question3" :toggleable="true" >
          <template #icons>
            <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" @click="openQ3Modal" />
          </template>
          
          <!-- 질문에 대한 내용 -->
          <p> {{ state.answer3 }} </p>
        </Panel>

        
      </div>

      <!-- 활동 사항 -->
      <div class="p-col profile">
        <div class="p-grid">
          <div class="p-col-10">
            <span class="header-font">활동 사항</span>
          </div>

          <div class="p-col-2 edit-div">
            <Button icon="pi pi-plus" class="p-button-rounded p-button-text" @click="openActivityModal" />
          </div>
        </div>

        <div class="p-col">내용내용</div>

      </div>

      <!-- 어학, 자격증 -->
      <div class="p-col profile">
        <div class="p-grid">
          <div class="p-col-10">
            <span class="header-font">어학, 자격증</span>
          </div>

          <div class="p-col-2 edit-div">
            <Button icon="pi pi-plus" class="p-button-rounded p-button-text" />
          </div>
        </div>

        <div class="p-col">내용내용</div>

      </div>


      <!-- 병역사항 기재 -->
      <div class="p-col profile">
        <div class="p-grid">
          <div class="p-col-10">
            <span class="header-font">병역 사항</span>
          </div>

          <div class="p-col-2 edit-div">
            <Button icon="pi pi-plus" class="p-button-rounded p-button-text" />
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


<!-- 각 사항들은 모두 Modal창으로 수정할 예정 -->
<!-- https://primefaces.org/primevue/showcase/#/dialog 참고... -->

<!-- 자기소개 1번 문항 수정 -->
<Dialog :header="state.question1" v-model:visible="state.displayQ1Modal" :style="{width: '50vw'}" :modal="true">
    <Textarea class="textarea" v-model="state.answer1" :autoResize="true" maxlength="1000" placeholder="내용을 입력하세요" rows="10" />
    <template #footer>
        <Button label="닫기" icon="pi pi-check" @click="closeQ1Modal" autofocus />
    </template>
</Dialog>

<!-- 자기소개 2번 문항 수정 -->
<Dialog :header="state.question2" v-model:visible="state.displayQ2Modal" :style="{width: '50vw'}" :modal="true">
    <Textarea class="textarea" v-model="state.answer2" :autoResize="true" maxlength="1000" placeholder="내용을 입력하세요" rows="10" />
    <template #footer>
        <Button label="닫기" icon="pi pi-check" @click="closeQ2Modal" autofocus />
    </template>
</Dialog>

<!-- 자기소개 3번 문항 수정 -->
<Dialog :header="state.question3" v-model:visible="state.displayQ3Modal" :style="{width: '50vw'}" :modal="true">
    <Textarea class="textarea" v-model="state.answer3" :autoResize="true" maxlength="800" placeholder="내용을 입력하세요" rows="10" />    
    
    <template #footer>
        <Button label="닫기" icon="pi pi-check" @click="closeQ3Modal" autofocus />
    </template>
</Dialog>



<!-- 활동 사항 추가 modal 창 -->
<Dialog header="활동사항 입력" v-model:visible="state.displayActivityModal" :style="{width: '50vw'}" :modal="true">
    
  <div class="p-field">
    <label for="activityName">이름*</label><br>
    <InputText id="activityName" type="activityName" aria-describedby="username1-help" />
  </div>


    <template #footer>
        <Button label="저장" icon="pi pi-check" @click="saveActivityModal" autofocus />
    </template>
</Dialog>



<div>
        <h5>Basic</h5>
        <InputText type="text" v-model="value1" />
        <span :style="{marginLeft: '.5em'}">{{value1}}</span>

        <h5>Floating Label</h5>
        <span class="p-float-label">
            <InputText id="username" type="text" v-model="value2" />
            <label for="username">Username</label>
        </span>

        <h5>Left Icon</h5>
        <span class="p-input-icon-left">
            <i class="pi pi-search" />
            <InputText type="text" v-model="value3" placeholder="Search" />
        </span>

        <h5>Right Icon</h5>
        <span class="p-input-icon-right">
            <i class="pi pi-spin pi-spinner" />
            <InputText type="text" v-model="value4" />
        </span>

        <h5>Help Text</h5>
        <div class="p-field">
            <label for="username1">Username</label>
            <InputText id="username1" type="username" aria-describedby="username1-help" />
            <small id="username1-help">Enter your username to reset your password.</small>
        </div>

        <h5>Invalid</h5>
        <div class="p-field">
            <label for="username2">Username</label>
            <InputText id="username2" type="username" aria-describedby="username2-help" class="p-invalid" />
            <small id="username2-help" class="p-error">Username is not available.</small>
        </div>

        <h5>Disabled</h5>
        <InputText type="text" v-model="value5" disabled />

        <h5>Sizes</h5>
        <div class="sizes">
            <InputText type="text" class="p-inputtext-sm" placeholder="Small" />
            <InputText type="text" placeholder="Normal" />
            <InputText type="text" class="p-inputtext-lg"  placeholder="Large" />
        </div>
    </div>




</template>
<script>
import { createWeb3 } from '@/utils/itemInventory.js'
import { reactive } from '@vue/reactivity'
import defaultImage from "~/images/test.png"
import defaultUserImage from "~/images/user.png"

export default {
name: 'personalInfo',
components: {},
setup() {
  const web3 = createWeb3()

  const state = reactive({
    web3: web3,
    walletAddress: null,
    privateKey: null,
    defaultImage: defaultImage,
    defaultUserImage: defaultUserImage,
    displayQ1Modal: false,
    displayQ2Modal: false,
    displayQ3Modal: false,
    displayActivityModal: false,
    // 개인 정보
    dateBirth: '',    // 생년월일
    address: '',      // 주소
    englishName: '',    // 영문이름
    gender: '',    // 성별
    repProfile: '',    // 대표 프로필 경로

    // 자기소개 문항
    question1: '간단한 자기소개를 해주세요. (800자 제한)',
    question2: '본인이 생각하는 본인의 강점과 약점을 얘기해 주세요. (1000자 제한)',
    question3: '최근 목표하는 기업의 입사를 위해 준비하고 있는 것이 있나요? (800자 제한)',
    // 자기소개 문항 문답
    answer1: 'answer1',
    answer2: 'answer2',
    answer3: 'answer3',
    
    // 활동 사항
    activityName: '', 
  })
  return {
    state
  }
},

methods: {
  changeImg() {
    
  },

  // Quesion 1. //
  // 1번 질문 modal창 열고 닫기
  openQ1Modal() {
    this.state.displayQ1Modal = true
  },
  // Modal창 닫기
  closeQ1Modal() {
    this.state.displayQ1Modal = false
    // axios 통해 답변 변경 사항 저장

  },

  // Quesion 2. //
  // 1번 질문 modal창 열고 닫기
  openQ2Modal() {
    this.state.displayQ2Modal = true
  },
  // Modal창 닫기
  closeQ2Modal() {
    this.state.displayQ2Modal = false
    // axios 통해 답변 변경 사항 저장

  },

  // Quesion 3. //
  // 1번 질문 modal창 열고 닫기
  openQ3Modal() {
    this.state.displayQ3Modal = true
  },
  // Modal창 닫기
  closeQ3Modal() {
    this.state.displayQ3Modal = false
    // axios 통해 답변 변경 사항 저장

  },
  // 경력 사항 //
  openActivityModal() {
    this.state.displayActivityModal = true
  },
  saveActivityModal() {
    this.state.displayActivityModal = false
  },

},
}
</script>

<style lang="scss" scoped>
.profile { /* 1)상단 배경 이미지, 2)사용자 프로필 이미지, 3)사용자 인적사항을 포함한 프레임 */
  background-color: white;
  border: 0.5px solid lightgrey; 
  border-radius: 5px;
  position: relative;
  margin-bottom: 20px;
}
.bg-img { /* 상단 배경 이미지 */
  display: inline-block;
  width: 100%;
  height: 50%;
  border-top-right-radius: 5px;
  border-top-left-radius: 5px;
  object-fit: cover;
}
.user-img-frame { /* 사용자 프로필 이미지 프레임 */
  background-color: #F9F7F7;
  border: 5px solid white;
  border-radius: 100px;
  height: 150px;
  width: 150px;
  position: absolute;
  top: 25%;
  left: 5%; 
}
.sub-frame { /* userImgFrame 내 sub프레임  ( 이미지의 absolute 배치 위해 ) */
  position: relative;
}
.user-img-mod-frame { /* 사용자 프로필 이미지 수정 버튼 프레임 */
  height: 20px;
  width: 20px;
  position: absolute;
  top: 25%;
  left: 23%;
}
.mod-sub-frame { /* 사용자 프로필 수정 버튼 내 sub 프레임( 배치 때문에 ) */ 
  position: relative;
}
.user-img { /* 사용자 프로필 이미지 */
  display: inline-block;
  width: 100%;
  height: 50%;
  object-fit: cover;
}
.user-info { /* 사용자 인적사항 */
  padding-left: 25px; 
}
.header-font{ /* 각 부분 별 타이틀 폰트 */
  font-size: 20px;
  font-weight: bold;
  display: inline-block;
  padding-left: 5px;
}
.edit-div {
  text-align: end;
}
.textarea {
  width: 100%;
  height: 100%;
  overflow: auto;
}
.p-field * {
    display: block;
}
</style>