package com.stydy.mysite.Answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.stydy.mysite.Question.Question;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AnswerServices {
	private final AnswerRepository answerRepository; //의존주입
	
	public void create(Question question, String content) {
		Answer answer =new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		this.answerRepository.save(answer); //answer을 answerrepository에 저장
		
	}
}
