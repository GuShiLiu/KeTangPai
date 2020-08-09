<template>
  <div>
    <v-top />
    <div style="margin-left: 100px;margin-right: 100px;padding-top: 20px;padding-bottom: 20px;box-shadow: rgb(226, 230, 237) 0px 1px 0px 0px;z-index: 1;">
      <div style="height: 40px;">
        <span style="float: left; font-size: 15px;position: relative;top: 15px;">全部课程</span>
        <h3 class="el-icon-sort" style="position: relative;height: 17px;width: 80px;top: 20px;left: 450px;">&nbsp;课程排序</h3>
        <h3 class="el-icon-folder-opened" style="position: relative;height: 18px;width: 72px;left: 475px;top: 20px;">归档管理</h3>
        <el-button style="float: right;position: relative;top: 5px;" @click="dialogVisible1 = true" type="primary" icon="el-icon-plus">
          新建课程</el-button>
      </div>

      <!--新建课程时显示的弹窗-->
      <el-container>
        <el-dialog :visible.sync="dialogVisible1" width="40%" style=" text-align: center;">
          <h2 style="margin-bottom: 30px;">新建课程</h2>
          <div>
            <el-form :model="this.addCourse1" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <!--课程名字-->
              <el-form-item label="课程名称" prop="name">
                <el-input v-model="addCourse1.name" placeholder="要增加的课程名字"></el-input>
              </el-form-item>
              <!--生成的加课码，不可输入，自动生成，当课程创建的时候生成-->
              <el-form-item label="加课码">
                <el-input v-model="addCourse1.courseAdd" :disabled="true" placeholder="不可输入，自动生成"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button style="float: right" type="primary" @click="createCourse">立即创建</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-dialog>
      </el-container>
    </div>
    <div style="margin-left: 100px; margin-right: 100px; margin-top: 30px; ">
      <!--for循环，将每一个课程都展示出来-->
      <li style="list-style: none;padding: 0;margin: 0;" v-for="item in course">
        <div style="height: 285px;width: 240px;
                    margin-right: 20px; float: left;
                    margin-bottom: 20px;">
          <!--课程名称以及加课码等-->
          <div style="height: 130px; width:100%; color: white;border-top-left-radius: 7px;
                    border-top-right-radius: 7px;"
            class="imgCard">
            <a @click="jumpToCourse(item.cid)" style="color: white; font-size: 18px; float: left;margin-left: 20px; margin-top: 30px;width: 75px;">
              {{item.name}}
            </a>
            <span style="color: white; font-size: 15px;position: relative;top: 93px;left: -86px;">
              加课码:
              <span>
                {{item.courseAdd}}
              </span>
            </span>
          </div>

          <!--近期作业-->
          <div style="height: 120px; border:1px solid rgba(226,230,237,1)">
            <ul style="float: left;margin-left: 20px; margin-top: 10px;
                       font-size: 13px; line-height: 35px; color: black;">
              <li>
                <span>近期作业</span>
              </li>
              <div>
                <div v-if="item.workList.size !== 0">
                  <li v-for="itemWork in item.workList">
                    <a style="color: black" @click="jumpToMarkingWork(itemWork.wid,item.cid)">{{itemWork.wname}}</a>
                  </li>
                </div>
                <div v-else>
                  <li>
                    <a style="color: black">暂无作业</a>
                  </li>
                </div>
              </div>
            </ul>
          </div>
          <!--该课程的老师以及操作-->
          <div style="height: 35px; background: #e2e6ed;
                    border:1px solid rgb(226,230,237);
                    border-bottom-left-radius: 7px;
                    border-bottom-right-radius: 7px;">
            <span style="float: left; margin-left: 20px; margin-top: 10px;">{{item.teacherName}}</span>
            <el-button @click="deleteCourse(item.cid)" size="mini" style="height: 20px; margin-top: 7px;
                      line-height: 7px; margin-left: 50px; float: right; margin-right: 5px;">删除课程</el-button>
          </div>
        </div>
      </li>

    </div>
  </div>
</template>

<script>
  import vtop from '../components/top.vue'
  import * as qs from "qs";
  export default {
    name: "mainTeacher",
    components: {
      'v-top': vtop,
    },

    data() {
      return {
        course: [],
        addCourse1: {},
        dialogVisible: false,
        dialogVisible1: false,
        courseAdd: '',
      }
    },
    methods: {

      createCourse() {
        this.$axios.get('api/teacher/addCourse?course=id=1&' + qs.stringify(this.addCourse1))
          .then(res => {
            alert("添加成功");
            location.reload();
          }).catch(err => {
            alert("添加失败");
            console.log(err)
          });
      },

      jumpToMarkingWork(wid, cid) {
        this.$router.push({
          name: 'markingHomework',
          params: {
            wid: wid,
            cid: cid,
            uid: this.addCourse1.uid
          }
        })
      },

      jumpToCourse(cid) {
        this.$router.push({
          name: 'workList',
          params: {
            cid: cid,
            uid: this.addCourse1.uid
          }
        });
      },

      getAllcourse(uid) {
        this.$axios.get('api/teacher/getAllCourseByUid?uid=' + uid)
          .then(res => {
            console.log(res)
            if (res) {
              this.course = res.data;
            } else
              alert("res为空")
          }).catch(err => {
            console.log(err);
          });
      },

      deleteCourse(cid) {
        this.$confirm("确定删除此课程？")
          .then(_ => {
            //删除此课程从course表中
            this.$axios.get('api/teacher/deleteCourse?cid=' + cid).then(res => {
              alert("删除成功");
              location.reload();
            })

          }).catch(_ => {})
      },

    },

    mounted() {
      if (this.$route.params.uid == null) {
        alert("用户过期，请重新登录！")
        this.$router.push('/')
      } else {
        this.getAllcourse(this.$route.params.uid);
        this.addCourse1.uid = this.$route.params.uid;
      }
    },

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
