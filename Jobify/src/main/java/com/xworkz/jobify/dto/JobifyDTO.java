package com.xworkz.jobify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobifyDTO {
	
	private String name;
	private String email;
	private String mobile;
	private String password;
	private String confirmPass;
	private String account;

}
