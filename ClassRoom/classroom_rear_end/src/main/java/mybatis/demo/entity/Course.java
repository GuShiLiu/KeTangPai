package mybatis.demo.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan
public class Course {
    private long cid;
    //课程名字
    private String name;
    //课程加课码
    private String courseAdd;
    //课程老师的id
    private long uid;
    //课程的老师
    private String teacherName;
    //课程的作业
    private List<Work> workList;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseAdd() {
        return courseAdd;
    }

    public void setCourseAdd(String courseAdd) {
        this.courseAdd = courseAdd;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
