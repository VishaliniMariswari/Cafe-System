package com.vishalini.cafe.RestImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vishalini.cafe.Rest.UserRest;
import com.vishalini.cafe.Service.UserService;
import com.vishalini.cafe.constants.CafeConstants;
import com.vishalini.cafe.utils.CafeUtils;

@RestController
public class UserRestImpl implements UserRest {
	
	@Autowired
	UserService userservice;

	@Override
	public ResponseEntity<String> signup(Map<String, String> requestBody) {
		// using try catch bloc as good practice 
		try {
			
			return userservice.signup(requestBody);
			
		} catch(Exception exc) {
			exc.printStackTrace();		
			}
		
		return CafeUtils.getErrorMessage(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
