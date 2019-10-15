package com.wx.spring.oauth2.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.wx.spring.base.ReturnData;
import com.wx.spring.base.StatusEnum;

/**
 * 自定义失败处理器
 * @Description:
 * @Title: AuthenticationFailHandler
 * @author wangxin
 * @date 2019年10月14日
 */
@Component
public class AuthenticationFailHandler implements AuthenticationEntryPoint, AccessDeniedHandler,WebResponseExceptionTranslator<OAuth2Exception>{

	private  Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * token 失效
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
        	String result = JSON.toJSONString(new ReturnData<>(StatusEnum.TOKEN_INVALID.getCode(),StatusEnum.TOKEN_INVALID.getMsg(),authException.getMessage()));
            response.getWriter().write(result);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            String result = JSON.toJSONString(new ReturnData<>(StatusEnum.CUSTOM_INFO.getCode(),""+e.getMessage(),""+e.getLocalizedMessage()));
            response.getWriter().write(result);
        }
		
	}

	/**
	 * 权限不足
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,AccessDeniedException accessDeniedException) throws IOException, ServletException {
		 response.setStatus(HttpStatus.OK.value());
	     response.setHeader("Content-Type", "application/json;charset=UTF-8");
         try {
        	String result = JSON.toJSONString(new ReturnData<>(StatusEnum.NO_HAVE_ROLE.getCode(),StatusEnum.NO_HAVE_ROLE.getMsg(),accessDeniedException.getMessage()));
            response.getWriter().write(result);
         } catch (Exception e) {
        	 logger.error(e.getMessage());
             String result = JSON.toJSONString(new ReturnData<>(StatusEnum.CUSTOM_INFO.getCode(),""+e.getMessage(),""+e.getLocalizedMessage()));
             response.getWriter().write(result);
         }
	}

	/**
	 * 登录异常
	 */
	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		 MyOAuth2Exception exception = new MyOAuth2Exception(e.getMessage(),e);
		 ResponseEntity<OAuth2Exception> response = new ResponseEntity<OAuth2Exception>(exception, HttpStatus.OK);
	     return response;
	}
}
