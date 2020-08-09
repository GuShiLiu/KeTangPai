<template>
  <div class="cWidth cWidth-new">
    <div class="send-an-new" style="display: block;" data-role="0" data-step="1" data-intro="点击此处发起新话题，师生均可以参与">
      <el-button type="text" style="font-size: 19px;border-radius: 7px;" class="el-icon-edit" @click="dialogVisible = true">&nbsp;&nbsp;发起新话题</el-button>
      <el-dialog title="发起新话题" :visible.sync="dialogVisible" width="90%" height="auto" :before-close="handleClose">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item class="input-box1">
            <el-input placeholder="话题标题,老师和学生都可以发起话题" label-width="80px"></el-input>
          </el-form-item>
          <el-form-item class="input-box2">
              <el-input type="textarea"  placeholder="话题内容,说说你的话题内容吧!"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <div class="box">
      <h3>点击上方按钮，您可以发起新话题。</h3>
      <p>发起话题之后，无论是老师还是同学们，都可以基于此话题进行讨论</p>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        dialogVisible: false
      };
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
    }
  };
</script>

<style>
  .input-box1{
    position: relative;
    left: -35px;
    border-radius: 7px;
  }

  .input-box2{
    position: relative;
    left: -35px;
    border-radius: 7px;
  }
  .announce-page {
    padding-bottom: 110px;
    height: auto;
    overflow: hidden;
  }

  .send-an-new {
    width: 1398px;
    text-align: left;
    padding-left: 45px;
    border: 1px solid #D2D2D2;
    height: 60px;
    line-height: 60px;
    font-size: 18px;
    margin: 40px 0 0;
    position: relative;
    left: -30px;
  }

  .cWidth,
  .cWidth-new,
  .gWidth {
    margin-left: auto;
    margin-right: auto;
  }

  .cWidth-new {
    width: 1400px;
  }

  .forbiddiscuss {
    height: 94px;
    background: rgba(241, 243, 244, 1);
    border-radius: 4px;
    line-height: 94px;
    margin-top: 40px;
  }

  .box {
    width: 1398px;
    height: auto;
    background: #F1F3F4;
    border-radius: 4px;
    margin-top: 42px;
    text-align: center;
    padding: 45px 0;
    margin-left: auto;
    margin-right: auto;
    position: relative;
    left: -30px;
  }
</style>
