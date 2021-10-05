<template>
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

    <div class="p-col" v-for="act in state.myActivity" :key="act.id">
      {{ act.activity }}
      {{ act.name }}
      {{ act.description }}
      {{ act.period }}
    </div>

  </div>

  <!-- 활동 사항 추가 modal 창 -->
  <Dialog header="활동사항 입력" v-model:visible="state.displayActivityModal" :style="{width: '50vw'}" :modal="true">
    <div class="p-field">
      <label for="activityName">이름*</label>
      <InputText id="activityName" class="input-text" type="activityName" aria-describedby="username1-help" placeholder="예: 소매관리자" />
    </div> 
    <div class="p-field">
      <label for="activityType">활동 구분*</label>
      <select name="type" id="activityType" class="select">
        <option value="정규직">정규직</option>
        <option value="계약직">계약직</option>
        <option value="프로젝트">프로젝트</option>
        <option value="인턴">인턴</option>
        <option value="교육 이수">교육 이수</option>
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
</template>

<script>
import { reactive, onMounted } from 'vue'
import * as aService from '@/utils/activityService.js'

export default {
  name: 'Activity', 
  setup() {
    // 활동사항 불러오기
    aService.getActivities().then(resp => {
      console.log("활동사항 data : " +resp);
      if (resp.isWritten === false) {
        state.input.uid = resp.uid
        state.input.pid = resp.pid
      }
      else {
        resp.isWritten = true
        state.myActivity = resp    // 배열이 된다.
      }
    })

    const state = reactive({
      displayActivityModal: false,

      myActivity: '',   // DB에서 받아온 내용을 저장하는 변수.

      isWritten: false,  // 기존에 작성된 내역이 존재하는지
      startDate: '',
      endDate: '',
      // 활동 사항
      input: {          // 새로 입력한 값을 저장하는 변수
        id : '',
        activity: '', 
        description: '',
        name: '',
        period: '',    // startDate와 endDate 데이터 결합
        uid: '',
        pid: '',
      },
    })

    onMounted(() => {
      
    })
    
    return {
      state,
    }
  },
  methods: {
    openActivityModal() {
      this.state.displayActivityModal = true
    },
    saveActivityModal() {
      if (this.state.isWritten === false) {
        aService.createActivity(this.state.input).then(
          alert("최종활동을 등록하였습니다.")
        )
      }

      this.state.displayActivityModal = false
    },
    onUpload() {
      // toast는 메시지를 오버레이하기 위해 필요한 툴이다.
      // this.toast.add({severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000});
    },
  }
}
</script>

<style scoped>

</style>