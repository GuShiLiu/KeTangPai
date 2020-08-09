<template>
    <div>
      <el-container>
        <el-dialog :visible.sync="dialogVisible" width="40%" style=" text-align: center;">
          <h1>查看详细信息</h1>
          <div>
            <div style="margin-top: 30px;">
              <el-form :model="UserChecked" label-width="100px">
                <el-form-item label="学号" prop="username">
                  <el-input v-model="UserChecked.username" disabled></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="uname">
                  <el-input v-model="UserChecked.uname" disabled></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="usex">
                  <el-input v-model="UserChecked.usex" disabled></el-input>
                </el-form-item>
                <el-form-item label="学校" prop="school">
                  <el-input v-model="UserChecked.school" disabled></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                  <el-input v-model="UserChecked.phone" disabled></el-input>
                </el-form-item>
                <el-button type="primary" @click="dialogVisible=false" style="margin-right: 20px;">确定</el-button>
                <el-button type="danger" @click="deleteStudent(UserChecked.uid)">退选该学生</el-button>
              </el-form>
            </div>
          </div>
        </el-dialog>
      </el-container>
      <div style="width: 101.4%; padding-top: 10px; float: left;
          border-radius: 7px; margin-left: -30px; margin-bottom: 20px;
          border: 1px solid #E2E6ED">
        <div style="margin-left: 30px;margin-right: 30px; margin-bottom: 10px;">
          <el-table
            :data="User"
            stripe
            style="width: 100%">
            <el-table-column
              prop="username"
              label="学号">
            </el-table-column>
            <el-table-column
              prop="uname"
              label="姓名">
            </el-table-column>
            <el-table-column
              prop="usex"
              label="性别">
            </el-table-column>
            <el-table-column
              prop="school"
              label="学校">
            </el-table-column>
            <el-table-column
              prop="phone"
              label="电话">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="popMsg(User[scope.$index].uid)" type="text" size="mini">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "teamOfStudent",
      data(){
          return{
            User:[],
            UserChecked:{},
            dialogVisible:false,
            electiveCourse:{},
          }
      },
      methods:{
        getTeacherByCid(cid){
            this.$axios.get('api/teacher/getStudentByCid?cid='+cid)
              .then(res=>{
                this.User = res.data;
              }).catch(err=>{
                console.log(err);
            })
        },

        deleteStudent(uid){
          if (confirm("确定退选该学生吗？")) {
            this.electiveCourse.uid = uid;
            this.$axios.post('api/teacher/deleteStudentByCid',this.electiveCourse)
              .then(res => {
                console.log(res);
                alert("删除成功！");
                location.reload();
              }).catch(err => {
              alert("删除失败，请稍后尝试！")
              console.log(err);
            })
          }
        },

        popMsg(uid){
          console.log(uid);
          this.dialogVisible = true;
          console.log(this.User);
          for (let i=0;i<this.User.length;i++){
            console.log(this.User[i]);
            if (this.User[i].uid === uid){
              this.UserChecked = this.User[i];
            }
          }
        },
      },
      mounted(){
        this.getTeacherByCid(this.$route.params.cid);
        this.electiveCourse.cid = this.$route.params.cid;
      }
    }
</script>

<style scoped>

</style>
