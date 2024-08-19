package com.study.mysite.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.mysite.User.SiteUser;

import lombok.RequiredArgsConstructor;


	@RequiredArgsConstructor
	@Service
	public class QuestionService {
		private final QuestionRepository questionRepository;
		
/*		
 		public List<Question> getList(){
			return this.questionRepository.findAll();	
		}
*/		
		public Question getQuestion(Integer id) {
			Optional<Question> question= this.questionRepository.findById(id);
			if(question.isPresent()) {
				return question.get();
						
			}else {
				throw new ConfigDataLocationNotFoundException(null, "question not found", null);
				}
			
		}
		
		public void create(String subject, String content, SiteUser user) {
			Question q = new Question();
			q.setSubject(subject);
			q.setContent(content);
			q.setCreateDate(LocalDateTime.now());
			q.setAuthor(user);
			this.questionRepository.save(q);
		}
		
		public Page<Question> getList(int page){
			List<Sort.Order> sorts = new ArrayList<>();
			sorts.add(Sort.Order.desc("createDate"));
	        Sort sort = Sort.by(sorts);
			Pageable pagealbe = PageRequest.of(page, 5,sort);
			return this.questionRepository.findAll(pagealbe);
			
			}
		public void modify(Question question, String subject,String contnet) {
			question.setSubject(subject);
			question.setModifyDate(LocalDateTime.now());
			this.questionRepository.save(question);
		}

		public void delete(Question question) {
		this.questionRepository.delete(question);
			
		}
	}

