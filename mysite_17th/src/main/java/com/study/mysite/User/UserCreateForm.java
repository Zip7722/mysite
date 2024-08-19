package com.study.mysite.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserCreateForm {
	
	@NotEmpty(message = "set user name")
	@Size(min =2, max =15)
	private String username;
	
	@NotEmpty(message= "set user paassword")
	private String password;
	
	@NotEmpty(message= "check your paassword")
	private String password2;
	
	@NotEmpty(message= "set user email")
	@Email  //email양식으로 검증해주는 email어노테이션
	private String email;
	
	
	
	
}
