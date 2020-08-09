<template>
    <div>
      <div style="width: 101.4%; padding-top: 10px; float: left;
          border-radius: 7px; margin-left: -30px; margin-bottom: 20px;
          border: 1px solid #E2E6ED">
        <div style="margin-left: 30px;margin-right: 30px; margin-bottom: 10px;">
          <el-table
            :data="electiveCourse"
            stripe
            style="width: 100%">
            <el-table-column
              prop="student.username"
              label="学号">
            </el-table-column>
            <el-table-column
              prop="student.uname"
              label="姓名">
            </el-table-column>
            <el-table-column
              prop="student.usex"
              label="性别">
            </el-table-column>
            <el-table-column
              prop="student.phone"
              label="电话">
            </el-table-column>
            <el-table-column
              prop="score"
              label="成绩">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="popMsg(electiveCourse[scope.$index].ecid)" type="text" size="mini">修改成绩</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <el-container>
          <el-dialog :visible.sync="dialogVisible" width="30%" style=" text-align: center;">
            <h2 style="margin-bottom: 20px;">修改成绩</h2>
            <div style="margin-bottom: 30px;">
              <span style="margin-right: 30px;">请输入该同学课程成绩</span>
              <el-input type="number" min="0" max="100" @blur="BlurText($event)" style="width: 200px;" v-model="score">
              </el-input>
            </div>
            <el-button type="primary" @click="upDateScore">确定</el-button>
            <el-button @click="dialogVisible=false">取消</el-button>
          </el-dialog>
        </el-container>
      </div>
    </div>
</template>

<script>
    export default {
        name: "scoreList",
      data(){
          return{
            electiveCourse: [],
            dialogVisible: false,
            score: '',
            ecid: '',
            electiveCourse1:{},
          }
      },
      methods: {
        getScoreByCid(cid) {
          this.$axios.get('api/teacher/getScoreByCid?cid=' + cid)
            .then(res => {
              console.log(res);
              this.electiveCourse = res.data;
            }).catch(err => {
            console.log(err);
          });
        },
        BlurText(e) {
          let boolean = new RegExp("^[1-9][0-9]*$").test(e.target.value)
          if (!boolean) {
            this.$message({
              type:'error',
              message:'请输入合法的成绩(成绩需要是整数)'
            });
            e.target.value = ''
          }
        },
        popMsg(ecid) {
          this.dialogVisible = true;
          this.electiveCourse1.ecid = ecid;
          this.ecid = ecid;
        },
        upDateScore() {
          if (this.score !== ''&&this.score<=100&&this.score>=0){
            if (confirm("确认修改吗？")) {
              this.electiveCourse1.score = this.score;
              let param = {ecid: this.ecid,score: this.score};
              console.log(this.electiveCourse1)
              this.$axios.get('api/teacher/upDateScore?ecid='+this.ecid+"&score="+this.score)
                .then(res => {
                  console.log(res)
                  alert("修改成功！");
                  location.reload();
                }).catch(err => {
                alert("修改失败，请检查成绩")
                console.log(err);
              });
            }
          }else {
            this.$message({
              type:'error',
              message:'成绩应该在0-100之间'
            });
          }
        }
      },
      mounted(){
        this.getScoreByCid(this.$route.params.cid);
      }
    }
</script>

<style scoped>

</style>
