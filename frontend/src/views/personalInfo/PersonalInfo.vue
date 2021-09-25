<template>
<div style="background-color: #F9F7F7;">
  <h1>ETH 트랜잭션</h1>

  <h1>1. 지갑 생성하기</h1>
  <Button label="Submit" @click="createWallet" />
  <InputText v-model="state.walletAddress" />
  <InputText v-model="state.privateKey" />

  <h1>2. 생성한 지갑 정보 DB에 저장하기</h1>
  <Button label="DB에 저장하기" @click="saveWalletInDB" />


  <h1>3. 지갑 정보 트랜잭션 전송 통해 infura 퍼블릭 노드에 저장하기</h1>
  <Button label="Submit" @click="saveWalletInEther" />


  <h1>신상 정보 입력</h1>
  <!-- container -->
  <div class="p-grid p-jc-center"> <!-- 내부 요소를 가운데 정렬한다. -->
    <div class="p-col-6 p-flex-column"> <!-- 내부 요소의 너비를 col-4로 지정 -->
      
      <!-- 사용자 프로필 -->
      <div class="p-col profile" style="padding: 0px; height: 430px;">
        <!-- 배경 이미지 부분 -->
        <img class="bg-img" :src="state.defaultImage" alt="">
        
        <!-- 사용자 지정 이미지 부분 -->
        <div class="user-img-frame">
          <div class="sub-frame">
            <img class="user-img" :src="state.defaultUserImage" alt="">
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
        <span class="header-font">정보</span>
      </div> 

      <!-- 간단한 자기소개 -->
      <div class="p-col profile">
        <div>
          <span class="header-font">자기소개 질문1.</span>
        </div>
        <div>
          <span class="header-font">자기소개 질문2.</span>  
        </div>
        <div>
          <span class="header-font">자기소개 질문3.</span>
        </div>
      </div>

      <!-- 활동 사항 -->
      <div class="p-col profile">
        <span class="header-font">활동 사항</span>
      </div>

      <!-- 어학, 자격증 -->
      <div class="p-col profile">
        <span class="header-font">어학 , 자격증</span>
      </div>


      <!-- 병역사항 기재 -->
      <div class="p-col profile">
        <span class="header-font">병역 사항</span>
      </div>

      <!-- 보훈사항 기재 -->
      <div class="p-col profile">
        <span class="header-font">보훈 사항</span>
      </div>

      <!-- 장애인 여부 기재 -->
      <div class="p-col profile">
        <span class="header-font">장애 여부</span>
      </div>


    </div> 
  </div> <!-- end of Container -->
</div>


<!-- 각 사항들은 모두 Modal창으로 수정할 예정 -->






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
  })
  return {
    state
  }
},

methods: {
  createWallet() {
    console.log("지갑 생성합니다.")
    const myWallet = this.state.web3.eth.accounts.wallet.create(1)
    console.log("생성한 지갑:", myWallet)
    
    this.state.walletAddress = myWallet[0].address
    this.state.privateKey = myWallet[0].privateKey


    // 이제 생성한 지갑의 정보를 infura의 퍼블릭 노드에 저장하자.

  },
  saveWalletInDB() {

  },
  saveWalletInEther() {

  },
},
}
</script>

<style>
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
}
</style>