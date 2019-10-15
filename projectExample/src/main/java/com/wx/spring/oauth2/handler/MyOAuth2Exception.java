package com.wx.spring.oauth2.handler;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ExceptionJacksonSerializer.class)
public class MyOAuth2Exception extends OAuth2Exception{

	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;

	public MyOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public MyOAuth2Exception(String msg) {
        super(msg);
    }
}
