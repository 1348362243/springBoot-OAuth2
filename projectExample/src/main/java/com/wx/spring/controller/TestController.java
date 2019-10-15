package com.wx.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wx.spring.service.TestService;


@RestController
@RequestMapping(value = "/wx")
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping(value="login" , method = RequestMethod.GET)
	public Object login() {
		return testService.login();
	}
}
