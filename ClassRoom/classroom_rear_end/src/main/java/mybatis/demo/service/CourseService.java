package mybatis.demo.service;

import mybatis.demo.dao.CourseMapper;
import mybatis.demo.dao.WorkMapper;
import mybatis.demo.entity.*;
import mybatis.demo.security02.mapper.UserMapper;
import mybatis.demo.security02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkMapper workMapper;

    private void setWorkAndName(List<Course> courses) {
        for (Course course : courses) {
            List<Work> courses1 = new ArrayList<>();
            int number = courseMapper.getWorksByCid(course.getCid()).size();
            if (number>2){
                courses1.add(courseMapper.getWorksByCid(course.getCid()).get(number-1));
                courses1.add(courseMapper.getWorksByCid(course.getCid()).get(number-2));
                course.setWorkList(courses1);
            }else
                course.setWorkList(courseMapper.getWorksByCid(course.getCid()));
            course.setTeacherName(courseMapper.getTeacherName(course.getUid()));
        }
    }

    public List<Course> getAllCourseByUid(long uid){
        List<Course> courses = courseMapper.getAllCourseByUid(uid);
        setWorkAndName(courses);
        return courses;
    }

    public Course getCourseByCid(long cid){
        Course course = courseMapper.getCourseByCid(cid);
        List<Work> workList = courseMapper.getWorksByCid(cid);
        course.setWorkList(workList);
        return course;
    }

    public List<Course> getAllCourseByUidStudent(long uid){
        List<Course> courses = courseMapper.getAllCourseByUidStudent(uid);
        setWorkAndName(courses);
        return courses;
    }

    public void addCourse(Course course){
        StringBuffer courseAdd = new StringBuffer();
        String charStr = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        while (true) {
            for (int i = 5; i >= 0; i--) {
                int index = random.nextInt(charStr.length());
                courseAdd.append(charStr.charAt(index));
            }
            int count = courseMapper.getCountOfCourseAdd(courseAdd.toString());
            if (count == 0) {
                break;
            }
        }
        course.setCourseAdd(courseAdd.toString());
        courseMapper.addCourse(course);
    }

    public void deleteCourse(long cid){
        courseMapper.deleteCourse(cid);
    }

    public void addStudentCourse(ElectiveCourse electiveCourse){
        courseMapper.addStudentCourse(electiveCourse);
    }

    public Course getByCourseAdd(String courseAdd){
        return courseMapper.getByCourseAdd(courseAdd);
    }

    public void deleteCourseStudent(ElectiveCourse electiveCourse){
        courseMapper.deleteCourseStudent(electiveCourse);
    }
    public List<User> getTeacherByCid(long cid){
        List<User> users = courseMapper.getTeacherByCid(cid);
        for (User user: users) {
            user.setRoles(userMapper.getUserRolesByUid(user.getuid()));
        }
        return users;
    }

    public List<User> getStudentByCid(long cid){
        List<User> users = courseMapper.getStudentByCid(cid);
        for (User user: users) {
            user.setRoles(userMapper.getUserRolesByUid(user.getuid()));
        }
        return users;
    }

    public void deleteStudentByCid(ElectiveCourse electiveCourse){
        courseMapper.deleteStudentByCid(electiveCourse);
    }

    public List<ElectiveCourse> getScoreByCid(long cid){
        List<ElectiveCourse> electiveCourses = courseMapper.getScoreByCid(cid);
        for (ElectiveCourse electiveCourse:electiveCourses){
            electiveCourse.setStudent(userService.getUserByUid(electiveCourse.getuid()));
            electiveCourse.setCourse(courseMapper.getCourseByCid(electiveCourse.getCid()));
        }
        return electiveCourses;
    }

    public void upDateScore(ElectiveCourse electiveCourse){
        courseMapper.upDateScore(electiveCourse);
    }

    public List<WorkSheetOfStudent> getWorkScoreOfCourse(WorkSheetOfStudent workSheetOfStudent){
        List<WorkSheetOfStudent> workSheetOfStudents = courseMapper.getWorkScoreOfCourse(workSheetOfStudent);
        for (WorkSheetOfStudent workSheetOfStudent1 : workSheetOfStudents){
            workSheetOfStudent1.setStudent(userService.getUserByUid(workSheetOfStudent1.getUid()));
            workSheetOfStudent1.setWork(workMapper.getWorkByWid(workSheetOfStudent1.getWid()));
        }
        return workSheetOfStudents;
    }

    public int getCountOfCourseAndUser(ElectiveCourse electiveCourse){
        return courseMapper.getCountOfCourseAndUser(electiveCourse);
    }

    public ElectiveCourse getScoreOfCourse(ElectiveCourse electiveCourse){
        return courseMapper.getScoreOfCourse(electiveCourse);
    }
}
