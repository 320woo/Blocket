<template>
    <div>
        <div class="login-form">
            <Panel header="Blocket을 체험해보세요." id="panel">
                <div>
                    <label for="username1">이메일</label>
                </div>
                <InputText
                    readonly
                    type="text"
                    id="email"
                    name="email"
                    v-model="state.email"
                    class="InputText"/>
                <div>
                    <label for="username1">비밀번호</label>
                </div>
                <InputText
                    type="password"
                    id="password"
                    name="password"
                    v-model="password"
                    ref="password"
                    class="InputText"
                    placeholder="6자 이상"/>
                <div>
                    <label for="username1">이름</label>
                </div>
                <InputText
                    type="text"
                    id="name"
                    name="name"
                    v-model="state.name"
                    class="InputText"/>
                <div>
                    <label for="username1">소속</label>
                </div>
                <InputText
                    type="text"
                    id="belong"
                    name="belong"
                    v-model="state.belong"
                    class="InputText"/>
                <div>
                    <label for="username1">연락처</label>
                </div>
                <InputText
                    type="text"
                    id="phoneNumber"
                    name="phoneNumber"
                    v-model="state.phoneNumber"
                    maxlength="11"
                    class="InputText"
                    placeholder="-를 제외하고 입력해주세요."
                    />
                <span v-if="show_brn">
                <div>
                    <label for="username1">사업자 등록번호</label>
                </div>
                
                <InputText
                    type="text"
                    id="brn"
                    name="brn"
                    v-model="state.brn"
                    ref="brn"
                    class="InputText"/>
                </span>
                <div>
                    <div class="center-btn">
                        <Button class="p-button-info" @click="modifyUser">
                            수정하기
                        </Button>
                         <Button id="done" class="p-button-danger" @click="deleteUser">
                            탈퇴하기
                        </Button>
                        <div>
                            <div>
                            <span @click="home" class="goback-link">
                                홈으로 돌아가기
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
            </Panel>

        </div>
    </div>
</template>
<script>
    // import http from "@/utils/http-common";
    import * as pService from '@/utils/pService.js'
    import { reactive } from 'vue'
    // import axios from 'axios'

    export default {
        name: "signup",
        computed: {},
        setup() {
          pService.checkToken()  
          pService.UserCheck().then(res => {  // 각 함수는 비동기 처리하였음
            state.email = res.email
            state.name = res.name
            state.belong = res.belong
            state.phoneNumber = res.phoneNumber
          })

        const state = reactive({
            email :null,
            name : null,
            belong : null,
            phoneNumber : null
        })
    return {
      state,
    }
        },
        methods: {
            Solo() {
                this.solo = true;
                this.company = false;
                this.type = "회원"
                this.show_brn = false;
            },
            Company() {
                this.solo = false;
                this.company = true;
                this.type = "기업"
                this.show_brn = true;
            },
            modifyUser() {
                let err = true;
                let msg = "";
                err && !this.email && ((msg = "이메일을 입력해주세요"), (err = false));
                err && !this.password && ((msg = "비밀번호를 입력해주세요"), (err = false));
                err && !this.name && ((msg = "이름을 입력해주세요"), (err = false));
                err && !this.phoneNumber && ((msg = "연락처를 입력해주세요"), (err = false));
                if (!err) alert(msg);
                else {
                    if (confirm("수정 하시겠습니까?")) {
                        this.$store.dispatch("modify",
                            {   
                                belong: this.belong,
                                brn: 0,
                                name: this.name,
                                phoneNumber: this.phoneNumber,
                                type: 0
                            });
                    }
                }
            },
            deleteUser() {
                if (confirm("정말 탈퇴 하시겠습니까?")) {
                    pService.UserDelete();
                }
            },
            home() {
                this
                    .$router
                    .push("/");
            }
        }
    };
</script>
<style scoped="scoped">
    .p-button {
    margin-right: .5rem;
    margin-left: .5rem;
    }

    .login-form {
        padding: 0 40px;
        margin-top: 35px;
    }

    .login-form div {
        padding: 10px;
    }

    .login-form label {
        color: #888;
    }
    .login-form .checkbox {
        color: black;
    }

    .InputText {
        width: 100%;
    }

    #panel {
        display: block;
        width: 25%;
        margin: auto;
    }

    #login-btn {
        background-color: #3f72af;
        color: #fff;
    }

    #checkbox {
        padding : 15px;
    }

    .goback-link {
        color: #888;
        cursor: pointer;
    }

    .center-btn {
        text-align: center;
    }
</style>