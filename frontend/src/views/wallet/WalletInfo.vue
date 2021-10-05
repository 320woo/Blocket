<template>
  <div class="login-form">
    <Panel id="panel" header="축하합니다! 성공적으로 Blocket의 계정을 생성하였습니다.">
      
      <div class="p-grid p-flex-column">
        <div class="p-col-12 information">
          
          Blocket은 블록체인 기술을 기반으로 하는 신상정보 검증 서비스입니다. 
          모든 사용자는 회원가입 후 계정에 할당되는 지갑이 생성됩니다. <br><br>
          생성된 지갑의 주소는 다음과 같습니다.
          
        </div>
        <div class="p-col-3" style="margin: auto; text-align: center;">
          <Button id="login-btn" @keyup.enter="checkValue" @click="checkValue" value="가입하기">로그인하기</Button>
        </div>
        <div class="p-col-3" style="margin: auto; text-align: center;">
          홈 화면으로
        </div>
      </div>
    </Panel>
  </div>
</template>

<script>
import { createWallet } from '@/utils/itemInventory.js'
import { useRoute } from 'vue-router'

export default {
    name: "WalletInfo",
    setup() {
      const route = useRoute()
      // 이 페이지에 진입해서, DB에 지갑 정보를 저장한다.
      createWallet(route.params.email)

    },
    methods: {

    },  
};
</script>

<style scoped="scoped">
.login-form {
    padding: 0 40px;
    margin-top: 35px;
}
.information {
  padding: 20px;
}
#panel {
    display: block;
    width: 50%;
    margin: auto;
}
#login-btn {
    background-color: #3f72af;
    color: #fff;
}

#login-btn:hover {
    background-color: #f9f7f7;
    color: #112d4e;
}
</style>