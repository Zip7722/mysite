package com.study.mysite.Question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

	@NotEmpty(message="제목은 필수사항 입니다")
	@Size(max=100)
	private String subject;//form 템플릿 파일의 input의 name속성값 subject와 바인딩된다
	
	
	@NotEmpty(message="내용을 입력해주세요")
	@Size(max=1000)
	private String content;//form 템플릿 파일의 textarea의 name속성값 content와 바인딩된다
	
}
