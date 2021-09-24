<template>
    <div>
        <div class="login-form">
            <div>
                <InputText
                    type="text"
                    id="email"
                    name="email"
                    v-model="email"
                    ref="email"
                    placeholder="이메일"
                    class="form-control mt-5 mb-2"/>
            </div>
            <div>
                <InputText
                    type="password"
                    id="password"
                    name="password"
                    v-model="password"
                    ref="password"
                    placeholder="비밀번호"
                    class="form-control mb-2"/>
            </div>
            <div>
                <InputText
                    type="text"
                    id="name"
                    name="name"
                    v-model="name"
                    ref="name"
                    placeholder="이름"
                    class="form-control mb-2"/>
            </div>
            <div>
                <SelectButton v-model="value1" :options="options" class="form-control mb-2"/>
            </div>
            <div>
                <InputText
                    type="text"
                    id="belong"
                    name="belong"
                    v-model="belong"
                    ref="belong"
                    placeholder="소속"
                    class="form-control mb-2"/>
            </div>
            <div>
                <InputText
                    type="text"
                    id="phoneNumber"
                    name="phoneNumber"
                    v-model="phoneNumber"
                    ref="phoneNumber"
                    placeholder="연락처"
                    class="form-control mb-2"/>
            </div>
            <div>
                <InputText
                    type="text"
                    id="brn"
                    name="brn"
                    v-model="brn"
                    ref="brn"
                    placeholder="사업자 등록번호"
                    class="form-control mb-3"/>
            </div>
            <div>
                <Button
                    class="btn mb-5"
                    id="login-btn"
                    @keyup.enter="checkValue"
                    @click="checkValue">
                    회원가입
                </Button>
                <div class="d-flex justify-content-between">
                    <span @click="home" class="goback-link">
                        홈으로 돌아가기
                    </span>
                </div>
            </div>
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
                email: "",
                password: "",
                name: "",
                phoneNumber: "",
                belong: "",
                brn: "",
                type: "",
                withdrawal: "",
                value1: '회원',
                options: ['회원', '기업']
            };
        },
        methods: {
            checkValue() {
                // 사용자 입력값 체크하기
                let err = true;
                let msg = "";
                err && !this.email && ((msg = "이메일을 입력해주세요"), (err = false));
                err && !this.password && ((msg = "비밀번호를 입력해주세요"), (err = false));
                err && !this.name && ((msg = "이름을 입력해주세요"), (err = false));
                err && !this.phoneNumber && ((msg = "연락처를 입력해주세요"), (err = false));
                if (!err) 
                    alert(msg);
                else 
                    this.insertUser();
                }
            ,
            insertUser() {
                http
                    .post("/api/recruit/users/register", {
                        email: this.email,
                        password: this.password,
                        name: this.name,
                        phoneNumber: this.phoneNumber,
                        belong: this.belong,
                        brn: this.brn,
                        type: this.type,
                        withdrawal: 0
                    })
                    .then(({data}) => {
                        console.log(data);
                        let msg = "회원가입 실패!!";
                        if (data === "success") {
                            msg = "회원가입 완료";
                            this
                                .$router
                                .push("/");
                        }
                        alert(msg);
                    })
                    .catch((error) => {
                        alert("회원가입 실패");
                        console.dir(error);
                    });
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
    .login-form {
        padding: 0 40px;
        text-align: center;
    }

    #login-btn {
        background-color: #3F72AF;
        color: #fff;
    }

    #login-btn:hover {
        background-color: #F9F7F7;
        color: #112D4E;
    }

    .goback-link {
        color: #888;
        cursor: pointer;
    }
</style>