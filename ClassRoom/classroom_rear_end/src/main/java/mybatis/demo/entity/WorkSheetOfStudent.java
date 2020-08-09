package mybatis.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
@EntityScan
public class WorkSheetOfStudent {
    // 每个作业的唯一标识
    private long wsid;
    // 作业id
    private long wid;
    // 学生id
    private long uid;
    // 作业文件URl
    private String fileURL;
    // 作业文件的名字
    private String fileName;
    // 每一次作业的成绩（不能超过设置的分数）
    private int score;
    //提交作业的时间
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upTime;
    //该同学的信息
    private User student;
    //该作业的信息
    private Work work;
    private long cid;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getCid() {

        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getWsid() {
        return wsid;
    }

    public void setWsid(long wsid) {
        this.wsid = wsid;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
