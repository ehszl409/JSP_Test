package com.cos.test.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// model
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String createDate;
}
