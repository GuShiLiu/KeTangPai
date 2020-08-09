<template>
    <div>
      <el-form :model="Course" ref="ruleForm"
               label-width="100px" class="demo-ruleForm">
        <!--课程名字-->
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="Course.name" placeholder="要增加的课程名字"></el-input>
        </el-form-item>
        <!--生成的加课码，不可输入，自动生成，当课程创建的时候生成-->
        <el-form-item label="加课码">
          <el-input v-model="Course.courseAdd" :disabled="true" placeholder="不可输入，自动生成"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="float: right" type="primary" @click="alert('成功')">立即创建</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
    export default {
        name: "newCourse",
      data(){
          return{
            Course:{},
          }
      }
    }
</script>

<style scoped>

</style>
