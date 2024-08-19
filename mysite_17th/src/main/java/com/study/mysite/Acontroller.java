package com.study.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Acontroller {
		@GetMapping("/a")
		@ResponseBody
		public String helloSpring() {
			
			return "reload를 사용하여 자동으로 서버가 refresh됨";
		}
	
}
//@Controller 어노테이션으로 해당 코드가 컨트롤러 역할을 함(정보 전달(model))
//@getmapping("/a")로 주소창에 enter를 입력시 /a가 나온다 따라서 주소창에 엔터를 입력 할 시
//@responsebody로 helloworld로 출력받는 다는 의미