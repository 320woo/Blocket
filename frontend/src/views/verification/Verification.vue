<template>
    <div >
        <Panel>
            <template #header>
                {{file.name}}
            </template>
            <div v-if="check=='act'">
                <div>
                    <label for="name">활동명</label>
                </div>
                <InputText
                    type="text"
                    name="name"
                    v-model="file.data.name"
                    class="InputText" disabled/>

                <div>
                    <label for="activity">활동구분</label>
                </div>
                <InputText
                    type="text"
                    name="activity"
                    v-model="file.data.activity"
                    class="InputText" disabled/>
                <div>
                    <label for="activity">기간</label>
                </div>
                <InputText
                    type="text"
                    name="period"
                    v-model="file.data.period"
                    class="InputText" disabled/>
                <div>
                    <label for="activity">설명</label>
                </div>
                <InputText
                    type="text"
                    name="description"
                    v-model="file.data.description"
                    class="InputText" disabled/>
            </div>

            <div v-else-if="check=='cert'">
                <div>
                    <label for="name">자격명</label>
                </div>
                <InputText
                    type="text"
                    name="name"
                    v-model="file.data.name"
                    class="InputText" disabled/>

                <div>
                    <label for="sortation">자격구분</label>
                </div>
                <InputText
                    type="text"
                    name="sortation"
                    v-model="file.data.sortation"
                    class="InputText" disabled/>
                <div>
                    <label for="acquisitionDate">기간</label>
                </div>
                <InputText
                    type="text"
                    name="acquisitionDate"
                    v-model="file.data.acquisitionDate"
                    class="InputText" disabled/>
                <div>
                    <label for="score">점수/등급</label>
                </div>
                <InputText
                    type="text"
                    name="score"
                    v-model="file.data.score"
                    class="InputText" disabled/>
            </div>
            <div v-else-if="check=='edu'">
                <div>
                    <label for="name">최종 학교명</label>
                </div>
                <InputText
                    type="text"
                    name="name"
                    v-model="file.data.name"
                    class="InputText" disabled/>

                <div>
                    <label for="sortation">학교구분</label>
                </div>
                <InputText
                    type="text"
                    name="sortation"
                    v-model="file.data.sortation"
                    class="InputText" disabled/>
                <div>
                    <label for="grades">학점</label>
                </div>
                <InputText
                    type="text"
                    name="grades"
                    v-model="file.data.grades"
                    class="InputText" disabled/>
            </div>
            <div>
                    <label for="verified">승인여부</label>
            </div> 
            <InputText
                    type="text"
                    name="verified"
                    v-model="file.currentStatus"
                    class="InputText" disabled/>

            <div class="btn">
            <div>
                    <label for="verified">반려사유</label>
            </div>
            <div>
            <InputText
                    type="text"
                    name="resonsRejection"
                    v-model="verify.reasonsRejection"
                    aria-describedby="resons-rejection"
                    class="InputText"/>
            <small v-show="rejectCheck" :v-model="rejectCheck" id="resons-rejection" class="p-error">사유를 입력해주세요.</small>
            </div>
                <Button label="승인" class="p-button-raised p-button-primary" @click="accept"/>
                <Button label="거부" class="p-button-raised p-button-danger" @click="refuse"/>
            </div>

        </Panel>

    </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["file"]),
  },
  data(){
      return{
          check:'',
          rejectCheck:false,
          verify:{
              fileId:0,
              verified:'',
              reasonsRejection:'',
          }
      }
  },
  created() {
    // let fileId = this.$route.query.no;
    const fileId = 1;
    this.verify.fileId = fileId;
    this.$store.dispatch("getFileInfo", { fileId });

    this.verify.verified = this.file.currentStatus;
    this.check = this.file.sortation;
  },
  methods: {
      accept(){
          this.verify.verified = "승인완료";
          this.$store.dispatch("patchVerification", this.verify);
      },
      refuse(){
          this.verify.verified = "거절";
        //   if(this.verify.reasonsRejection==="") this.rejectCheck=true;
        //   else if(this.verify.reasonsRejection!==""){
            //   this.rejectCheck=false;
            // this.verify.reasonsRejection=this.file.reasonsRejection;
              this.$store.dispatch("patchVerification", this.verify);
    //   }
      }
  },
}
</script>
<style>
    #panel {
        display: block;
        width: 40%;
        margin: auto;
    }
        .InputText {
        width: 100%;
    }
</style>