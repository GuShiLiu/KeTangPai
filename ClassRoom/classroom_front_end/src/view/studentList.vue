<template>
    <div>       
        <div class="add-box">
          <el-button @click="jumpToAddPage()" type="primary" size="small">新增</el-button>
        </div>
        <el-table :data="students" class='table-center'>
          <el-table-column prop="uId" label="ID" width="180"/>
          <el-table-column prop="name" label="姓名" width="180"/>
          <el-table-column prop="sex" label="性别" width="180"/>
          <el-table-column prop="age" label="年龄" width="180"/>
          <el-table-column label="操作"  width="150">
            <template slot-scope="scope">
              <el-button @click="jumpToEditPage(scope.row.uId)" type="text" size="small">编辑</el-button>
              <el-button @click="removeStudent(scope.row.uId)" type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
    </div>
</template>
<script>
export default {
  data() {
    return {
      students: [],
    }
  },
  methods: {
    getAllStudent(){
      this.$axios.get("api/test/getAllStudent")
      .then(res => {
          this.students = res.data;
      })
      .catch(err => {
          console.log(err);
      });
    },
    
    removeStudent(uId) {
      this.$axios.get("api/test/removeStudentById", {params: {uId: uId}})
      .then(() => {
        this.getAllStudent()
      })
      .catch(err => {
        alert("删除失败")
      })
    },  

    jumpToAddPage() {
      this.$router.push({name: 'studentAddForm', params: {type: 'add'}})
    },

    jumpToEditPage (uId) {
      this.$router.push({name: 'studentEditForm', params: {type: 'edit', id: uId}})
    },

  },
  mounted() {
    this.getAllStudent()
  }
}
</script>
<style scoped>
.add-box{
  float: right;
  margin-right: 200px;
}
.table-center {
  padding-left: 25%;
}
</style>