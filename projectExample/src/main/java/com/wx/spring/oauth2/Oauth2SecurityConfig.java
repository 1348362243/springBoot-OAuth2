package com.wx.spring.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 安全配置
 * @Description:
 * @Title: Oauth2SecurityConfig
 * @author wangxin
 * @date 2019年10月12日
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Oauth2SecurityConfig extends WebSecurityConfigurerAdapter{

	 @Autowired
	 public UserDetailsService userDetailsService;
	 
	 @Autowired
	 private RedisConnectionFactory connectionFactory;
	  
	 @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
     }
	 
	 @Override
     protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
     }
	 
	 @Override
	 @Bean
	 protected AuthenticationManager authenticationManager() throws Exception {
	      return super.authenticationManager();
	 }
	 
	 @Bean
     public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
     } 
	 
	 @Bean
	 public TokenStore tokenStore(){
	     return new RedisTokenStore(connectionFactory);
	 }
}
