<template>
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
          <strong>최종 학력</strong> : {{ state.myGrade.sortation }}
        </div>
        <div class="p-col-12">
          <strong>학교명</strong> : {{ state.myGrade.schoolName }} {{ state.myGrade.majorName }}
        </div>
        <div class="p-col-12">
          <strong>학점</strong> : {{ state.myGrade.grades }} / {{ state.myGrade.totalScore }}
        </div>
      </div>

      <div v-if="!state.isWritten">
        <span style="color: blue; cursor: pointer; display: inline-block;" @click="state.displayEducationModal = true">
          <strong>새로 입력하기</strong>
        </span>
      </div>
    </div>
  </div>


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
</template>

<script>  
import { reactive, onMounted, ref } from 'vue'
import { getAllColleges, getAllMajors } from '@/utils/colleges.js'
import { FilterService, FilterMatchMode }  from 'primevue/api'

import * as eService from '@/utils/educationService.js'

export default {
  name: 'Education',
  setup() {
    const colleges = ref()         // 모든 학교
    const filteredColleges = ref() // 검색 결과로 나온 학교들
    const majors = ref()           // 모든 전공
    const filteredMajors = ref()   // 검색 결과로 나온 전공들

    const state = reactive({
      displayEducationModal: false,
      // 최종 학력 사항
      isWritten: false,
      myGrade: {
        id: '',                     // 학력 사항 Pk. 있는 경우에만 값이 들어간다.
        pid: '',                    // 신상정보 PK
        userId: '',                 // 작성자 Id,
        grades: '',                 // 내가 취득한 평균 학점
        totalScore: '',             // 학점 점수 기준
        schoolInfoObj: '',          // 객체로 저장된다.
        majorInfoObj: '',           // 객체로 저장된다.
        schoolName: '', 
        majorName: '',  
        sortation: '',              // 분류. 대학교, 고등학교, 중학교,
      },
    })

    onMounted(() => {
      // 모든 대학교 목록 불러오기
      getAllColleges().then(res => {
        colleges.value = res.data.dataSearch.content
      })

      // 학과 목록 불러오기
      getAllMajors().then(res => {
        majors.value = res.data.dataSearch.content
      })

      // 최종학력 불러오기
      eService.getFinalEducation().then(res => {
        state.isWritten = res.isWritten
      
        if (!res.isWritten) {
          // 최종학력 작성에 필요한 기본사항(pid, userId만 등록해둔다.)
          state.myGrade.pid = res.pid
          state.myGrade.userId = res.userId
        } 
        else {
          state.myGrade = res
        }
      })
    })

    return {
      state,
      colleges,
      filteredColleges,
      majors, 
      filteredMajors,
    }
  },
  methods: {
    openEducationModal() {
      this.state.displayEducationModal = true
    },
    saveEducationModal() {
      // 기본적으로, 파일이 반드시 선택되어 있어야 한다.

      // 최종 학력 등록하기
      this.state.myGrade.name = this.state.myGrade.schoolInfoObj.schoolName  + " " + this.state.myGrade.majorInfoObj.mClass
      
      // 만약 처음으로 작성하는 거라면...
      if (this.state.isWritten === false) {
        eService.createFinalEducation(this.state.myGrade).then(
          alert("최종학력을 등록하였습니다.")
        )
      }
      // 이미 작성되었다면
      else {
        console.log("최종학력 수정")
        eService.updateFinalEducation(this.state.myGrade)
        .then(res => {
          // 변경 사항 갱신하기
          this.state.myGrade.schoolName = res.schoolName
          this.state.myGrade.majorName = res.majorName
        })
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
  }
}

</script>

<style scoped>

</style>