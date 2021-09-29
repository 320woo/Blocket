import Web3 from 'web3'
import {
  BLOCKCHAIN_URL,
  // BLOCKCHAIN_WEBSOCKET_URL,
} from '../config'

// Web3 Object 생성
export function createWeb3() {
  console.log("web3 객체 생성")
  const web3 = new Web3(new Web3.providers.HttpProvider(BLOCKCHAIN_URL))
  return web3
}

// 관리자 계정에서 사용자 계정으로 0.1 이더씩 보내기
export function sendEther() {
  console.log("0.1 이더를 사용자 계정에 전송합니다. ")
  // const web3 = createWeb3()

}


