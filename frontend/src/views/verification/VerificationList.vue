<template>
    <div>
        {{verifications.content.id}}
        <DataTable value="verifications" :paginator="true" :rows="verifications.size"
            paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink" responsiveLayout="scroll">
            <Column field="content.id" header="검증ID"></Column>
            <!-- <Column field="userId" header="사용자번호"></Column>
            <Column field="galleryId" header="파일ID"></Column>
            <Column field="registrationDate" header="등록일"></Column>
            <Column field="currentStatus" header="검증상태"></Column>
            <Column field="reasonsRejection" header="반려사유"></Column>             -->
            <template #paginatorLeft>
                <Button type="button" icon="pi pi-refresh" class="p-button-text" />
            </template>
            <template #paginatorRight>
                <Button type="button" icon="pi pi-cloud" class="p-button-text" />
            </template>
        </DataTable>    
	</div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
    computed: {
    ...mapGetters(["verifications"]),
    },
    data() {
        return {
            pageable:[],
            req:{
                page:1,
                size:10,
                verified:'',
            }
        }
    },
    created() {
        this.$store.dispatch("getVerifications", this.req).then((res)=>{
            this.pageable = res;
        console.log(this.pageable);
        });
    },
    mounted() {
        // console.log(this.pageable);
        // this.pageable = this.verifications;
        // console.log(this.verifications.verificationList);
    }
}
</script>
<style>

</style>