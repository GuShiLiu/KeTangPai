<template xmlns="http://www.w3.org/1999/html">
  <div class="content">
    <el-form class="login-container" ref="user" :model="user" label-width="80px">
      <h1 style="position: relative; left: -135px;font-family: cursive;">账号登录</h1>
      <el-form-item label="账号:" class="input-box">
        <el-input v-model="user.username" @keyup.enter.native="submit(user)" placeholder="请输入您的学号/工号" label-width="80px"></el-input>
      </el-form-item>
      <el-form-item label="密码:" class="input-box">
        <el-input type="password" v-model="user.password" @keyup.enter.native="submit(user)" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox v-model="checked" style="position: relative;left:-100px; top: 0.9375rem;">下次自动登录</el-checkbox>
      <el-link :underline="false" style="position: relative;left:100px;top: 0.9375rem;">忘记密码？</el-link>
      <el-form-item>
        <el-button type="primary" style="width: 360px; position: relative; left: -85px; top: 2.625rem;" @click="submit(user)">登录</el-button>
      </el-form-item>
      <p style="position: relative;left: 110px;top: 45px; color: #CAC6C6;">还没有账号？</p>
      <router-link to="/loginUp">
        <el-button type="text" style="position: relative; left: 165px; top: 16px;font-size: 13px;">去注册</el-button>
      </router-link>
    </el-form>
  </div>
</template>

<script>
  import * as qs from "qs";
  export default {
    data() {
      return {
        type: '',
        user: {},
        username: '',
        password: '',
      }
    },
    methods: {
      submit() {
        this.login();
      },

      login() {
        console.log(qs.stringify(this.user))
        this.$axios
          .post("api/login", qs.stringify(this.user), {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            }
          })
          .then(res => {
            this.user.uid = res.data.msg.uid;
            this.user.uname = res.data.msg.uname;
            this.type = res.data.msg.roles[0].id;
            console.log(this.user.uid)
            console.log(this.type)
            this.$message({
            	type: 'success',
            	message: '登录成功!',
            });
            this.jumpToList()
          })
          .catch(err => {
            alert(err.msg);
            console.log(err);
          });
      },
      jumpToList() {
        if ((this.type === 2)) {
          alert("欢迎您," + this.user.uname[0] + "老师")
          this.$router.push({
            name: 'mainTeacher',
            params: {
              uid: this.user.uid
            }
          })
        } else if ((this.type === 3)) {
          alert(this.user.uname + "同学,你好")
          this.$router.push({
            name: 'mainStudent',
            params: {
              uid: this.user.uid
            }
          })
        } else {
          alert("本系统暂未设置管理员，请与开发人员联系")
        }
      }
    },

  }
</script>
<style scoped>
  .content {
    background-image: url("./image/login-bg2.jpg");
    background-size: cover;
    position: relative;
    text-align: center;
    margin: 0;
    padding: 0;
    width: 1536px;
    height: 730px;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: auto auto;
    width: 415px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    position: relative;
    top: 160px;
  }

  .input-box {
    margin-bottom: 24px;
    height: 45px;
    position: relative;
    left: -30px;
    top: 0.625rem;
  }
</style>
