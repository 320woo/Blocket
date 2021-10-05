<template>
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
</template>

<script>
import { reactive, onMounted } from 'vue'

export default {
  name: 'Certification', 
  props: [

  ],
  setup() {
    const state = reactive({
      displayCertModal: false,

      // 어학, 자격증
      certName: '',
      certsortation: '',
      acquisitionDate: '',
      certScore: '',
    })
    onMounted(() => {

    })
    return {
      state,
    }
  },
  methods: {
    // ========== 어학, 자격증 ========== //
    openCertModal() {
      this.state.displayCertModal = true
    },
    // 어학, 자격증 저장
    saveCertModal() {
      this.state.displayCertModal = false
    },
  }
}
</script>

<style scoped>

</style>