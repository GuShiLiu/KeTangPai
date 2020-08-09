package mybatis.demo.dao;

import mybatis.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    //教师得到自己所有的课程
    List<Course> getAllCourseByUid(long uid);
    //学生得到自己加入的课程
    List<Course> getAllCourseByUidStudent(long uid);
    //教师添加课程
    void addCourse(Course course);
    //得到课程码的数量
    int getCountOfCourseAdd(String courseAdd);
    //通过加课码得到课程
    Course getByCourseAdd(String courseAdd);
    //删除课程
    void deleteCourse(long cid);
    //学生选课
    void addStudentCourse(ElectiveCourse electiveCourse);
    //学生退课
    void deleteCourseStudent(ElectiveCourse electiveCourse);
    //通过课程id得到作业
    List<Work> getWorksByCid(long cid);
    //通过uid得到老师
    String getTeacherName(long uid);
    //通过cid得到课程
    Course getCourseByCid(long cid);
    //通过cid得到老师的信息
    List<User> getTeacherByCid(long cid);
    //通过cid得到学生的信息
    List<User> getStudentByCid(long cid);
    //老师从此课程中删除学生
    void deleteStudentByCid(ElectiveCourse electiveCourse);
    List<ElectiveCourse> getScoreByCid(long cid);
    void upDateScore(ElectiveCourse electiveCourse);
    List<WorkSheetOfStudent> getWorkScoreOfCourse(WorkSheetOfStudent workSheetOfStudent);
    int getCountOfCourseAndUser(ElectiveCourse electiveCourse);
    ElectiveCourse getScoreOfCourse(ElectiveCourse electiveCourse);
}
