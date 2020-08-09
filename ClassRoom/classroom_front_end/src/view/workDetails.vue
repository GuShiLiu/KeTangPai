<template>
  <div>
    <v-top />
    <div style="float: left; height: 50px; width:100%; z-index:1;">
      <span style="margin-left: 100px; margin-top: 20px; font-size: 15px; float: left;">
        <el-button plain class="el-icon-back" @click="$router.go(-1)"> 返回</el-button>
      </span>
    </div>
    <div style="margin: 20px 100px; ">
      <div style="width: 100%; height: 100px; margin-bottom: 20px;position: relative;top: 25px;">
        <div style="width:100%; float: left;
                    font-size: 20px; line-height: 50px;">
          <span style="float: left;position: relative;left: 105px;">作业名称:{{work.wname}}</span>
          <span style="float: right;">
            <el-button type="text" @click="dialogVisible=true" class="el-icon-document" style="position: relative;top: 5px;left: -100px;color: #2C3E50;">&nbsp;&nbsp;查看谁交了</el-button>
          </span>
          <el-container>
            <el-dialog :visible.sync="dialogVisible" width="30%" style=" text-align: center; line-height: 70px;">
              <h2>您不是VIP,开通VIP即可享受此服务</h2>
              <el-button type="danger" @click="jumpToVIP()">立即开通</el-button>
            </el-dialog>
          </el-container>
        </div>
        <div style="font-size: 15px;height: 80px; width:100%;margin-bottom: 20px;">
          <span style="float: left; margin-bottom: 20px;position: relative;left: 105px;">作业要求:{{work.introduction}}</span>
        </div>
        <div style="font-size: 15px;width: 204px;height: 23px;top: -15px;position: relative;">
          <span style="float:left;position: relative;left: 105px;">截止日期:{{work.endDate}}</span>
        </div>
      </div>
      <div style="border: 2px solid #E2E6ED; width: 85%;border-radius: 7px; margin-right: 100px; margin-bottom: 20px; margin-top: 30px;position: relative;top: 45px;left: 105px;">
        <el-upload style="margin-top: 70px; margin-bottom: 70px; padding-left: 70px; padding-right: 70px;" class="upload-demo"
          action="api/upAndDownFile/uploadFile" :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove"
          :data="{uid:this.uid,wid:this.work.wid}" multiple :limit="1" :on-exceed="handleExceed" :file-list="fileList">
          <el-button size=" medium" type="primary" class="el-icon-circle-plus-outline" style="width: 150%;position: relative;left: -57px;">
            &nbsp;&nbsp;添加作业文件
          </el-button>
          <div slot="tip" class="el-upload__tip" style="margin-top: 30px;margin-left: -25px;margin-bottom: 30px;">
            <li style="list-style: none">支持各种格式文件上传</li>
            <li style="list-style: none">只可上传一个文件</li>
          </div>
        </el-upload>
      </div>
    </div>
  </div>
</template>

<script>
  import vtop from '../components/top.vue'
  export default {
    name: "workDetails",
    components: {
      'v-top': vtop,
    },
    data() {
      return {
        //{name : url:}
        fileList: [{}],
        dialogVisible: false,
        work: {},
        uid: '',
        workSheetOfStudent: {},
      };
    },
    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },

      jumpToVIP() {
        this.$router.push("/VIP");
      },
      getWorkByWid(wid) {
        this.$axios.get('api/teacher/getWorkByWid?wid=' + wid)
          .then(res => {
            this.work = res.data;
          }).catch(err => {
            console.log(err);
          });
      },
      getWorkSheetOfStudentByWidAndUid(wid, uid) {
        this.$axios.get('api/teacher/getWorkSheetOfStudentByWidAndUid?wid=' + wid + '&uid=' + uid)
          .then(res => {
            console.log(res);
            this.workSheetOfStudent = res.data;
            this.fileList[0].name = res.data.fileName;
            this.fileList[0].url = res.data.fileURL;
            console.log(this.fileList);
          }).catch(err => {
            console.log(err);
          });
      }
    },
    mounted() {
      this.uid = this.$route.params.uid;
      this.work.wid = this.$route.params.wid;
      this.getWorkByWid(this.$route.params.wid);
      this.getWorkSheetOfStudentByWidAndUid(this.$route.params.wid, this.$route.params.uid);
    }
  }
</script>

<style>
  input[type="file"] {
    display: none;
  }
</style>
