package mybatis.demo.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class ElectiveCourse {
    //id
    private long ecid;
    //学生id
    private long uid;
    //课程id
    private long cid;
    //学生在此课程的成绩
    private int score;
    //该课程的信息
    private Course course;
    //该学生的信息
    private User student;

    public long getEcid() {
        return ecid;
    }

    public void setEcid(long ecid) {
        this.ecid = ecid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public long getuid() {
        return uid;
    }

    public void setuid(long uid) {
        this.uid = uid;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
