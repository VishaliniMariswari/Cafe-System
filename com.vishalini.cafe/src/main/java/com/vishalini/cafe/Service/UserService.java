package com.vishalini.cafe.Service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface UserService {
	
	public ResponseEntity<String> signup(Map<String, String> requestBody);

}
