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


