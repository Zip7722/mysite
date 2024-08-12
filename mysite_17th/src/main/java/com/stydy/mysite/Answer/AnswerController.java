package com.stydy.mysite.Answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stydy.mysite.Question.Question;
import com.stydy.mysite.Question.QuestionRepository;
import com.stydy.mysite.Question.QuestionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerServices answerServices;
	
	@PostMapping("/create/{questionId}")//form의 양식이 post기 떄문에 값을 받으려면 postMapping을 해야함
	public String createAnswer(Model model, @PathVariable("questionId") Integer id, @RequestParam(value="content") String content ) {
				Question question = this.questionService.getQuestion(id);
				//TODO:답변 저장 코드 하단에 작성
				this.answerServices.create(question,content);
				return String.format("redirect:/question/detail/%s",id); //링크의 주소를 question/detail/"id의값"
	}
	
	
}
