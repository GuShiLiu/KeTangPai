package mybatis.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mybatis.demo.entity.Student;

@Mapper
public interface StudentMapper {
	List<Student> findAllStudents();

	void saveStudent(Student student);

	void editStudent(Student student);

	Student findStudentById(Long uId);

	void removeStudent(Long uId);
}
