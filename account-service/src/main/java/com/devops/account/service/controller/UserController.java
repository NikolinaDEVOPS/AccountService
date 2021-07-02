package com.devops.account.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.account.service.DTO.UserDTO;
import com.devops.account.service.VO.Post;
import com.devops.account.service.VO.ResponseVO;
import com.devops.account.service.model.User;
import com.devops.account.service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public User savePost(@RequestBody UserDTO userDTO) {
		log.info("savePost method of PostController");
		User user = new User(userDTO);
		return userService.save(user);
	}
	
	@GetMapping("/{username}")
	public User findOne(@PathVariable String username) {
	    return userService.findByUsername(username);
	}
	
	@GetMapping("/public")
	public List<User> findAllPublic() {
	    return userService.findByIsPublic();
	}
	
	@GetMapping("/{username}/isPublic")
	public Boolean chekIfPublic(@PathVariable String username) {
	    return userService.findByUsername(username).getIsPublic();
	}
	
	@GetMapping("/{username}/followers")
	public List<User> getFollowers(@PathVariable String username) {
	    return userService.findByUsername(username).getFollowers();
	}
	
	@GetMapping("/{username}/following")
	public List<User> getFollowing(@PathVariable String username) {
	    return userService.findByUsername(username).getFollowing();
	}
	
	@GetMapping("/{username}/requests")
	public List<User> getFollowRequests(@PathVariable String username) {
	    return userService.findByUsername(username).getFollowRequests();
	}
	
	@PutMapping("/{username}")
	public User updateProfile(@RequestBody UserDTO newUser, @PathVariable String username) {
		User user = userService.findByUsername(username);
		if(user == null) return null;
		
		user.setUserData(newUser);
		return userService.save(user);
	}
	
	@PutMapping("/{username}/setPublic")
	public User setIsPublic(@PathVariable String username) {
		User user = userService.findByUsername(username);
		
		if(user == null) return null;
		user.setIsPublic(true);
		return userService.save(user);
	}
	
	@PutMapping("/{username}/setPrivate")
	public User setPrivate(@PathVariable String username) {
		User user = userService.findByUsername(username);
		
		if(user == null) return null;
		user.setIsPublic(false);
		return userService.save(user);
	}
	
	@PostMapping("/{username}/follow/{userToFollow}")
	public Boolean follow(@PathVariable String username, @PathVariable String userToFollow) {
		return userService.follow(username, userToFollow);
	}
	
	@PostMapping("/{username}/accept/{userToFollow}")
	public void accept(@PathVariable String username, @PathVariable String userToFollow) {
		userService.accept(username, userToFollow);
	}
	
	@GetMapping("/{username}/posts")
	public ResponseVO getPostsFromUser(@PathVariable String username) {
		return userService.getPostsFromUser(username);
	}
	
	@PostMapping("/{username}/favorites/{postId}")
	public void favorites(@PathVariable String username, @PathVariable Integer postId) {
		userService.favorites(username, postId);
	}
	
	@GetMapping("/{username}/favorites")
	public List<Post> getFavorites(@PathVariable String username) {
		User user = userService.findByUsername(username);
		
		if(user == null) return null;
		return userService.getFavorites(username);
	}
}
