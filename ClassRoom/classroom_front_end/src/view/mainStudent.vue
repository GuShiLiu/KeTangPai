<template>
  <div>
    <v-top />
    <div style="margin-left: 100px;margin-right: 100px;padding-top: 20px;padding-bottom: 20px;box-shadow: rgb(226, 230, 237) 0px 1px 0px 0px;z-index: 1;">
      <div style="height: 40px;">
        <span style="float: left; font-size: 15px;position: relative;top: 15px;">全部课程</span>
        <h3 class="el-icon-sort" style="position: relative;height: 17px;width: 80px;top: 20px;left: 450px;">&nbsp;课程排序</h3>
        <h3 class="el-icon-folder-opened" style="position: relative;height: 18px;width: 72px;left: 475px;top: 20px;">归档管理</h3>
        <el-button style="float: right;position: relative;top: 5px;" @click="dialogVisible = true" type="primary" icon="el-icon-plus">
          加入课程</el-button>
      </div>

      <!--加入课程时显示的弹窗-->
      <el-container>
        <el-dialog :visible.sync="dialogVisible" width="30%" style=" text-align: center;">
          <h2 style="margin-bottom: 30px;">选修课程</h2>
          <span>请输入加课码 </span>
          <el-input v-model="courseAdd" style="width: 300px;"></el-input>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @keyup.enter.native= "addCourse()" @click="addCourse()">加 入</el-button>
          </span>
        </el-dialog>
      </el-container>
    </div>
    <div style="margin-left: 100px; margin-right: 100px; margin-top: 30px; ">
      <!--for循环，将每一个课程都展示出来-->
      <li style="list-style: none;padding: 0;margin: 0;" v-for="item in course">
        <div style="height: 285px;width: 240px;
                    margin-right: 50px; float: left;
                    margin-bottom: 20px;">
          <!--课程名称以及加课码等-->
          <div style="height: 130px; width:100%; color: white;border-top-left-radius: 7px;
                    border-top-right-radius: 7px;"
            class="imgCard">
            <a style="color: white; font-size: 18px; float: left;margin-left: 20px; margin-top: 30px;width: 75px;"
              @click="jumpToCourse(item.cid)">
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
                    <a style="color: black" @click="JumpToWorkDetails(itemWork.wid)">{{itemWork.wname}}</a>
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
            <span style="float: left; margin-left: 20px; margin-top: 10px;">老师：{{item.teacherName}}</span>
            <el-button @click="deleteCourse(item.cid)" size="mini" style="height: 20px; margin-top: 7px;
                      line-height: 7px; margin-left: 50px; float: right;margin-right: 10px;">退选课程</el-button>
          </div>
        </div>
      </li>
    </div>
  </div>
</template>

<script>
  import vtop from '../components/top.vue'
  import newwork from '../components/newCourse.vue'
  import * as qs from "qs";
  import Dialog from "vue-beauty/vb/components/modal/dialog";
  export default {
    name: "main.vue",
    components: {
      Dialog,
      'v-top': vtop,
      'v-newcourse': newwork
    },

    data() {
      return {
        course: [],
        dialogVisible: false,
        dialogVisible1: false,
        courseAdd: '',
        electiveCourse: {},
      }
    },
    methods: {
      getAllcourse(uid) {
        this.$axios.get('api/student/getAllCourseByUid?uid=' + uid)
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
      JumpToWorkDetails(wid) {
        this.$router.push({
          name: 'workDetails',
          params: {
            wid: wid,
            uid: this.electiveCourse.uid
          }
        })
      },

      addCourse() {
        if (this.courseAdd === "" || undefined === this.courseAdd || this.courseAdd == null) {
          alert("请输入加课码");
        } else {
          // 查找所有课程并且关闭弹窗
          this.$confirm('确认添加？').then(_ => {
            this.$axios.get('api/student/getByCourseAdd?courseAdd=' + this.courseAdd)
              .then(res => {
                if (res.data.cid !== undefined) {
                  this.electiveCourse.cid = res.data.cid;
                  this.$axios.get('api/student/getCountOfCourseAndUser?uid=' + this.electiveCourse.uid + '&cid=' +
                      this.electiveCourse.cid)
                    .then(res => {
                      if (res.data > 0) {
                        alert("您已选修此课程，请勿重复选择!");
                      } else {
                        this.$axios.get('api/student/addStudentCourse?electiveCourse=id=1&' + qs.stringify(this
                            .electiveCourse))
                          .then(res => {
                            alert("加课成功！");
                            location.reload();
                          }).catch(err => {
                            console.log(err)
                          });
                      }
                    })
                } else {
                  alert("无此课程！请检查您的加课码！");
                }
              }).catch(err => {
                console.log(err)
                alert("此课程不存在！")
              })
          }).catch(_ => {});
        }
      },

      deleteCourse(cid) {
        this.$confirm("确定退选此课程？")
          .then(_ => {
            this.$axios.get(
                `api/student/deleteCourseStudent?electiveCourse?id=1&cid=${cid}&uid=${this.electiveCourse.uid}`)
              .then(res => {
                alert("退选成功！");
                location.reload();
              }).catch(err => {
                alert("退选失败，请联系老师");
              })
            //在课程与学生对应的表中删除
          }).catch(_ => {})
      },

      jumpToCourse(cid) {
        this.$router.push({
          name: 'workListStudent',
          params: {
            cid: cid,
            uid: this.electiveCourse.uid
          }
        });
      },

    },

    mounted() {
      if (this.$route.params.uid == null) {
        alert("用户过期，请重新登录！")
        this.$router.push('/')
      } else {
        this.getAllcourse(this.$route.params.uid);
        this.electiveCourse.uid = this.$route.params.uid;
      }
    }
  }
</script>

<style scoped>
  .imgCard {
    width: 100%;
    height: 100%;
    background: url('./image/class1.png') center center no-repeat;
    z-index: -10;
    background-size: cover;
  }
</style>
