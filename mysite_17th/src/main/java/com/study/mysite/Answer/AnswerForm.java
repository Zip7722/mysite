package com.study.mysite.Answer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	@NotEmpty(message="답변내용 입력")
	@Size(max=200)
	private String content;

	
	
}
