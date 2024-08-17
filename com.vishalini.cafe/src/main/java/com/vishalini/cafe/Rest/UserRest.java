package com.vishalini.cafe.Rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/user")
public interface UserRest {
	
	// giving it as PostMapping bcos we are getting request body from user 
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody(required=true) Map<String,String> requestBody);

}
