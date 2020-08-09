<template>
  <div style="text-align: center">
    <v-top />
    <div style="float: left; height: 50px; width:100%; z-index:1;">
      <span style="margin-left: 100px; margin-top: 20px; font-size: 15px; float: left;">
        <el-button plain class="el-icon-back" @click="$router.go(-1)"> 返回</el-button>
      </span>
    </div>
    <h3 style="color: burlywood; font-size: 30px; margin-top: 30px;">
      扫码添加小助手微信，即可开通VIP
    </h3>
    <img src="./image/Wechat.png" class="wechatimage" />
  </div>
</template>

<script>
  import vtop from '../components/top.vue'
  export default {
    name: "VIP",
    components: {
      'v-top': vtop,
    },
    methods: {
      

    }
  }
</script>

<style scoped>
  .wechatimage {
    width: 50%;
    height: 100%;
    position: relative;
  }
</style>
