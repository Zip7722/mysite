package com.study.mysite.User;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.mysite.DatanotfonudException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserServices {
		private final UserRepository userRepository;
		private final PasswordEncoder passwordEncoder;
		
		public SiteUser create(String username, String password,String email) {
			SiteUser user = new SiteUser();
			user.setUsername(username);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user.setPassword(passwordEncoder.encode(password));
			
			user.setEmail(email);
			//user.setPassword(password);
			this.userRepository.save(user);
			return user;
		}
		
		//로그인한 사용자명을 알 수 있는 메서드
		public SiteUser getUser(String username) {
			Optional<SiteUser> siteUser=
			this.userRepository.findByUsername(username);

			if(siteUser.isPresent()) {
				return siteUser.get();
			}else {
				throw new DatanotfonudException("해당 회원이 없습니다");
			}
			
			
		}

}
