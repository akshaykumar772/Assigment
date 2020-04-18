/**
 * 
 */
package com.assignment.SchoolApplication.service;

import java.util.List;

import com.assignment.SchoolApplication.entity.Student;

/**
 * @author Akshay
 *
 */
public interface StudentService {

	List<Student> getStudentList();

	Student addStudent(Student studentDetails);

	Student getStudentDetails(Long rollno);

	void deleteStudent(String id);

	Student updateStudentDetails(Student studentDetails);

}
