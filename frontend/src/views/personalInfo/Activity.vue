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

    <div class="p-col">내용내용</div>

  </div>

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
</template>

<script>
import { reactive, onMounted } from 'vue'

export default {
  name: 'Activity', 
  props: [

  ],
  setup() {
    const state = reactive({
      displayActivityModal: false,

      // 활동 사항
      activityName: '', 
      startDate: '',
      endDate: '',
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
      this.state.displayActivityModal = false
    },
  }
}
</script>

<style scoped>

</style>