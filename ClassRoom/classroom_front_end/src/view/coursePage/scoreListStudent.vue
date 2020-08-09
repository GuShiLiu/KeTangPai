<template>
  <div>
    <div style="width: 101.4%; padding-top: 10px; float: left;
          border-radius: 7px; margin-left: -30px; margin-bottom: 20px;
          border: 1px solid #E2E6ED">
      <div style="margin-left: 30px;margin-right: 30px; margin-bottom: 10px;">
        <el-table
          :data="workSheetOfStudent"
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
            prop="work.wname"
            label="作业名称">
          </el-table-column>
          <el-table-column
            prop="upTime"
            width="300"
            label="提交时间">
          </el-table-column>
          <el-table-column
            prop="score"
            label="成绩">
          </el-table-column>
          <el-table-column
            prop="work.score"
            label="满分">
          </el-table-column>
        </el-table>
      </div>
      <div>
        <span style="font-size: 30px;">你此课程的最终成绩为:{{this.score}}分</span>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "scoreListStudent",
      data(){
          return{
            workSheetOfStudent: [],
            score: '',
          }
      },
      methods:{
        getWorkScoreOfCourse(cid,uid){
          this.$axios.get('api/student/getWorkScoreOfCourse?uid='+uid+'&cid='+cid)
            .then(res=>{
              this.workSheetOfStudent = res.data;
            }).catch(err=>{
              console.log(err);
          });
        },
        getScoreOfCourse(cid,uid){
          this.$axios.get('api/student/getScoreOfCourse?cid='+cid+'&uid='+uid)
            .then(res=>{
              this.score = res.data.score;
              console.log(res);
            }).catch(err=>{
              console.log(err);
          })
        }
      },
      mounted(){
        this.getWorkScoreOfCourse(this.$route.params.cid,this.$route.params.uid);
        this.getScoreOfCourse(this.$route.params.cid,this.$route.params.uid);
      }
    }
</script>

<style scoped>

</style>
