### Solidity 변수 및 데이터 타입

| 데이터 타입 | 키워드 | 사용 | 범위 |
| ------ | ------ |
| Boolean | bool | True or False | True, False |
| Uint | uint8 | 부호가 없는 정수 표현 | 0~255 |
| - | uint16 | 부호가 없는 정수 표현 | 0~65,535 |
| - | uint24 | 부호가 없는 정수 표현 | 0~16,777,216 |
| - | uint256 | 부호가 없는 정수 표현 | 0~2^256 |
| - | uint | 부호가 없는 정수 표현 | 0~4,294,967,295 |
| String | string |	문자열 | - |
| Address |	address | 주소 잔액 조회 | - |
| - | address | payable | 이더리움 전송 | - |
| Array | DataType[] | 배열 선언 | - |
| - | push | 가장 마지막 데이터 추가 | - |
| - | pop | 가장 마지막 데이터를 가져오고 제거 | - |
| - | length | 배열의 길이 | - |
| - | struct | 구조체 선언 | - |
| Map | Mapping (key type=>value type) | 맵 | - |

### Solidity 함수 및 옵션
```
Function 함수명() 옵션 {
    기능 정의
}
```
- view/pure를 사용하여 데이터 조회 시 수수료를 지불하지 않음
- view는 변수 값을 조회하여 반환이 가능하나 pure는 변수에 접근이 불가능함
- view/pure가 붙은 함수는 데이터 변경이 불가능함

| View | Pure |
| ------ | ------ |
| 상태 변수 값 변경 | 상태 변수 읽기 |
| 이벤트 발생 | this.balance 혹은 <주소>.balance로 접근 |
| 다른 계약 생성 | Block, tx, msg 중 하나의 멤버 변수에 접근
(msg.sig와 msg.data) 제외 |
