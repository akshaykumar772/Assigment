/**
 * 
 */
package com.assignment.SchoolApplication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignment.SchoolApplication.entity.Student;

/**
 * @author Akshay
 *
 */
public interface StudentDAO extends MongoRepository<Student, String> {

	Student findByRollno(Long rollno);

	Student findByUsername(String username);

	Student findByRollnoOrUsername(long rollno, String username);

}
