package com.study.mysite.Question;


import java.time.LocalDateTime;
import java.util.List;

import com.study.mysite.Answer.Answer;
import com.study.mysite.User.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100) //글자의 갯수를 100개(영문, 한글 동일)
	private String subject;

	@Column(columnDefinition = "TEXT") //글자의 갯수를 무한대
	private String content;	

	private LocalDateTime createDate;  //db에서는 create_date

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //질문이 삭제되면 관련 답변도 모두 삭제하겠다.
	private List<Answer> answerList; //하나의 question 테이블이 여러 자식(answer)테이블에 속하므로 One(Q) to many(A)

	@ManyToOne
	private SiteUser author;
	//사용자 한명이 질문을 여러개 작성할 수 있으므로 다대일 관계가 성립
	
	
	private LocalDateTime modifyDate;
	}
