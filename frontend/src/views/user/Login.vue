<template>
    <div>
        <div class="login-form">
            <Panel header="로그인하세요" id="panel">
                <div>
                    <InputText
                        type="text"
                        id="email_id"
                        name="email_id"
                        v-model="email_id"
                        ref="email_id"
                        placeholder="이메일"
                        class="InputText"/>
                </div>
                <div>
                    <InputText
                        type="password"
                        id="password"
                        name="password"
                        v-model="password"
                        ref="password"
                        placeholder="비밀번호"
                        @keyup.enter="checkValue"
                        class="InputText"/>
                </div>
                <div>
                    <Button id="login-btn" @keyup.enter="checkValue" @click="checkValue">
                        로그인
                    </Button>
                    <!-- <button style="width: 100%" class="btn btn-mint btn-primary" @click="join"
                    > 회원가입 </button> -->
                </div>
            </Panel>
            <div>
                <span style="color:#888;">
                    아직 계정이 없으신가요?
                </span>
                <span @click="join" class="sign-up-link">
                    회원가입
                </span>
            </div>
        </div>
    </div>
</template>
<script>
    // import {mapGetters} from "vuex";
    export default {
        name: "login",
        computed: {},

        data() {
            return {email_id: "", password: ""};
        },
        methods: {
            join() {
                this
                    .$router
                    .push("/join");
            },
            checkValue() {
                // 사용자 입력값 체크하기
                let err = true;
                let msg = "";
                !this.email_id && ((msg = "ID를 입력해주세요"), (err = false));
                !this.password && ((msg = "비밀번호를 입력해주세요"), (err = false));

                if (!err) 
                    alert(msg);
                else 
                    this
                        .$store
                        .dispatch("login", {
                            email_id: this.email_id,
                            password: this.password
                        });
                
                if (this.$store.state.user.nickname !== undefined) {
                    console.log(this.$store.state.user.email_id + " 로그인성공");

                    this
                        .$router
                        .push("/");
                }
            }
        }
    };
</script>

<style scoped="scoped">
    #panel {
        display: block;
        width: 25%;
        margin-left: auto;
        margin-right: auto;
        margin-top : auto;
        margin-bottom: auto;
    }
    .InputText {
        width : 100%;
    }

    .login-form {
        padding: 0 40px;
        margin-top: 15%;
        text-align: center;
    }

    .login-form div {
        padding: 10px;
    }

    #login-btn {
        background-color: #3F72AF;
        color: #fff;
    }

    #login-btn:hover {
        background-color: #F9F7F7;
        color: #112D4E;
    }

    .sign-up-link {
        color: #333;
        font-weight: 600;
        cursor: pointer;
    }

    .sign-up-link:hover {
        color: #3F72AF;
        font-weight: 600;
    }
</style>