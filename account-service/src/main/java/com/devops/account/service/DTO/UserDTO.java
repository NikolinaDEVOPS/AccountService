package com.devops.account.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
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
