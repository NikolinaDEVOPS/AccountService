package com.devops.account.service.VO;

import com.devops.account.service.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {
	private User user;
	private Post[] posts;
}
