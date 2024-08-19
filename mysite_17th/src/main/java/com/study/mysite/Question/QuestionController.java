package com.study.mysite.Question;


import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.study.mysite.Answer.AnswerForm;
import com.study.mysite.User.SiteUser;
import com.study.mysite.User.UserServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Return;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	//private final QuestionRepository questionRepository; //@RequiredArgsConstructor를 사용하여 
															//생성자 함수가 호출되었을 때 매개변수로 이 상수를 지정(autowired 사용 지양)
	private final QuestionService questionService;
	private final UserServices userServices;
	
	//게시판 페이지로 이동
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page",defaultValue = "0") int page) {//Model model이 매개변수로 들어감(프레임워크/ui import) model객체는 자동으로 생성됨
									//이때 responseBody를 빼 주어야 해당 html이 출력된다
		//List<Question> questionList = this.questionRepository.findAll();
		//List<Question> questionList = this.questionService.getList();
		
		Page<Question> paging =this.questionService.getList(page);
		model.addAttribute("paging",paging);
		
		return "question_list";
		
		//자바 코드를 삽입할 수 있는 html코드인 템플릿이 필요 => Tymeleaf 사용
		//해당 파일의 이름만 설정하면 알아서 실행해줌
}
	//상세 페이지로 이동
	@GetMapping(value = "/detail/{id}") //id의 값을 가진 링크를 이동할 때 해당 id의 데이터를 가져오는것
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {//Integer id컬럼값과 연결하여 @pathvariable("변수명")으로 변경한다(사용자 url의 변수명 사용가능)
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
	return "question_detail";	
	}
	
	
	//질문 등록 페이지로 이동
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionform) {
		return "question_form";
	}
	
	/*
	@PostMapping("/create")
	public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content ) {
		//todo: 질문 저장 (subject인 값을 Strng subject에 담음
		this.questionService.create(subject, content);
		return "redirect:/question/list";
		
		유효성 검증(validation)적용을 위해 재 작성함
	}*/
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult ,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = this.userServices.getUser(principal.getName());
		this.questionService.create(questionForm.getSubject(),questionForm.getContent(), siteUser);
		return "redirect:/question/list";
			}
	
	//질문을 수정하는 메서드
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm,BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal) {
		
	if(bindingResult.hasErrors()) {
		return "question_form";
	}

	Question question = this.questionService.getQuestion(id);
	if(!question.getAuthor().getUsername().equals(principal.getName())) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다");
	}
	
	
	questionForm.setSubject(question.getSubject());
	questionForm.setContent(question.getContent());
	
	this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
	
	return String.format("redirect:/question/detail/%s",id);
	}
	
	//질문삭제 요청 메서드
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다");
		}
		this.questionService.delete(question);
		
		return "redirect:/";
		
	}
	}
