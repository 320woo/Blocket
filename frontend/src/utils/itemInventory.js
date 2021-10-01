import Web3 from 'web3'
// import Tx from 'ethereumjs-tx' // 이더리움의 트랜잭션 생성에 사용된다.

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
  const web3 = createWeb3()

  const send_account = "0xf255FC9eF3778E688950649547D398B027D8b999"
  const receive_account = "0x41278A913Ae5D0F68CF2D06A4007d76AF696B255"
  const privateKey = Buffer.from("b35023e44ad462879d110e4f68c8e794e0097c475d4639d4b9dbf463dcb1ef09", 'hex') // 개인키 맨 앞의 0x를 빼야한다. 

  // nonce : EOA가 생성하여 블록체인에 기록된 트랜잭션의 개수. nonce는 트랜잭션의 중복 전송을 방지하는데 사용된다. 
  web3.eth.getTransactionCount(send_account, (err, txCount) => {

    const txObject = {
      nonce: web3.utils.toHex(txCount),
      gasLimit: web3.utils.toHex(100000), // 0.1ether
      gasPrice: web3.utils.toHex(web3.utils.toWei('10', 'gwei')),
      from: send_account,
      to: receive_account,
      value: '0x2386f26fc10000' // 0.01 이더
    }

    const ethereumTx = require('ethereumjs-tx').Transaction
    
    const tx = new ethereumTx(txObject, {'chain': 'ropsten'})
    tx.sign(privateKey) // 객체를 담은 후 개인키를 이용한 sign이 들어간다. 이더리움 테스트넷과 메인넷에서는 필수로 sign객체가 필요하다. 

    const serializedTx = tx.serialize()
    const raw = '0x' + serializedTx.toString('hex')
    
    web3.eth.sendSignedTransaction(raw) // hash가 리턴되는데, 트랜잭션을 전송한 hash값이다. 이 hash값을 이용해 etherscan.io에서 조회가 가능하다. 
    .on('transactionHash', console.log)
  })
}

// 사용자의 진행상태를 트랜잭션으로 전송하기 (신상정보 등록완료 / 검증 진행중 / 검증 완료)
export function saveState() {

}






// 사용자로부터 데이터 가져오기
export function getState() {
  
}

