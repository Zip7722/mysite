package com.study.mysite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason="entitiy not founddddd")
public class DatanotfonudException extends RuntimeException {
	private static final long serialVersitonUID=1L;
	public DatanotfonudException(String message) {
		super(message);
	}
}
