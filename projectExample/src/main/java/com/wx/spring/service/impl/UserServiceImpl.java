package com.wx.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wx.spring.exception.CustomException;
import com.wx.spring.mapper.UserMapper;
import com.wx.spring.service.UserService;

/**
 * 
 * @Description:
 * @Title: UserServiceImpl
 * @author wangxin
 * @date 2019年10月12日
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map<String, Object> param = new HashMap<>();
		param.put("USER_ID", username);
		List<com.wx.spring.model.User> list = userMapper.selectByMap(param);
	    if(list != null && list.size()>0){
	    	if(list.size() != 1){
	    		throw new CustomException("账号异常");
	    	}else{
	    		com.wx.spring.model.User user  = list.get(0);
	    		// 权限
	    		List<GrantedAuthority> author = AuthorityUtils.createAuthorityList("admin");
	    		return new User(user.getUserId(), user.getPassword(), author);
	    	}
	    }else{
	    	throw new CustomException("账号不存在");
	    }
	}
}
