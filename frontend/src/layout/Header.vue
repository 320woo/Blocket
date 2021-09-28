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
                    <Button id="join" @click="logout">로그 아웃</Button>
                </li>
            </ul>
        </div>
    </header>
</template>

<script>
    export default {
        data() {
            return {}
        },
        methods: {
            logout() {
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
		background-color: #3F72AF;
	}

	#join:hover {
        background-color: #f9f7f7;
        color: #112d4e;
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