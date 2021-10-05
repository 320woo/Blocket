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
            <div class="p-mb-2">성별: {{ state.personalInfo.gender }}</div>
            <div class="p-mb-2">영문 이름: {{ state.personalInfo.englishName }}</div>
            <div class="p-mb-2">생년월일: {{ state.personalInfo.dateBirth }}</div>
            <div class="p-mb-2">주소: {{ state.personalInfo.address }}</div>
          </div>
        </div> 

        <!-- 최종 학력 -->
        <div class="p-col profile">
          <div class="p-grid">
            <div class="p-col-10">
              <span class="header-font">최종 학력</span>
            </div>

            <div class="p-col-2 edit-div">
              <Button icon="pi pi-pencil" class="p-button-rounded p-button-text" @click="openEducationModal" />
            </div>
          </div>

          <div class="p-col">
            <!-- 입력한 최종학력이 존재하는 경우 -->
            <div class="p-grid">
              <div class="p-col-12">
                최종 학력 : {{ state.myGrade.sortation }}
              </div>
              <div class="p-col-12">
                학교명 : {{ state.myGrade.schoolInfo }} {{ state.myGrade.majorInfo }}
              </div>
              <div class="p-col-12">
                학점 : {{ state.myGrade.grades }} / {{ state.myGrade.totalScore }}
              </div>
            </div>

            <div v-if="!state.myGrade.isItWritten">
              <span style="color: blue; cursor: pointer; display: inline-block;" @click="state.displayEducationModal = true">
                <strong>새로 입력하기</strong>
              </span>
            </div>
          </div>

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
              <Button icon="pi pi-plus" class="p-button-rounded p-button-text" @click="openCertModal" />
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
  <!-- https://primefaces.org/primevue/showcase/#/dialog 참고... -->

  <!-- 학력 사항 Modal 창 -->
  <Dialog header="최종 학력 입력" v-model:visible="state.displayEducationModal" :style="{width: '40vw'}" :modal="true">
      
      <div class="p-grid">
        <!-- 대학교 이름 검색 -->
        <div class="p-field p-col-6">
          <label for="collegeName">학교명*</label>  
          <AutoComplete id="collegeName" v-model="this.state.myGrade.schoolInfoObj" 
          :suggestions="filteredColleges" @complete="searchCollege($event)" field="schoolName" placeholder="ex) OO대학교, OO대학" />
        </div>
        <!-- 학과 이름 검색 -->
        <div class="p-field p-col-6">
          <label for="majorName">학과명*</label>
          <AutoComplete id="majorName" v-model="this.state.myGrade.majorInfoObj" 
          :suggestions="filteredMajors" @complete="searchMajor($event)" field="mClass" placeholder="ex) OO학과, OO학" />
        </div>
        <!-- 총 학점 -->
        <div class="p-field p-col-3">
          <label for="grade">취득 학점*</label>
          <InputText id="grade" class="input-text" type="grade" style="width: 80px;" v-model="state.myGrade.grades" />
        </div>


        <div class="p-field p-col-3">
          <label for="scoreType">학점 기준</label>
          <select name="scoreType" id="scoreType" class="select" v-model="state.myGrade.totalScore">
            <option value="4.3">4.3</option>
            <option value="4.5">4.5</option>
          </select>
        </div>

        <!-- 학교 분류 ex) 고등학교, 대학교, 대학원,,, -->
        <div class="p-field p-col-12">
          <label for="sortation">학교분류*</label>
          <select name="sortation" id="sortation" class="select" v-model="state.myGrade.sortation">
            <option value="고등학교">고등학교</option>
            <option value="대학교">대학교</option>
            <option value="대학원(석사졸)">대학원(석사졸)</option>
            <option value="대학원(박사졸)">대학원(박사졸)</option>
          </select>
        </div>

      </div>
      <!-- 졸업 증명서 첨부 -->
      <div class="p-field">
        <label for="file" class="for">졸업 증명서 첨부*</label>
        <FileUpload mode="basic" name="demo[]" url="./" accept="image/*" :maxFileSize="1000000" @upload="onUpload" />
      </div>
      <template #footer>
          <Button label="저장" icon="pi pi-check" @click="saveEducationModal" autofocus />
      </template>
  </Dialog>


  <!-- 활동 사항 추가 modal 창 -->
  <Dialog header="활동사항 입력" v-model:visible="state.displayActivityModal" :style="{width: '30vw'}" :modal="true">
    <div class="p-field">
      <label for="activityName">이름*</label>
      <InputText id="activityName" class="input-text" type="activityName" aria-describedby="username1-help" placeholder="예: 소매관리자" />
    </div> 
    <div class="p-field">
      <label for="activityType">활동 구분*</label>
      <select name="type" id="activityType" class="select">
        <option value="1">정규직</option>
        <option value="2">계약직</option>
        <option value="3">프로젝트</option>
        <option value="4">인턴</option>
      </select>
    </div> 
    <div class="p-field">
      <label for="description">설명*</label>
      <InputText id="description" class="input-text" type="description" aria-describedby="username1-help" placeholder="간단한 설명을 적어 주세요." />
    </div>
    <div class="p-fluid p-grid p-formgrid">
      <div class="p-field p-col-12">
        <label for="icon">시작일*</label>
        <Calendar id="icon" class="calendar" v-model="state.startDate" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>
      <div class="p-field p-col-12">
        <label for="icon">종료일*</label>
        <Calendar id="icon" class="calendar" v-model="state.endDate" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>
      <!-- 관련 서류 제출 -->
      <div class="p-field p-md-6">
        <label for="file" class="for">활동 증명서 첨부*</label>
        <FileUpload mode="basic" name="demo[]" url="./" accept="image/*" :maxFileSize="1000000" @upload="onUpload" />
      </div>


    </div>
      <template #footer>
          <Button label="저장" icon="pi pi-check" @click="saveActivityModal" autofocus />
      </template>
  </Dialog>


  <!-- 어학, 자격증 관련 모달 창 -->
  <Dialog header="어학, 자격증" v-model:visible="state.displayCertModal" :style="{width: '30vw'}" :modal="true">
    <div class="p-field">
      <label for="certName">이름*</label>
      <InputText id="certName" class="input-text" type="certName" aria-describedby="username1-help" placeholder="예: 국제 공인 네트워크 자격증" />
    </div>

    <div class="p-field">
      <label for="certType">구분*</label>
      <select name="certType" id="certType" class="select">
        <option value="1">어학</option>
        <option value="2">국가공인 자격증 </option>
        <option value="3">민간 자격증</option>
        <option value="4">기타</option>
      </select>
    </div> 

    <div class="p-fluid p-grid p-formgrid">
      <div class="p-field p-col-12">
        <label for="certScore">취득점수, 등급</label>
        <!-- Error msg 출력 -->
        <div></div>
        <InputText id="certScore" class="input-text" type="certScore" aria-describedby="username1-help" />
      </div>
      
      <div class="p-field p-col-12">
        <label for="certIcon">취득기간*</label>
        <Calendar id="certIcon" class="calendar" v-model="state.acquisitionDate" :showIcon="true" />
        <!-- Error msg 출력 -->
        <div></div>
      </div>
    </div>

    <!-- 관련 서류 제출 -->
    <div class="p-field p-md-6">
      <label for="file" class="for">증명서 첨부*</label>
      <FileUpload mode="basic" name="demo[]" url="./" accept="image/*" :maxFileSize="1000000" @upload="onUpload" />
    </div>

    <template #footer>
      <Button label="저장" icon="pi pi-check" @click="saveCertModal" autofocus />
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
import { createWeb3 } from '@/utils/itemInventory.js'
import { reactive, onMounted, ref } from 'vue'
import defaultImage from "~/images/test.png"
import defaultUserImage from "~/images/user.png"
import { getAllColleges, getAllMajors } from '@/utils/colleges.js'
import { FilterService, FilterMatchMode }  from 'primevue/api'
import { useStore } from 'vuex'
import * as pService from '@/utils/pService.js' // default를 붙이면 중괄호 없이 가져올 수 있다..! 반대로 default가 없는 경우에는 중괄호 필수
// import { useToast } from 'primevue/usetoast'

