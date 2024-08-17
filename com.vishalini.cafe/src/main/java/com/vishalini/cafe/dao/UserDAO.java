package com.vishalini.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vishalini.cafe.POJO.User;


public interface UserDAO extends JpaRepository<User,Integer>{
	
	// since this is an interface implementation of queries will be in POJO class using 
	// @NamedQuery annotation
	
	User findByEmailId(@Param("email") String email);

}
