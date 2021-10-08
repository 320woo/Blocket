import createPersistedState from 'vuex-persistedstate'
import {createStore} from "vuex";
import http from "@/utils/http-common";
// import axios from "@/utils/bearer";
import * as pService from '@/utils/pService.js'
import vueConfig from '../../vue.config'
import axios from 'axios'
var BASE_URL = vueConfig.devServer.proxy['/blocket'].target + "/api"
var USER_URL = BASE_URL + "/recruit/users"


export default createStore({
    state: {
        user: {
            check: null,
            userId: 0,
            userEmail: null,
            username: null,
            userbelong: null,
            userbrn: null,
            userphone : null,
            type:0,
            walletAddress: null,
            accessToken: null,
            show: true,
            personalInfoId: 0,
        },
        file:{},
        verifications:{},
    },
    mutations: {
        setUserId(state, id) {
            state.user.userId = id
        },
        setUserEmail(state, userEmail) {
            state.user.userEmail = userEmail
        },
        setUserType(state, type) {
            state.user.type = type
        },
        setWalletAddress(state, address) {
            state.user.walletAddress = address
        },
        logout(state) {
            state.user.userId = 0
            state.user.personalInfoId = 0
            state.user.walletAddress = null
            state.user.show = true
        },
        login(state, payload) {
            state.user.accessToken = payload.accessToken;
            state.user.show = false;
            state.user.type=payload.type;
        },
        userinfo(state, payload) {
            console.log(payload.name);
            state.user.username = payload.name;
            state.user.userbelong = payload.belong;
            state.user.userbrn = payload.brn;
            state.user.userphone = payload.phoneNumber;
        },
        setPersonalInfoId(state, payload) {
            state.user.personalInfoId = payload
        },

        check(state, payload) {
            state.user.check = payload;
        },
        
        setFile(state,payload){
            state.file = payload;
        },
        setVerifications(state,payload){
            state.verifications = payload;
        },
        setFileVerified(state,payload){
            state.file.currentStatus = payload.currentStatus;
            state.file.reasonsRejection = payload.reasonsRejection;
        },
    },
    actions: {
        setUserEmail({ commit }, payload){
            console.log("actions.js에서 setUserEmail 호출")
            commit("setUserEmail", payload)
        },

        saveWalletInDB({
            state
        }, payload) {
            console.log(state)
            console.log(payload)
        },
        login(context, { email, password }) {
            console.log("로그인");
            http
                .post("/api/recruit/users/login", {
                    email: email,
                    password: password
                })
                .then(({data}) => {
                    console.log(data);
                    localStorage.setItem("accessToken", data.accessToken);

                    if (data.statusCode == 404) {
                        alert("아이디 또는 비밀번호가 일치하지 않습니다.");
                    } else {
                        // vuex 변수에 user 이메일 저장
                        context.commit("setUserEmail", email)
                        // vuex에 잘 저장되었나..?
                        context.commit("login", data);
                        pService.checkLogin();
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
                .get("/api/recruit/users/" + email, {email: email})
                .then(({data}) => {
                    console.log("메세지" + data.message)
                    if (data.statusCode == 200) {
                        return data.statusCode;
                    } else if(data.statusCode == 409) {
                        return data.statusCode;
                    }
                }).then( data  => {
                    console.log(data);
                    context.commit("check",data)
                })
        },
        userCheck(context) {
            axios.get(USER_URL + "/me", {
                headers: {
                    Authorization: "Bearer " + this.state.user.accessToken
                }
            }).then(({data}) => {
                context.commit("userinfo", data);
            })
        },
        modify(context, payload) {
            console.log("수정 들어옴" + context);
            console.log(payload.belong)
            if (localStorage.getItem("accessToken")) {
                const url = "/api/recruit/users/me";
                const headers = {
                    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                };
                return http.patch(url,payload,{headers}).then((res)=>{
                    if(res.data.statusCode==200){
                        console.log("수정 완료");
                    }
                }).catch((err)=>{
                    console.log(err);
                });
            }
            
        },

        Disabled(context, payload) {
            console.log("장애 여부 정보 들어옴" + context);
            console.log(payload.disabled)
            if (localStorage.getItem("accessToken")) {
                const url = "/api/recruit/personalinfo/" + payload.pid + "/disabled";
                const headers = {
                    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                };
                const disabled = payload.disabled;
                return http.patch(url, { disabled },{headers}).then((res)=>{
                    if(res.data.statusCode==200){
                        console.log("장애 여부 등록완료");
                    }
                }).catch((err)=>{
                    console.log(err);
                });
            }
            
        },
        
        async Army(context, payload) {
            console.log(context);
            console.log(payload.army)
            if (localStorage.getItem("accessToken")) {
                const url = "/api/recruit/personalinfo/" + payload.pid + "/military";
                const headers = {
                    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                };
                const militaryService = payload.army;
                await http.patch(url, { militaryService }, { headers }).then((res) => {
                    if(res.data.statusCode==200){
                        console.log("병역 등록완료");
                    }
                })
            }
        },

        getFileInfo(context,payload){
                console.log(payload);
            if(localStorage.getItem("accessToken")){
                const url = "/api/recruit/Gallery/galleryDetail/";
                return http.get(url+payload.fileId).then((res)=>{
                    console.log(res.data);
                    context.commit("setFile",res.data)
                })
            }
        },
        patchVerification(context,payload){
            console.log(payload);
            if(localStorage.getItem("accessToken")){
                const url = "/api/recruit/verification";
                const headers = {
                    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                };
                return http.patch(url,payload,{headers}).then((res)=>{
                    if(res.data.statusCode==200){
                        console.log(res.data);
                        context.commit("setFileVerified",res.data);    
                    }
                }).catch((err)=>{
                    console.log(err);
                });
            }
        },
        async getVerifications(context,payload){
            let result="";
            if(localStorage.getItem("accessToken")){
                const url = "/api/recruit/verification/list";
                const headers = {
                    Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
                };
                await http.post(url,payload,{headers}).then((res)=>{
            
                        console.log(res.data.verificationList);
                        context.commit("setVerifications",res.data.verificationList);
                        result = res.data;
                }).catch((err)=>{
                     console.log(err);
                });
            }
            return result;
        }
    },
    getters: {
        user(state){
            return state.user;
        },
        file(state){
            return state.file;
        },
        verifications(state){
            return state.verifications;
        },
    },
    modules: {},
    plugins: [ createPersistedState() ],
});
