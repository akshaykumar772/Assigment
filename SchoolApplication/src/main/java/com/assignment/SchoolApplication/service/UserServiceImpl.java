/**
 * 
 */
package com.assignment.SchoolApplication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.SchoolApplication.dao.StudentDAO;
import com.assignment.SchoolApplication.entity.Student;

/**
 * @author Akshay
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private StudentDAO studentDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentDao.findByUsername(username);
		if(student != null) {
			return new User(student.getUsername(), student.getPassword(), new ArrayList<>());	
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
