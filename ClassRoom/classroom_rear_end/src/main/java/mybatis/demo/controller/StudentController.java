package mybatis.demo.controller;

import java.util.List;

import mybatis.demo.entity.Course;
import mybatis.demo.entity.ElectiveCourse;
import mybatis.demo.entity.WorkSheetOfStudent;
import mybatis.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {
	@Resource
	private CourseService courseService;

	@GetMapping("/getAllCourseByUid")
	public List<Course> getAllCourseByUid(long uid){
		return courseService.getAllCourseByUidStudent(uid);
	}

	@GetMapping("/getByCourseAdd")
	public Course getByCourseAdd(String courseAdd){
		return courseService.getByCourseAdd(courseAdd);
	}

	@GetMapping("/addStudentCourse")
	public void addStudentCourse(ElectiveCourse electiveCourse){
		courseService.addStudentCourse(electiveCourse);
	}

	@GetMapping("/deleteCourseStudent")
	public void deleteCourseStudent(ElectiveCourse electiveCourse){
		courseService.deleteCourseStudent(electiveCourse);
	}

	@GetMapping("/getWorkScoreOfCourse")
	public List<WorkSheetOfStudent> getWorkScoreOfCourse(WorkSheetOfStudent workSheetOfStudent){
		return courseService.getWorkScoreOfCourse(workSheetOfStudent);
	}

	@GetMapping("/getCountOfCourseAndUser")
	public int getCountOfCourseAndUser(ElectiveCourse electiveCourse){
		return courseService.getCountOfCourseAndUser(electiveCourse);
	}

	@GetMapping("/getScoreOfCourse")
	public ElectiveCourse getScoreOfCourse(ElectiveCourse electiveCourse){
		return courseService.getScoreOfCourse(electiveCourse);
	}

}
