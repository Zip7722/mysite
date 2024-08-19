package com.study.mysite.Answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.mysite.DatanotfonudException;
import com.study.mysite.Question.Question;
import com.study.mysite.User.SiteUser;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AnswerServices {
	private final AnswerRepository answerRepository; //의존주입
	
	public Answer create(Question question, String content, SiteUser author) {
		Answer answer =new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer); //answer을 answerrepository에 저장
		
		return answer;
	}
	
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer =this.answerRepository.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		}else {
			throw new DatanotfonudException("답변이 없습니다");
		}
	}
	
	public void modify (Answer answer , String content ) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}
	
	//답변 삭제
		public void delete(Answer answer) {
			this.answerRepository.delete(answer);
		}
}
