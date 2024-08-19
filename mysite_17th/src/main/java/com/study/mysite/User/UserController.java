package com.study.mysite.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserServices userservice;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {//에러 발생시
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword().equals(userCreateForm.getPassword2())){ //비밀번호와 비밀번호 확인이 다를경우
			bindingResult.rejectValue("password2", "passwordInCorrect","패스워드가 일치하지 않음");
			//rejectValue("필드명","오류 코드","출력 문구");
			return "signup_form";	
		}
		
		try {
			userservice.create(userCreateForm.getUsername(),userCreateForm.getPassword(),userCreateForm.getEmail());

		} catch (DataIntegrityViolationException e) {
		System.out.println(e);
		//DataIntegrityViolationException: unique로 설정한 값에 같은 데이터가 들어갈 때 발생하는 예외 클래스
		bindingResult.reject("sinupFaild","이미 등록된 사용자 입니다");
		//reject로(오류 코드, 오류 메시지)
		return "signup_form";
		}
		//아이디 중복확인을 할때 주로 사용됨
		
		return "redirect:/";
		
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	//post형식으로 받은 값을 postMapping으로 처리해왔지만 스프링 시큐리티를 사용하면 해당 부분을 스프링 시큐리티가 처리하므로 우리가 구현할 필요가 없다
	

	
}
