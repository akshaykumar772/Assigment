/**
 * 
 */
package com.assignment.SchoolApplication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignment.SchoolApplication.entity.Role;

/**
 * @author Akshay
 *
 */
public interface RoleDAO extends MongoRepository<Role, String> {

}
