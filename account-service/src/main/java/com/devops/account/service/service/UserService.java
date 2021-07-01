package com.devops.account.service.service;

import java.util.List;

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

	public List<User> findByIsPublic() {
		return userRepository.findByIsPublicTrue();
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}
	
	public Boolean follow(String username, String usernameToFollow) {
		User user = userRepository
				.findByUsername(username).get();
		User userToFollow = this.findByUsername(usernameToFollow);
		
		if (userToFollow.getIsPublic() == false) {
			userToFollow.getFollowRequests().add(user);
			userRepository.save(userToFollow);
			userRepository.save(user);
			return false;
		}
		
		user.getFollowing().add(userToFollow);
		userToFollow.getFollowers().add(user);
		
		userRepository.save(userToFollow);
		userRepository.save(user);
		return true;
	}
	
	public void accept(String username, String usernameToAccept){
		User user = userRepository.findByUsername(username).get();
		
		User userToAccept = userRepository.findByUsername(usernameToAccept).get();
		
		userToAccept.getFollowing().add(user);
		
		for (int i = 0; i < user.getFollowRequests().size(); i++) {
			if (user.getFollowRequests().get(i).getUsername().equals(userToAccept.getUsername())) {
				user.getFollowRequests().remove(i);
				break;
			}
		}
		user.getFollowers().add(userToAccept);
		userRepository.save(userToAccept);
		userRepository.save(user);
	}
	/*public ResponseVO getUserWithPost(Integer userId) {
		ResponseVO vo = new ResponseVO();
		User user = userRepository.findById(userId).get();
		Post post =
				restTemplate.getForObject(
						"http://POST-SERVICE/post/" + user.getPostId()
						, Post.class);
		
		vo.setUser(user);
		vo.setPost(post);
		return vo;
	}*/

	public ResponseVO getPostsFromUser(String username) {
		ResponseVO vo = new ResponseVO();
		User user = userRepository.findByUsername(username).get();
		Post[] posts =
				restTemplate.getForObject(
				"http://POST-SERVICE/post/" + username
				, Post[].class);
		
		vo.setUser(user);
		vo.setPosts(posts);
		return vo;
	}
}
