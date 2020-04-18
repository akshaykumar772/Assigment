/**
 * 
 */
package com.assignment.SchoolApplication.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.SchoolApplication.config.JwtTokenUtil;
import com.assignment.SchoolApplication.entity.Student;
import com.assignment.SchoolApplication.entity.UserRequest;
import com.assignment.SchoolApplication.entity.UserResponse;
import com.assignment.SchoolApplication.service.StudentService;
import com.assignment.SchoolApplication.service.UserServiceImpl;

/**
 * @author Akshay
 *
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/studentController")
public class StudentRestController {
	
	@Autowired
	private StudentService service;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping("/getStudentList")
	public List<Student> getStudentsList() {
		List<Student> students = service.getStudentList();
		return students;
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student addStudent(@RequestBody Student studentDetails) {
		Student student = service.addStudent(studentDetails);
		return student;
	}
	
	@RequestMapping(value = "/updateStudentDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student updateStudentDetails(@RequestBody Student studentDetails) {
		Student student = service.updateStudentDetails(studentDetails);
		return student;
	}
	
	@RequestMapping(value = "/getStudentDetails", method = RequestMethod.GET)
	public Student getStudentDetails(@RequestParam("rollno") Long rollno) {
		Student student = service.getStudentDetails(rollno);
		return student;
	}
	
	@RequestMapping(value = "/deleteStudent", method = RequestMethod.DELETE)
	public void deleteStudent(@RequestParam("id") String id) {
		service.deleteStudent(id);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody UserRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userServiceImpl
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new UserResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