export default {
name: 'personalInfo',
components: {},
setup() {
  
  const store = useStore()
  const web3 = createWeb3()
  
  // 로그인 여부 가장 먼저 확인해야 함. 토큰이 없는 경우에는 로그인 페이지로 이동하게끔 한다.
  pService.checkToken()

  // 로그인 여부를 확인했으면 신상정보를 불러온다.
  pService.getMyInfo()
  

  // 학교 검색 관련 변수
  const colleges = ref()         // 모든 학교
  const filteredColleges = ref() // 검색 결과로 나온 학교들
  
  
  // 학과 검색 관련 변수
  const majors = ref()          // 모든 전고
  const filteredMajors = ref()  // 검색 결과로 나온 전공들

  // 파일 첨부 관련 변수 - 토스트
  // const toast = useToast()
  
  const state = reactive({
    store: store,
    web3: web3,
    belong: null,
    walletAddress: null,
    privateKey: null,
    defaultImage: defaultImage,
    defaultUserImage: defaultUserImage,

    // Modal창 on, off
    displayEducationModal: false,
    displayActivityModal: false,
    displayCertModal: false,
    displayArmyModal: false,
    
    // 개인 정보
    personalInfo: {
      id: '',
      dateBirth: '',    // 생년월일
      address: '',      // 주소
      englishName: '',    // 영문이름
      gender: '',    // 성별
      repProfile: '',    // 대표 프로필 경로
    },

    // 최종 학력 사항
    myGrade: {
      id: '', // 학력 사항 Pk. 있는 경우에만 값이 들어간다.
      pid: '',     // 신상정보 PK
      userId: '', // 작성자 Id,
      isItWritten: false,   // DB에서 데이터를 받아오는 경우 true가 된다.
      grades: '',   // 내가 취득한 평균 학점
      totalScore: '', // 학점 점수 기준
      schoolInfoObj: '', // 객체로 저장된다.
      majorInfoObj: '', // 객체로 저장된다.
      schoolInfo: '', 
      majorInfo: '',  
      name: '',
      sortation: '',  // 분류. 대학교, 고등학교, 중학교,
      
    },

    // 활동 사항
    activityName: '', 
    startDate: '',
    endDate: '',

    // 어학, 자격증
    certName: '',
    certsortation: '',
    acquisitionDate: '',
    certScore: '',
  })


  // =================== 페이지 로드 ================== //
  // ================================================= //
  onMounted(() => {

    // 모든 대학교 목록 불러오기
    getAllColleges().then(res => {
      colleges.value = res.data.dataSearch.content
    })

    // 학과 목록 불러오기
    getAllMajors().then(res => {
      majors.value = res.data.dataSearch.content
    })

    // 소속 표시하기 위해, user 테이블에서 조회하기
    pService.getUserBelong().then(res => {
      state.belong = res.data.belong
      state.store.commit("setUserId", res.data.id)
    })
    
    // personalInfo 정보 불러오기. 이 때 personalInfo PK도 함께 저장해둔다.
    pService.getMyInfo().then(res => {
      state.personalInfo.id = res.data[0].id
      state.personalInfo.dateBirth = res.data[0].dateBirth
      state.personalInfo.address = res.data[0].address
      state.personalInfo.englishName = res.data[0].englishName
      state.personalInfo.gender = res.data[0].gender
    
      // 최종학력 불러오기
      pService.getFinalEducation(state.personalInfo.id).then(res => {
        // 만약 저장되어 있는 최종 학력이 존재하지 않는경우 ...
        if (res.data.length === 0) {
          state.myGrade.isItWritten = false
        } 
        else {
          state.myGrade.isItWritten = true

          // 객체의 데이터를 하나하나 넣어준다.
          const name = res.data[0].name.split(" ")
          const gradeInfo = res.data[0].grades.split("/")

          state.myGrade.id = res.data[0].id
          state.myGrade.schoolInfo = name[0]
          state.myGrade.schoolInfoObj = name[0]
          state.myGrade.majorInfo = name[1]
          state.myGrade.majorInfoObj = name[1]
          state.myGrade.grades = gradeInfo[0]
          state.myGrade.totalScore = gradeInfo[1]
          state.myGrade.sortation = res.data[0].sortation
        }
      })

      // 활동 사항 정보 불러오기
      
      // 어학, 자격증 정보 불러오기
      
      // 병역 사항 불러오기

      // 보훈 사항 불러오기

      // 장애 여부 불러오기
    })

    
  })

  return {
    state,
    colleges,
    filteredColleges,
    majors, 
    filteredMajors,
    // toast,
  }
  
  

},

methods: {
  changeImg() {
    
  },

  // ========== 학력 사항 ========== //
  openEducationModal() {
    this.state.displayEducationModal = true
  },

  // 최종학력 저장
  saveEducationModal() {
    // 기본적으로, 파일이 반드시 선택되어 있어야 한다.


    // 최종 학력 등록하기
    this.state.myGrade.pid = this.state.personalInfo.id
    this.state.myGrade.userId = this.state.store.state.user.userId
    this.state.myGrade.name = this.state.myGrade.schoolInfoObj.schoolName  + " " + this.state.myGrade.majorInfoObj.mClass
    
    // 만약 처음으로 작성하는 거라면...
    if (!this.state.myGrade.isItWritten) {
      console.log("최종학력 작성")
      pService.createFinalEducation(this.state.myGrade)
    }
    // 이미 작성되었다면
    else {
      console.log("최종학력 수정")
      pService.updateFinalEducation(this.state.myGrade)
      .then(
        // 데이터 갱신이 필요하다.

      )
    }
    this.state.displayEducationModal = false
  },
  


  // 대학교 검색
  searchCollege(event) {
    setTimeout(() => {
      // 검색어가 존재하지 않는 경우
      if (!event.query.trim().length) { // query는 검색어를 의미한다.
        this.filteredColleges.value = [...this.colleges.value]
      }
      else {
        this.filteredColleges = FilterService.filter(this.colleges, ['schoolName'], event.query.trim(), FilterMatchMode.CONTAINS)
        
      }
    })
  },
  // 학과 검색
  searchMajor(event) {
    setTimeout(() => {
      // 검색어가 존재하지 않는 경우
      if (!event.query.trim().length) { 
        this.filteredMajors.value = [...this.majors.value]
      }
      else {
        this.filteredMajors = FilterService.filter(this.majors, ['mClass'], event.query.trim(), FilterMatchMode.CONTAINS)
      }
    })
  },
  // 졸업 증명서 파일 첨부
  onUpload() {
    // toast는 메시지를 오버레이하기 위해 필요한 툴이다.
    // this.toast.add({severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000});
  },


  //  ========== 활동 사항 ========== //
  openActivityModal() {
    this.state.displayActivityModal = true
  },
  // 활동 사항 저장
  saveActivityModal() {
    this.state.displayActivityModal = false
  },


  // ========== 어학, 자격증 ========== //
  openCertModal() {
    this.state.displayCertModal = true
  },
  // 어학, 자격증 저장
  saveCertModal() {
    this.state.displayCertModal = false
  },


  // ========== 병역 사항 ========== //
  openArmyModal() {
    this.state.displayArmyModal = true
  },
  // 어학, 자격증 저장
  saveArmyModal() {
    this.state.displayArmyModal = false
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
.p-field * { /* 라벨 & 정보 입력창 (input 요소의 부모 요소) */
    display: block;
}
.input-text { /* 정보 입력 창 */
  display: block;
  width: 100%;
}
.select {
  border: 1px solid lightgrey;
  border-radius: 5px;
  display: block;
  width: 100%;
  height: 40px;
}
.calendar { /* 시작일, 종료일 설정하는 캘린더 */
  width: 50%;
}
</style>