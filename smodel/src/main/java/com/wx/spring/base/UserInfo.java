package com.wx.spring.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 当前用户信息
 * @Description:
 * @Title: UserInfo
 * @author wangxin
 * @date 2019年10月14日
 */
public class UserInfo {
 
	private String userName;
	
	
	public UserInfo(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User principal =  (org.springframework.security.core.userdetails.User) auth.getPrincipal();
		this.userName = principal.getUsername();
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
