<template>
  <div>
    <v-top />
    <div style="float: left; height: 30px; width:100%; z-index:1;">
      <span style="margin-left: 70px; margin-top: 20px; font-size: 15px; float: left;">
        <el-button plain class="el-icon-back" @click="$router.go(-1)"> 返回</el-button>
      </span>
    </div>
    <div style="margin-left: 70px; height: 200px; width: 90%; margin-top: 80px;
            border-top-left-radius: 8px; border-top-right-radius: 8px;"
      class="imgCard">
      <div style="width: 100%; height: 50px;"></div>
      <span style="color: white; font-size: 50px;position: relative;left: -475px;top: -20px;font-family: serif;">{{work.wname}}</span>
    </div>
    <div style="width: 90%; margin-left:70px; margin-top: 10px;
                float: left; border: 1px solid #E2E6ED">
      <el-table :data="worksheetofstudent" stripe style="width: 85%; margin-left:120px;">
        <el-table-column prop="student.username" label="学号" width="150">
        </el-table-column>
        <el-table-column prop="student.uname" label="姓名" width="150">
        </el-table-column>
        <el-table-column prop="score" label="分数" width="180">
          <!--<el-input style="width: 50px;" v-model="this.score"></el-input>-->
        </el-table-column>
        <!--满分值为work.score-->
        <el-table-column label="满分值" prop="work.score" width="120">
        </el-table-column>
        <el-table-column label="作业" prop="fileName" width="180">
        </el-table-column>
        <el-table-column prop="upTime" label="提交时间">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click="downFile(worksheetofstudent[scope.$index].fileURL,worksheetofstudent[scope.$index].fileName)"
              type="text" size="mini">下载作业</el-button>
            <el-button @click="lookFile(worksheetofstudent[scope.$index].fileURL,worksheetofstudent[scope.$index].fileName)"
              type="text" size="mini" disabled>预览</el-button>
            <el-button @click="popMsg(worksheetofstudent[scope.$index].wsid)" type="text" size="mini">评分</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-container>
      <el-dialog :visible.sync="dialogVisible" width="30%" style=" text-align: center;">
        <h2 style="margin-bottom: 20px;">评分</h2>
        <div style="margin-bottom: 30px;">
          <span style="margin-right: 30px;">请输入该同学此次作业成绩</span>
          <el-input type="number" min="0" style="width: 200px;" v-model="score">
          </el-input>
        </div>
        <el-button type="primary" @click="upDateScore">确定</el-button>
        <el-button @click="dialogVisible=false">取消</el-button>
      </el-dialog>
    </el-container>

    <el-container>
      <el-dialog :visible.sync="dialogVisible1" width="70%" style=" text-align: center;">
        <h2 style="margin-bottom: 20px;">预览文件</h2>
        <div style="margin-bottom: 30px;">
          {{this.file}}
        </div>
        <el-button @click="dialogVisible1=false">取消</el-button>
      </el-dialog>
    </el-container>
  </div>
</template>

<script>
  import vtop from '../components/top.vue'
  export default {
    name: "markinghomework",
    components: {
      'v-top': vtop,
    },

    data() {
      return {
        worksheetofstudent: [],
        uid: '',
        work: '',
        name: '',
        dialogVisible: false,
        dialogVisible1: false,
        score: '',
        scoreFull: '',
        wsid: '',
        file: '',
      }
    },
    methods: {
      getAllWorksheeetofByWid(wid) {
        this.$axios.get('api/teacher/getAllWorksheeetofByWid?wid=' + wid)
          .then(res => {
            this.worksheetofstudent = res.data;
          }).catch(err => {

          });
      },

      popMsg(wsid) {
        this.dialogVisible = true;
        this.wsid = wsid;
      },
      lookFile(fileURL) {
        this.dialogVisible1 = true;
        this.$axios.post('api/upAndDownFile/download?fileName=' + fileURL)
          .then(res => {
            console.log(res);
            this.file = res.data;
          }).catch(err => {
            console.log(err);
          });
      },

      downFile(fileURL, fileName1) {
        console.log(fileURL);
        console.log(fileName1);
        // this.$axios.request('api/upAndDownFile/download?fileName='+fileURL)
        this.$axios({
          method: 'post',
          url: 'api/upAndDownFile/download?fileName=' + fileURL,
          // data: fileURL,
          headers: {},
          responseType: 'blob'
        }).then(res => {
          console.log(res)
          const content = res.data;
          const blob = new Blob([content])
          const fileName = fileName1;
          if ('download' in document.createElement('a')) { // 非IE下载
            const elink = document.createElement('a')
            elink.download = fileName;
            elink.style.display = 'none'
            elink.href = URL.createObjectURL(blob)
            document.body.appendChild(elink)
            elink.click()
            URL.revokeObjectURL(elink.href) // 释放URL 对象
            document.body.removeChild(elink)
          } else { // IE10+下载
            navigator.msSaveBlob(blob, fileName)
          }
          console.log(res);
        }).catch(err => {
          this.$message({
            type: 'error',
            message: '下载失败'
          })
          console.log(err);
        })
      },

      upDateScore() {
        if (this.score <= this.scoreFull) {
          this.$axios.get('api/teacher/updateOfWork?score=' + this.score + '&wsid=' + this.wsid)
            .then(res => {
              this.$message({
                type: 'success',
                message: '评分成功'
              });
              location.reload();
              console.log(res);
              this.dialogVisible = false;
            }).catch(err => {
              console.log(err);
              this.$message({
                type: 'error',
                message: '评分失败'
              })
            });
        } else {
          this.$message({
            type: 'error',
            message: '成绩不可以大于本次作业的满分'
          })
        }
      },

      jumpToMain() {
        this.$router.push({
          name: 'mainTeacher',
          params: {
            uid: this.uid
          }
        })
      },

      getCourseByCid(cid) {
        this.$axios.get('api/teacher/getCourseByCid?cid=' + cid)
          .then(res => {
            console.log(res);
            this.name = res.data.name;
          }).catch(err => {
            console.log(err)
          })
      },

      getWorkByWid(wid) {
        this.$axios.get('api/teacher/getWorkByWid?wid=' + wid)
          .then(res => {
            console.log(res)
            this.work = res.data;
            this.scoreFull = this.work.score;
          }).catch(err => {
            console.log(err)
          });
      },

    },
    mounted() {
      this.getAllWorksheeetofByWid(this.$route.params.wid);
      this.uid = this.$route.params.uid;
      this.getCourseByCid(this.$route.params.cid);
      this.getWorkByWid(this.$route.params.wid);
    }

  }
</script>

<style scoped>
  .imgCard {
    width: 100%;
    height: 100%;
    background: url('./image/class2.png') center center no-repeat;
    z-index: -10;
    background-size: cover;
  }
</style>
