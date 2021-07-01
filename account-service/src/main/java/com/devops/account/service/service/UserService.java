package com.devops.account.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devops.account.service.VO.Post;
import com.devops.account.service.VO.ResponseVO;
import com.devops.account.service.model.User;
import com.devops.account.service.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	public User save(User user) {
		return userRepository.save(user);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	public ResponseVO getUserWithPost(Integer userId) {
		ResponseVO vo = new ResponseVO();
		User user = userRepository.findById(userId).get();
		Post post =
				restTemplate.getForObject(
						"http://POST-SERVICE/post/" + user.getPostId()
						, Post.class);
		
		vo.setUser(user);
		vo.setPost(post);
		return vo;
	}
}
