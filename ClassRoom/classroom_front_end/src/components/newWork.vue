<template>
    <div>
      <div style="margin-top: 30px;">
        <el-form :model="Work" :rules="rules" ref="ruleForm"
                 label-width="100px" class="demo-ruleForm">
          <!--作业名称-->
          <el-form-item label="作业名称" prop="name">
              <el-input v-model="Work.name"></el-input>
          </el-form-item>
          <!--作业简介-->
          <el-form-item label="作业简介" prop="introduction">
            <el-input type="textarea" v-model="Work.introduction"></el-input>
          </el-form-item>
          <!--作业截止时间-->
          <el-form-item label="截止时间" prop="endDate">
            <el-date-picker
              v-model="Work.endDate"
              type="datetime"
              placeholder="选择截止日期时间"
              value-format="yyyy-MM-dd HH:mm:ss"
              :picker-options="endDate">
            </el-date-picker>
          </el-form-item>
          <!--是否查重-->
          <el-form-item label="是否查重" prop="delivery">
            <el-switch v-model="Work.isCheck"></el-switch>
          </el-form-item>
          <!--设置此次作业的满分值-->
          <el-form-item label="满分值" prop="score">
              <el-input-number v-model="Work.score" max="100" min="10"></el-input-number>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('Work')">立即创建</el-button>
            <el-button @click="resetForm('Work')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
    export default {
        name: "newWork",
      data(){
          return{
            endDate:{
              disabledDate(date){
                return date.getTime() <= Date.now();
              }
            },
            Work: {},
            rules: {
              name: [
                { required: true, message: '请输入作业名称', trigger: 'blur' }
              ],
              introduction: [
                { required: true, message: '请输入作业要求', trigger: 'change' }
              ],
              score:[
                { required: true, message: '最大分数不可超过100，最小不可小于10', trigger:'blur'}
              ]
            }
          }
      },
      methods:{
        submitForm(Work) {
          this.$refs[Work].validate((valid) => {
            if (valid) {
              alert('submit!');
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        resetForm(Work) {
          this.$refs[Work].resetFields();
        }
      },
    }
</script>

<style scoped>

</style>
