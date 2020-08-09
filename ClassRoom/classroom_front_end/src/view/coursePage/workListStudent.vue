<template>
  <div>
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
              <a style="float:left; font-size:25px;" @click="JumpToWorkDetails(item.wid)">{{item.wname}}</a>
          </div>

          <div style="width: 100%; float: left;
                       margin-top:10px; line-height: 30px;">
            <span style="float: left;">作业要求: &nbsp;&nbsp;{{item.introduction}} </span>
            <span style="float: left;">
              <a @click="JumpToWorkDetails(item.wid)">&nbsp;&nbsp;查看全文></a>
            </span>
          </div>

          <div style="width: 100%">
            <span style="float: left; margin-top: 10px;">截止日期: {{item.endDate}}</span>
          </div>
        </div>

        <div style="width: 30%; margin-right: 30px; float: right;margin-bottom:10px;">
          <el-button type="primary" round @click="JumpToWorkDetails(item.wid)" style="float: right; margin-right:10px;">
            提交作业
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
    name: "workListStudent",
    components:{
      'v-newwork':newwork,
    },

    data(){
      return{
        Work:{},
        Course:{},
        dialogVisible:false,
        uid:'',
        endDate:{
          disabledDate(date){
            return date.getTime() <= Date.now();
          }
        },
      }
    },
    methods:{
      getCourse(cid){
        this.$axios.get('api/teacher/getCourseByCid?cid='+cid)
          .then(res=>{
            this.Course = res.data;
          }).catch(err=>{
          console.log(err);
        })
      },
      JumpToWorkDetails(wid){
        this.$router.push({name: 'workDetails',params:{wid:wid,uid:this.uid}})
      }

    },

    mounted(){
      if (this.$route.params.cid !== undefined){
        this.getCourse(this.$route.params.cid);
        this.Work.cid = this.$route.params.cid;
        this.uid = this.$route.params.uid;
      }
    },


  }
</script>

<style scoped>

</style>
