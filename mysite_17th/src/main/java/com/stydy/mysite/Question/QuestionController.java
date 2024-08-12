package com.stydy.mysite.Question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
//@RequestMapping("/question") //모든 url에 /list를 선언함으로서 list를 생략할 수 있음(선택사항)
@RequiredArgsConstructor
@Controller
public class QuestionController {

	//private final QuestionRepository questionRepository; //@RequiredArgsConstructor를 사용하여 
															//생성자 함수가 호출되었을 때 매개변수로 이 상수를 지정(autowired 사용 지양)
	private final QuestionService questionService;
	@GetMapping("/question/list")
	
	public String list(Model model) {//Model model이 매개변수로 들어감(프레임워크/ui import) model객체는 자동으로 생성됨
									//이때 responseBody를 빼 주어야 해당 html이 출력된다
		//List<Question> questionList = this.questionRepository.findAll();
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList",questionList);
		
		return "question_list";
		
		//자바 코드를 삽입할 수 있는 html코드인 템플릿이 필요 => Tymeleaf 사용
		//해당 파일의 이름만 설정하면 알아서 실행해줌
	}
		
	@GetMapping(value = "/question/detail/{id}") //id의 값을 가진 링크를 이동할 때 해당 id의 데이터를 가져오는것
	public String detail(Model model, @PathVariable("id") Integer id) {//Integer id컬럼값과 연결하여 @pathvariable("변수명")으로 변경한다(사용자 url의 변수명 사용가능)
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
	return "question_detail";	
	}
	

}
