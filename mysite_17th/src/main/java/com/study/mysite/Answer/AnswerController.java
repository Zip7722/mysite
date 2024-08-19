 package com.study.mysite.Answer;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.mysite.Question.Question;
import com.study.mysite.Question.QuestionService;
import com.study.mysite.User.SiteUser;
import com.study.mysite.User.UserServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;



@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerServices answerServices;
	private final UserServices userServices;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{questionId}")//form의 양식이 post기 떄문에 값을 받으려면 postMapping을 해야함
	public String createAnswer(Model model, @PathVariable("questionId") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal pricipal) {
				Question question = this.questionService.getQuestion(id);
				//현재 로그인한 사용자의 정보를 알기위해 스프링 시큐리가 제공하는 principal 객체를 사용해야한다
				if(bindingResult.hasErrors()) {
					model.addAttribute("question",question); 
					return "question_detail";
				}
				SiteUser siteUser = this.userServices.getUser(pricipal.getName()); // siteUser에 작성된 getUser를 사용하기 위한 메서드\
																				   //이때 매개변수로는 princaipal의 getname을 매개변수로 하여 유저name을 알아와서 SiteUser 에서 사용한다
				
				this.answerServices.create(question,answerForm.getContent(),siteUser);
				return String.format("redirect:/question/detail/%s",id); //링크의 주소를 question/detail/"id의값"
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm, @PathVariable ("id") Integer id, Principal principal ) {
		Answer answer = this.answerServices.getAnswer(id);
		if(!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한 없음");
		}
		
		answerForm.setContent(answer.getContent());
		return String.format("redirect:/question/detail/%s",answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(@PathVariable("id") Integer id, Principal principal) {
        Answer answer = this.answerServices.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerServices.delete(answer);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }
	
	
}
