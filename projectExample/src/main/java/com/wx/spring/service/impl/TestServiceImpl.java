package com.wx.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wx.spring.base.UserInfo;
import com.wx.spring.mapper.UserMapper;
import com.wx.spring.service.TestService;
import com.wx.spring.utils.RedisUtils;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Override
	public Object login() {
//		return userService.loadUserByUsername("admin");
		return new UserInfo();
	}
	
	public static void main(String[] args) {
		try {

	        String encoded = new BCryptPasswordEncoder().encode("123");
			System.out.println(encoded);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
