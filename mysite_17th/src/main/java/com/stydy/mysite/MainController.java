package com.stydy.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	@GetMapping("/study")//8080주소 뒤 / 에 붙는 텍스트 (stydy가 붙으면 아래 index의 코드가 출력됨)
	@ResponseBody //웹페이지에 출력하는 용도
	public String index() {
		System.out.println("index"); //system 에 index를 찍는 코드만 있기 때문에 
	return "test1e2";
		
	}
	
	@GetMapping("/") //아무것도 입력 안했을경우
	public String root() { //root()를 사용하여 해당 페이지로 보낸다 
		return "index";
	}
	
	
	//localhost8080/question/list 에  게시판 목록 페이지 출력
}
