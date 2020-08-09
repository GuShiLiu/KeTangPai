<template>
  <el-menu :default-active="activeIndex"  class="el-menu-demo" style="box-shadow: 0 0 15px #cac6c6;" mode="horizontal" @select="handleSelect">
    <img class="logo-mainblue" src="../view/image/logo-mainblue.png" />
    <el-menu-item index="1" style="position: relative;left:250px;height: 80px;line-height: 85px;">课堂</el-menu-item>
    <el-menu-item index="2" style="position: relative;left:250px;height: 80px;line-height: 85px;">精品专区</el-menu-item>
    <el-menu-item index="3" style="position: relative;left:250px;height: 80px;line-height: 85px;">我的精品</el-menu-item>
    <h4 style="position: relative;left: 405px;height: 15px;top: 20px;font-size: 15px;" class="el-icon-copy-document"> 论文查重</h4>
    <h4 style="position: relative;left: 435px;height: 12px;top: 20px;font-size: 15px;" class="el-icon-bell"></h4>
    <el-popover placement="bottom" v-model="visible">
      <div style="margin: 0;padding: 0;">
        <el-button size=" medium" type="text" style="margin: 0;padding: 0;width: 140px;position: relative;" class="el-icon-switch-button"
          @click="logout()">
          注销账户
        </el-button>
      </div>
      <el-button type="text" slot="reference" style="position: relative;left: 470px;height: 16px;top: 20px;font-size: 15px;">
        <i class="el-icon-s-custom" @click="visible = !visible"></i>
      </el-button>
    </el-popover>
  </el-menu>
</template>

<script>
  export default {
    name: "v-top",
    data() {
      return {
        activeIndex: '1',
        user: {}
      };
    },
    methods: {
      logout() {
        this.$confirm("您确定要注销吗？").then(_ => {
          //注销操作
          this.$axios.post('api/logout').then(_ => {
            //退出并跳转到登录页面
            this.$router.push('/login');
          }).catch(_ => {

          });
        }).catch(_ => {

        });
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
    }
  }
</script>

<style scoped>
  .logo-mainblue {
    position: relative;
    width: 106px;
    top: 27px;
    left: -700px;
    height: 26px;
  }
</style>
