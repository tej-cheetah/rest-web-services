package com.example.resfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoPostsExists extends RuntimeException{

	public NoPostsExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
