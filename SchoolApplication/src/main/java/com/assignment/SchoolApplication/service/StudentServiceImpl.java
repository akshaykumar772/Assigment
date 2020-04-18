/**
 * 
 */
package com.assignment.SchoolApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.SchoolApplication.dao.StudentDAO;
import com.assignment.SchoolApplication.entity.Student;

/**
 * @author Akshay
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDAO studentDao;

	@Override
	public List<Student> getStudentList() {
		List<Student> students = studentDao.findAll();
		return students;
	}

	@Override
	public Student addStudent(Student studentDetails) {
		Student student = new Student();
		Student s = studentDao.findByRollnoOrUsername(studentDetails.getRollno(), studentDetails.getUsername());
		if(s == null) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(studentDetails.getPassword());
			studentDetails.setPassword(hashedPassword);
			student = studentDao.insert(studentDetails);
		} else if(studentDetails.getRollno() == s.getRollno()) {
			student.setErrorMsg("rollnumber is already used");
		} else {
			student.setErrorMsg("username is already used");
		}
		return student;
	}

	@Override
	public Student getStudentDetails(Long rollno) {
		Student student = studentDao.findByRollno(rollno);
		return student;
	}

	@Override
	public void deleteStudent(String id) {
		studentDao.deleteById(id);
	}

	@Override
	public Student updateStudentDetails(Student studentDetails) {
		Student s = studentDao.save(studentDetails);
		return s;
	}
}
