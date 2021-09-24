<template>
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
</template>
<script>
import { createWeb3 } from '@/utils/itemInventory.js'
import { reactive } from '@vue/reactivity'
import axios from 'axios'

export default {
name: 'personalInfo',
components: {},
setup() {
  const web3 = createWeb3()

  const state = reactive({
    web3: web3,
    walletAddress: null,
    privateKey: null,
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