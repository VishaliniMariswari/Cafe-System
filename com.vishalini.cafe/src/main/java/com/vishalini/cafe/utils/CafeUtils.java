package com.vishalini.cafe.utils;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {
	
	// creating this static bcos we need not create object of this class then access the method instead we can 
	// directly access by className.methodName
	public static ResponseEntity<String> getErrorMessage (String responseMessage, HttpStatus httpStatus ) {
		return new ResponseEntity<String> (responseMessage,httpStatus);
		
	}

}
