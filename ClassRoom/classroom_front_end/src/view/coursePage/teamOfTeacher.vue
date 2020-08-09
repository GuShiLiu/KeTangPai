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
              <el-button type="primary" @click="dialogVisible=false">确定</el-button>
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
          :data="Teacher"
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
              <el-button @click="popMsg(Teacher[scope.$index].uid)" type="text" size="mini">查看</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "teamOfTeacher",
      data(){
        return{
          Teacher:[],
          UserChecked:{},
          dialogVisible:false,
        }
      },
      methods:{
        getTeacherByCid(cid){
          this.$axios.get('api/teacher/getTeacherByCid?cid='+cid)
            .then(res=>{
              this.Teacher = res.data;
              console.log(res);
            }).catch(err=>{
            console.log(err);
          })
        },

        popMsg(uid){
          console.log(uid);
          this.dialogVisible = true;
          console.log(this.Teacher);
          for (let i=0;i<this.Teacher.length;i++){
            console.log(this.Teacher[i]);
            if (this.Teacher[i].uid === uid){
              this.UserChecked = this.Teacher[i];
            }
          }
        },

      },
      mounted(){
        this.getTeacherByCid(this.$route.params.cid);
      }
    }
</script>

<style scoped>

</style>
