package com.devops.account.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public User savePost(@RequestBody User post) {
		log.info("savePost method of PostController");
		return userService.save(post);
	}
	
	@GetMapping("/{id}")
	public User findOne(@PathVariable Integer id) {
	    return userService.findById(id);
	}
	
	@GetMapping("/{id}/post")
	public ResponseVO getUserWithost(@PathVariable Integer id) {
		return userService.getUserWithPost(id);
	}
}
