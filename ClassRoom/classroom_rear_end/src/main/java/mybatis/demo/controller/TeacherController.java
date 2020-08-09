package mybatis.demo.controller;

import mybatis.demo.entity.*;
import mybatis.demo.security02.service.UserService;
import mybatis.demo.service.CourseService;
import mybatis.demo.service.WorkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;
    @Resource
    private WorkService workService;

    @GetMapping("/getAllCourseByUid")
    public List<Course> getAllCourseByUid(long uid) {
        return courseService.getAllCourseByUid(uid);
    }

    @GetMapping("/addCourse")
    public void addCourse(Course course) {
        courseService.addCourse(course);
    }

    @GetMapping("/deleteCourse")
    public void deleteCourse(long cid) {
        courseService.deleteCourse(cid);
    }

    @GetMapping("/getTeacherNameById")
    public User getTeacherNameById(long uid) {
        return userService.getTeacherNameById(uid);
    }

    @GetMapping("/getCourseByCid")
    public Course getCourseByCid(long cid){
        return courseService.getCourseByCid(cid);
    }

    @PostMapping("/addWork")
    public void addWork(@RequestBody Work work){
        workService.addWork(work);
    }

    @GetMapping("/deleteWork")
    public void deleteWork(long wid){
        workService.deleteWork(wid);
    }

    @GetMapping("/getTeacherByCid")
    public List<User> getTeacherByCid(long cid){
        return courseService.getTeacherByCid(cid);
    }

    @GetMapping("/getStudentByCid")
    public List<User> getStudentByCid(Long cid){
        return courseService.getStudentByCid(cid);
    }

    @PostMapping("/deleteStudentByCid")
    public void deleteStudentByCid(@RequestBody ElectiveCourse electiveCourse){
        courseService.deleteStudentByCid(electiveCourse);
    }

    @GetMapping("/getUserByUid")
    public User getUserByUid(long uid){
        return userService.getUserByUid(uid);
    }

    @GetMapping("/getAllWorksheeetofByWid")
    public List<WorkSheetOfStudent> getAllWorksheeetofByWid(long wid){
        return workService.getAllWorksheeetofByWid(wid);
    }

    @GetMapping("/getWorkByWid")
    public Work getWorkByWid(long wid){
        return workService.getWorkByWid(wid);
    }

    @GetMapping("/getScoreByCid")
    public List<ElectiveCourse> getScoreByCid(long cid){
        return courseService.getScoreByCid(cid);
    }

    @GetMapping("/upDateScore")
    public void upDateScore(ElectiveCourse electiveCourse){
        courseService.upDateScore(electiveCourse);
    }

    @GetMapping("/getWorkSheetOfStudentByWidAndUid")
    public WorkSheetOfStudent getWorkSheetOfStudentByWidAndUid(WorkSheetOfStudent workSheetOfStudent){
        return workService.getWorkSheetOfStudentByWidAndUid(workSheetOfStudent);
    }
    @GetMapping("/updateOfWork")
    public void updateOfWork(WorkSheetOfStudent workSheetOfStudent){
        workService.updateOfWork(workSheetOfStudent);
    }

}
