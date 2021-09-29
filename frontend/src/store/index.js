import {createStore} from "vuex";
import http from "@/utils/http-common";

export default createStore({
    state: {
        user: {
            userId: 0,
            walletAddress: null,
            accessToken: null,
            show: true
        }
    },
    mutations: {
        setUserId(state, id) {
            state.user.userId = id
        },
        setWalletAddress(state, address) {
            state.user.walletAddress = address
        },
        logout(state) {
            state.user.userId = 0
            state.user.walletAddress = null
            state.user.show = true
        },
        login(state, payload) {
            console.log("payload" + payload.accessToken)
            state.user.accessToken = payload.accessToken;
            state.user.show = false;
        }
    },
    actions: {
        saveWalletInDB({
            state
        }, payload) {
            console.log(state)
            console.log(payload)
        },
        login(context, {email, password}) {
            console.log("로그인");
            console.log(email + " " + password);

            //들고간 값을 통해서 post요청을 해줍니다. post요청을 하고 나서 받은 객체를 mutations로 보내줍니다. (payload)

            http
                .post("/api/recruit/users/login", {
                    email: email,
                    password: password
                })
                .then(({data}) => {
                    console.log(data);
                    if (data.statusCode == 404) {
                        alert("아이디 또는 비밀번호가 일치하지 않습니다.");
                    } else {
                        context.commit("login", data);
                    }
                })
                .catch((error) => {
                    alert("로그인 오류");
                    console.dir(error);
                });
        },
        logout(context) {
            console.log("로그아웃");
            context.commit("logout");
        },
        checkEmail(context, {email}) {
            console.log("중복체크 : " + email);
            console.log(context);
            http
                .get("/api/recruit/users/" + email , {
                    email: email
                })
                .then(({ data }) => {
                    console.log(data)
                    if (data.statusCode == 200) {
                        alert("가입이 가능한 이메일입니다.")
                    }
                    else {
                        alert("이미 있는 이메일입니다.")
                    }
                })
        }
    },
    modules: {}
});
