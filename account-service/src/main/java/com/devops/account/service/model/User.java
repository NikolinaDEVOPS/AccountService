package com.devops.account.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
