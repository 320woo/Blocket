<template>
    <header>
        <h1>
            <router-link to="/"><img :src="require(`@/assets/images/Blocket-logo.png`)"/></router-link>
        </h1>
        <div class="menuWrap">
            <ul class="menu" v-if="this.$store.state.user.show">
                <li>
                    <router-link to="/login"><Button id="login">로그인</Button></router-link>
                </li>
                <li>
                    <router-link to="/join"><Button id="join">회원 가입</Button></router-link>
                </li>
            </ul>
            <ul class="menu" v-else>
                <li>
                    <router-link to="/personalInfo"><Button id="personal">내 정보</Button></router-link>
                </li>
                <li>
                    <router-link to="/modify"><Button id="modify" @click="me">회원 수정</Button></router-link>
                </li>
                <li>
                    <Button id="join" @click="logout">로그 아웃</Button>
                </li>
            </ul>
        </div>
    </header>
</template>

<script>
import http from "@/utils/http-common";
    export default {
        data() {
            return {}
        },
        methods: {
            me() {
                console.log("내 정보 가져오기")
                http
                .get("/api/recruit/users/me")
                .then(({ data }) => {
                    console.log("내 데이터 : " + data);
                })
            },
            logout() {
                this.$router.push("/");
                this.$store.dispatch("logout");
                alert("로그아웃 완료")
            }
        },
    }
</script>

<style scoped="scoped">
	Button {
		height: 30px;
	}
	#login {
		position: absolute;
        top: -5px;
        right: 110px;
		height: 30px;
		width: 74px;
		background-color : white; color : black; border : 1px solid black;
	}

	#login:hover {
        background-color: black;
        color: white;
    }

	#join {
		position: absolute;
        top: -5px;
        right: 5px;
		height: 30px;
		width: 95px;
        background-color : white; color : black; border : 1px solid black;
	}

	#join:hover {
        background-color: black;
        color: white;
    }

    #modify {
		position: absolute;
        top: -5px;
        right: 110px;
		height: 30px;
		width: 95px;
		background-color : white; color : black; border : 1px solid black;
	}

	#modify:hover {
        background-color: black;
        color: white;
    }

    #personal {
        position: absolute;
        top: -5px;
        right: 215px;
		height: 30px;
		width: 79px;
		background-color : white; color : black; border : 1px solid black;
    }

    #personal:hover {
        background-color: black;
        color: white;
    }


    header {
        width: 100%;
        text-align: center;
        position: relative;
        height: 60px;
		box-shadow: 0 2px 0 #ddd;
    }
    header h1 {
        position: absolute;
        top: -20px;
        left: 90px;
    }
    header ul.menu:after {
        display: block;
        clear: both;
        content: '';
    }
    header ul.menu {
        position: absolute;
        right: 150px;
    }
    header ul.menu li {
        float: left;
        padding: 5px 5px;
        list-style: none;
    }

    a {
        text-decoration: none;
        color: #333;
    }

	img {
		height: 60px;
	 	width: 200px;
	}
</style>