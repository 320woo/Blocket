<template>
    <div>
        <div class="login-form">
            <Panel header="Blocket을 체험해보세요." id="panel">
                <div>
                    <label for="username1">이메일</label>
                </div>
                <InputText
                    type="text"
                    id="email"
                    name="email"
                    v-model="email"
                    ref="email"
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
                    v-model="name"
                    ref="name"
                    class="InputText"/>
                <div>
                <span class="center-btn">
                    <div>
                <input type="checkbox" id="checkbox" v-model="solo" @click="Solo">
                    <label for="checkbox" class="checkbox">개인</label>
                <input type="checkbox" id="checkbox" v-model="company" @click="Company">
                    <label for="checkbox" class="checkbox">기업</label>
                    </div>
                </span>
                </div>
                <div>
                    <label for="username1">소속</label>
                </div>
                <InputText
                    type="text"
                    id="belong"
                    name="belong"
                    v-model="belong"
                    ref="belong"
                    class="InputText"/>
                <div>
                    <label for="username1">연락처</label>
                </div>
                <InputText
                    type="text"
                    id="phoneNumber"
                    name="phoneNumber"
                    v-model="phoneNumber"
                    maxlength="11"
                    ref="phoneNumber"
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
                    v-model="brn"
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
    import http from "@/utils/http-common";

    export default {
        name: "signup",
        computed: {},
        data() {
            return {
                show_brn : "",
                solo: true,
                company : false,
                email: "",
                password: "",
                name: "",
                phoneNumber: "",
                belong: "",
                brn: "",
                type: "",
                withdrawal: "",
                id: ""
            };
        },
        methods: {
            modifyUser() {
                let err = true;
                let msg = "";
                err && !this.email && ((msg = "이메일을 입력해주세요"), (err = false));
                err && !this.password && ((msg = "비밀번호를 입력해주세요"), (err = false));
                err && !this.name && ((msg = "이름을 입력해주세요"), (err = false));
                err && !this.phoneNumber && ((msg = "연락처를 입력해주세요"), (err = false));
                if (!err)alert(msg);
                
                else {
                        http.patch("/api/recruit/users/me");
                        // alert("수정 완료!")
                        // this.$router.push("/");
                }
            },
            deleteUser() {
                if (confirm("정말 탈퇴 하시겠습니까?")) {
                http.delete("/api/recruit/users/me");
                this.$store.dispatch("logout");
                this.$router.push("/");
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