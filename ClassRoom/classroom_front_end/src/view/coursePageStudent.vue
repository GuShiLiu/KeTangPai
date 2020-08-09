<template>
  <div>
    <v-top />
    <div style="float: left; height: 30px; width:100%; z-index:1;">
      <span style="margin-left: 70px; margin-top: 20px; font-size: 15px; float: left;">
        <el-button plain class="el-icon-back" @click="jumpToMain"> 全部课程</el-button>
      </span>
    </div>
    <div style="margin-left: 70px; height: 200px; width: 91%; margin-top: 80px;
          border-top-left-radius: 8px; border-top-right-radius: 8px;"
      class="imgCard">
      <div style="width: 100%; height: 50px;"></div>
      <span style="color: white; font-size: 50px;position: relative;left: -560px;top: -20px;font-family: serif;">
        {{name}}
      </span>
    </div>
    <div style="margin-left: 70px; margin-right: 54px;">
      <el-menu router="/coursePage" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
        style="background: rgba(241,243,244,1);width: 99%;">
        <el-menu-item index="workListStudent" style="margin-left: 35px;">
          全部作业
        </el-menu-item>
        <el-submenu style="margin-left: 25px;">
          <template slot="title">教学团队</template>
          <el-menu-item index="teamOfTeacher">老师</el-menu-item>
          <el-menu-item index="teamOfStudentStudent">学生</el-menu-item>
        </el-submenu>
        <el-menu-item index="topic" style="margin-left: 25px;">
          话题
        </el-menu-item>
        <el-menu-item index="scoreListStudent" style="margin-left: 25px;">
          <a target="_blank">成绩详情</a>
        </el-menu-item>
      </el-menu>
    </div>

    <!--显示作业等div框-->
    <div style="margin-top: 20px; margin-left: 100px; margin-right: 55px;">
      <router-view />
    </div>
  </div>
</template>

<script>
  import scoreListStudent from '@/view/coursePage/scoreListStudent.vue'
  import teamOfStudentStudent from '@/view/coursePage/teamOfStudentStudent.vue'
  import teamOfTeacher from '@/view/coursePage/teamOfTeacher.vue'
  import workListStudent from '@/view/coursePage/workListStudent.vue'
  import vtop from '../components/top.vue'

  export default {
    name: "coursePageStudent",
    props: {
      active: {
        type: String,
        required: true
      }
    },
    components: {
      scoreListStudent,
      teamOfStudentStudent,
      teamOfTeacher,
      workListStudent,
      'v-top': vtop,
    },
    data() {
      return {
        name: '',
        uid: '',
        courseAdd: '',
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        this.$emit('update:active', key)
      },

      jumpToMain() {
        this.$axios.get('api/teacher/getUserByUid?uid=' + this.uid)
          .then(res => {
            if (res.data.roles[0].id === 2) {
              this.$router.push({
                name: 'mainTeacher',
                params: {
                  uid: this.uid
                }
              })
            } else if (res.data.roles[0].id === 3) {
              this.$router.push({
                name: 'mainStudent',
                params: {
                  uid: this.uid
                }
              })
            }
          }).catch(err => {
            console.log(err);
          })

      },

      getCourse(cid) {
        this.$axios.get('api/teacher/getCourseByCid?cid=' + cid)
          .then(res => {
            this.name = res.data.name;
          }).catch(err => {
            console.log(err);
          })
      }

    },
    mounted() {
      if (this.$route.params.cid !== undefined) {
        this.getCourse(this.$route.params.cid);
        this.uid = this.$route.params.uid;
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

  .img {
    width: 100%;
    height: 100%;
    background: url('../../image/logo.jpg') center center no-repeat;
    background-size: cover;
  }
</style>
