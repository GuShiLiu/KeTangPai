<template>
  <div>
    <div>
      <el-button type="primary" style="margin-bottom: 20px;position: relative;left: -30px;float: left" @click="dialogVisible = true">
        发布新作业
      </el-button>
    </div>
    <el-container>
      <el-dialog :visible.sync="dialogVisible" width="60%" style=" text-align: center;">
        <h1>发布新作业</h1>
        <div>
          <div style="margin-top: 30px;">
            <el-form :model="Work" label-width="100px">
              <!--作业名称-->
              <el-form-item label="作业名称" prop="name">
                <el-input v-model="Work.wname"></el-input>
              </el-form-item>
              <!--作业简介-->
              <el-form-item label="作业简介" prop="introduction">
                <el-input type="textarea" v-model="Work.introduction"></el-input>
              </el-form-item>
              <!--作业截止时间-->
              <el-form-item label="截止时间" prop="endDate">
                <el-date-picker v-model="Work.endDate" type="datetime" placeholder="选择截止日期时间" value-format="yyyy-MM-dd HH:mm:ss"
                  :picker-options="endDate">
                </el-date-picker>
              </el-form-item>
              <!--是否查重-->
              <el-form-item label="是否查重" prop="delivery">
                <el-switch v-model="Work.isverify"></el-switch>
              </el-form-item>
              <!--设置此次作业的满分值-->
              <el-form-item label="满分值" prop="score">
                <el-input v-model="Work.score" max="100" min="10" type="number" style="width: 100px;"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('Work')">立即创建</el-button>
                <el-button @click="dialogVisible = false">取消</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-dialog>
    </el-container>
    <!--循环展示所有的作业-->
    <li v-for="item in Course.workList" style="list-style: none;padding: 0;margin: 0;">
      <div style="width: 101.5%; padding-top: 10px; float: left;
            border-radius: 7px; margin-left: -30px; margin-bottom: 20px;
            border: 1px solid #E2E6ED">

        <div style="margin: 20px 30px; line-height: 30px; width: 70%;">
          <div style=" float: left; font-size:15px; width: 100%;margin-bottom: 10px;">
            <span style="float: left;">发布日期: {{item.releaseDate}}</span>
          </div>

          <div style=" float: left; width: 100%;">
            <a @click="JumpToMarking(item.wid)" style="float:left; font-size:25px;">{{item.wname}}</a>

          </div>

          <div style="width: 100%; float: left;
                       margin-top:10px; line-height: 30px;">
            <span style="float: left;">作业要求: &nbsp;&nbsp;{{item.introduction}} </span>
            <span style="float: left;">
              <a @click="JumpToMarking(item.wid)">&nbsp;&nbsp;查看全文></a>
            </span>
          </div>

          <div style="width: 100%">
            <span style="float: left; margin-top: 10px;">截止日期: {{item.endDate}}</span>
          </div>
        </div>

        <div style="width: 30%; margin-right: 30px; float: right;margin-bottom: 10px;">
          <el-button type="danger" round style="float: right;margin-right: 30px;" @click="deleteWork(item.wid)">
            删除此作业
          </el-button>
          <el-button type="primary" round @click="JumpToMarking(item.wid)" style="float: right; margin-right:10px;">
            批阅作业
          </el-button>
        </div>
      </div>
    </li>
  </div>
</template>

<script>
  import newwork from '../../components/newWork.vue'
  import * as qs from "qs";
  export default {
    name: "workList",
    components: {
      'v-newwork': newwork,
    },

    data() {
      return {
        Work: {},
        Course: {},
        dialogVisible: false,
        uid: '',
        endDate: {
          disabledDate(date) {
            return date.getTime() <= Date.now();
          }
        },
      }
    },
    methods: {
      deleteWork(wid) {
        if (confirm("确定删除该作业吗？")) {
          this.$axios.get('api/teacher/deleteWork?wid=' + wid)
            .then(res => {
              alert("删除作业成功！");
              location.reload();
            })
        }
      },

      JumpToMarking(wid) {
        this.$router.push({
          name: 'markingHomework',
          params: {
            wid: wid,
            uid: this.uid,
            cid: this.Work.cid
          }
        })
      },

      submitForm() {
        console.log(this.Work)
        if (this.Work.isverify === true) {
          this.Work.isverify = 1;
        } else {
          this.Work.isverify = 0;
        }
        this.$axios.post('api/teacher/addWork', this.Work)
          .then(res => {
            alert("发布作业成功！");
            location.reload();
            console.log(res);
          }).catch(err => {
            console.log(err);
            alert("发布失败，请重新尝试！");
          });
      },

      getCourse(cid) {
        this.$axios.get('api/teacher/getCourseByCid?cid=' + cid)
          .then(res => {
            this.Course = res.data;
          }).catch(err => {
            console.log(err);
          })
      },

    },

    mounted() {
      if (this.$route.params.cid !== undefined) {
        this.getCourse(this.$route.params.cid);
        this.Work.cid = this.$route.params.cid;
        this.uid = this.$route.params.uid;
      }
    },


  }
</script>

<style scoped>

</style>
