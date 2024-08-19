package com.vishalini.cafe.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.vishalini.cafe.dao.UserDAO;

@Service
public class CustomerUsersDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	// here we are giving as full package bcos there is another User class in spring security userdetials package
	private com.vishalini.cafe.POJO.User userdetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// here username is the email so we can we FindByemilId method
		userdetails= userDAO.findByEmailId(username);
		if(!Objects.isNull(userdetails)) {
			return new User(userdetails.getEmail(),userdetails.getPassword(),new ArrayList<>());
		} else 
			throw new UsernameNotFoundException("User Not Found");
		
	}
	
	public com.vishalini.cafe.POJO.User getUserDetails() {
		return userdetails;
				}
	

}
