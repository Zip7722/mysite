package com.study.mysite.Answer;

import java.time.LocalDateTime;

import com.study.mysite.Question.Question;
import com.study.mysite.User.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column (columnDefinition = "TEXT")
	private String content;
	
	
	private LocalDateTime createDate;
	
	
	@ManyToOne
	private Question question;//외래키 : Answer(자식:N)<-Question(부모:1)
							  //여러개의 answer 테이블이 부모요소인 question 테이블에 속하므로 Many(Q) to one(A)
	@ManyToOne
	private SiteUser author;

	private LocalDateTime modifyDate;
}
