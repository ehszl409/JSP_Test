package com.cos.test.service;

import java.util.List;

import com.cos.test.domain.user.User;
import com.cos.test.domain.user.UserDao;
import com.cos.test.domain.user.dto.JoinReqDto;
import com.cos.test.domain.user.dto.LoginReqDto;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}

	public int 회원가입(JoinReqDto dto) {
		return userDao.join(dto);
	}
	
	public User 로그인(LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}
	
	public List<User> 유저목록보기(int page){
		return userDao.list(page);
	}
	
	public int 유저삭제(int userId) {
		return userDao.delete(userId);
	}
	
	public int 유저네임중복체크(String username) {
		return userDao.findByUsername(username);
	}
	
	public int 유저수() {
		return userDao.count();
	}
}
