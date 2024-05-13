<script setup lang="ts">

import {ref} from "vue";
import {useRouter} from "vue-router";
import {useLogin} from "@/apis/LoginApi";
import Base64Image from "@/components/Base64Image.vue";
import {initRoute} from "@/router/route";

const router = useRouter()
const loginApi = useLogin()

let username = ref()
let password = ref()
let code = ref()
let codeImage = ref<any>({})

loadCode()
function loadCode(){
  loginApi.code().then((res:any) => {
    codeImage.value = res
    code.value = ""
  })
}
const onLogin = async () => {
  const res:any = await loginApi.login({
    username: username.value,
    password: password.value,
    code: code.value,
    key: codeImage.value.key
  })

  localStorage.setItem(import.meta.env.VITE_TOKEN_KEY, res.token)
  await initRoute()
  await router.push({
    name: "dashboard"
  })
}
</script>

<template>
  <div class="login-main">
    <div class="login-img"></div>
    <div class="login-panel">
      <div class="login">
        <div class="login-title">
          <span>欢迎登录</span>
        </div>
        <el-form>
          <el-form-item>
            <el-input placeholder="用户名/手机号/邮箱" v-model="username"/>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="密码" type="password" v-model="password"/>
          </el-form-item>
          <el-form-item>
            <div class="code">
              <el-input v-model="code" placeholder="验证码"/>
              <base64-image style="height: 100%" v-model="codeImage.imageBase64" @click="loadCode" :key="codeImage.key"/>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onLogin">登录</el-button>
            <el-button link>忘记密码</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-main{
  width: 100vw;
  height: 100vh;
  display: flex;

  .login-img {
    width: 70%;
    background-color: #282828;
  }
  .login-panel{
    flex: 1;
    padding: 0 50px;
    position: relative;

    .login{
      position: relative;
      top: 20vh;

      .login-title{
        font-size: 1rem;
        padding: 10px 0;
      }
      .code{
        display: flex;
        height: 32px;
      }
    }
  }
}
</style>