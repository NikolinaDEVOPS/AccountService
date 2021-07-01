package com.devops.account.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public User savePost(@RequestBody User user) {
		log.info("savePost method of PostController");
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
	
	@PutMapping("/{username}")
	public User updateProfile(@RequestBody User newUser, @PathVariable String username) {
		User user = userService.findByUsername(username);
		
		if(user == null) return null;
		
		newUser.setUserId(user.getUserId());
		return userService.save(newUser);
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
	
	/*@GetMapping("/{id}/post")
	public ResponseVO getUserWithost(@PathVariable Integer id) {
		return userService.getUserWithPost(id);
	}*/
}
