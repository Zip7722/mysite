package com.study.mysite.User;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"), //상수를 열거형으로 작성시(enum)대문자로 작성 
	USER("ROLE_USER");
	
	UserRole(String value){
		this.value = value;
		
	}
	
	private String value;
}
