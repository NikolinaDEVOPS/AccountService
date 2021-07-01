package com.devops.account.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devops.account.service.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	public String username;

	public String name;
	public String surname;
	public String phoneNumber;
	public String sex;
	public String birthDate;
	public String website;
	public String biography;
	public Boolean isPublic;
	
	@OneToMany
	public List<User> following;
	@OneToMany
	public List<User> followers;
	@OneToMany
	public List<User> followRequests;
	
	public User(UserDTO userDTO) {
		this.username = userDTO.getUsername();
		this.name = userDTO.getName();
		this.phoneNumber = userDTO.getPhoneNumber();
		this.sex = userDTO.getSex();
		this.birthDate = userDTO.getBirthDate();
		this.website = userDTO.getWebsite();
		this.biography = userDTO.getBiography();
		this.isPublic = userDTO.getIsPublic();
		this.following = new ArrayList<User>();
		this.followRequests = new ArrayList<User>();
		followers = new ArrayList<User>();
	}
	
	public void setUserData(UserDTO userDTO) {
		this.username = userDTO.getUsername();
		this.name = userDTO.getName();
		this.phoneNumber = userDTO.getPhoneNumber();
		this.sex = userDTO.getSex();
		this.birthDate = userDTO.getBirthDate();
		this.website = userDTO.getWebsite();
		this.biography = userDTO.getBiography();
		this.isPublic = userDTO.getIsPublic();
	}
}
