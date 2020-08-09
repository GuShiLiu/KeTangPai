package mybatis.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mybatis.demo.dao.StudentMapper;
import mybatis.demo.entity.Student;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;

	public List<Student> findAllStudents() {
		return this.studentMapper.findAllStudents();
	}

	public void saveStudent(Student student) {
		this.studentMapper.saveStudent(student);
	}

	public void editStudent(Student student) {
		this.studentMapper.editStudent(student);
	}

	public void removeStudent(Long uId) {
		this.studentMapper.removeStudent(uId);
	}

	public Student findStudentById(Long uId) {
		return this.studentMapper.findStudentById(uId);
	}
}
