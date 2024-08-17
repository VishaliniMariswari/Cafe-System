package com.vishalini.cafe.ServiceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vishalini.cafe.POJO.User;
import com.vishalini.cafe.Service.UserService;
import com.vishalini.cafe.constants.CafeConstants;
import com.vishalini.cafe.dao.UserDAO;
import com.vishalini.cafe.utils.CafeUtils;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

// using sl4j for logging purpose
@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDao;

	@Override
	public ResponseEntity<String> signup(Map<String, String> requestBody) {
		
//		 first we need to validate the request Body whether we have reqd details such as 
//		contact number email & so on
//		 if it doen't contains mandatory details then we consider the request as invalid
//		so create a method to validate it 
		log.info("Inside signup :"+requestBody);
		
		try {
		if(validateRequest(requestBody)) {
			// check if email already exists
			User user = userDao.findByEmailId(requestBody.get("email"));
			if(Objects.isNull(user)) {
				// now user doesn't exist hence creating user data from requestBody 
				// create method to generate user and then save
				userDao.save(generatingUserfromRequestBody(requestBody));
				return CafeUtils.getErrorMessage(CafeConstants.USER_CREATED_SUCCESSFULLY , HttpStatus.OK);
			}
			else {
				return CafeUtils.getErrorMessage(CafeConstants.USER_ALREADY_EXISTS , HttpStatus.BAD_REQUEST);
			}
			
		} } catch(Exception exc) {
			exc.printStackTrace();
		}
		
		return CafeUtils.getErrorMessage(CafeConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST);
		
	}
	
	private boolean validateRequest(Map<String, String> requestBody) {
		if (requestBody.containsKey("name") && requestBody.containsKey("email") &&
				requestBody.containsKey("contactNumber") && requestBody.containsKey("password") ) {
			return true;
		}
		
		return false;
	}
	
	private User generatingUserfromRequestBody (Map<String, String> requestBody) {
		
		User user=new User();
				
		user.setName(requestBody.get("name"));
		user.setContactNumber(requestBody.get("contactNumber"));
		user.setPassword(requestBody.get("password"));
		user.setEmail(requestBody.get("email"));
		user.setRole("user");
		user.setStatus("false");
		
		return user;
	}

}
