package com.study.mysite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.study.mysite.Answer.Answer;
import com.study.mysite.Answer.AnswerRepository;
import com.study.mysite.Question.Question;
import com.study.mysite.Question.QuestionRepository;
import com.study.mysite.Question.QuestionService;
import com.study.mysite.User.SiteUser;

import jakarta.transaction.Transactional;

@SpringBootTest
class Mysite17thApplicationTests {

	@Autowired 
	private QuestionRepository questionRepository;
	//autowired로 자동 의존주입
	//테스트 코드에서는 생성자를 통한 객체 주입방식을 지원하지 않아서 autoWired를 사용했지만 순환참조 문제이슈가 발생할 수 있기 때문에 테스트가 아닌 실제 코드에서는 setter나 생성자 주입방식을 사용
	@Autowired
	private QuestionService questionService;
	
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Transactional
	@Rollback(false)
	@Test
	void testJpa() {
		
		//id는 자동생성되므로 생략
		
		Question q1 = new Question();
		q1.setSubject("test2");
		q1.setContent("test456");
		q1.setCreateDate(LocalDateTime.now());
		
		this.questionRepository.save(q1);//첫번째 질문 저장
	

		/*
		Question q2 = new Question();
		q2.setSubject("tes2");
		q2.setContent("2tes");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);//두번째 질문 저장	

		List<Question> all = this.questionRepository.findAll();
		assertEquals(3, all.size()); //all이 가지고 있는 question이 3개이다 라고 가정을 하는것(true,false반환?)
		
		Question q = all.get(0);//모든 테이블을 가져와 Q에 담음 
		assertEquals(2, q.getSubject());
		
		
		Question q = this.questionRepository.findBySubject("tes2");//tes2라는 이름의 subject가
		assertEquals(3, q.getId()); //3번째임을 선언(assert)함

		
		
		Question q = this.questionRepository.findBySubjectAndContent("tes2","2tes");
		assertEquals(3, q.getId());
		
	
		List<Question> qList = this.questionRepository.findBySubjectLike("%te%");
				this.questionRepository.findAll();	
		
		  assertEquals(7, this.questionRepository.count());
		  Optional<Question> oq = this.questionRepository.findById(3);
		  assertTrue(oq.isPresent());
		  Question q = oq.get();
		  this.questionRepository.delete(q);
		  assertEquals(6, this.questionRepository.count());
		  
				
		 */
	//========================Answer===================

	/*
		///Answer 엔티티에 값을 입력한것 
	Optional<Question> oq = this.questionRepository.findById(3); //id가 3인걸 찾기
		  assertTrue(oq.isPresent());
		  Question q = oq.get();
		  
		  Answer a = new Answer();
		  a.setContent("JPA의 인터페이스의 실제 구현체");
		  
		  a.setQuestion(q); //어떤 질문의 답인지가 나와야 하기 떄문에 setQuestion필수
		  					//setQuesiton은 manytoone으로 되어있음 
		  					//여러개의 테이블을 넣으려면 for문으로 반복해야함 (LIst형식 임)
		  a.setCreateDate(LocalDateTime.now());
		  this.answerRepository.save(a);
		*/
		  
		/*
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		*/
		//테스트 코드를 동과하지 못한 이유는 getAnswerList는 q객체를 조회할 떄가 아니라 getAnswerList()를 호출하는 시점에 가져오기 때문에 
		//데이터를 필요한 시점에 가져오는 방식을 지연방식(LAZY)라 한다 <=>이와 반대로 q객체를 조회할떄 미리 answer 리스트를 모두 가져오는 방식을 즉시(Eager)방식이라 한다
		//@Transitioanl을 사용해야한다
		//assertEquals(1, answerList.size());
		//assertEquals("JPA의 인터페이스의 실제 구현체",answerList.get(0).getContente());
		
		
		
		  
	
		  
		  
//	Question2 q2 = new Question2();	
//	q2.setSubject("num1");
//	q2.setSubject("num2");
		for(int i=1; i<=100; i++) {
			String subject= String.format("테스트 코드를 이용하여 생성한 제목:[%03d]", i);
			String content = String.format("테스트 코드를 이용하여 생성한 질문내용:[%03d]", i);
			this.questionService.create(subject, content, null);
		}
	}		
}
