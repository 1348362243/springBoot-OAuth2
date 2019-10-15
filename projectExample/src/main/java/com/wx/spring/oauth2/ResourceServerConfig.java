package com.wx.spring.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.wx.spring.oauth2.handler.AuthenticationFailHandler;

/**
 * 资源服务器
 * @Description:
 * @Title: ResourceServerConfig
 * @author wangxin
 * @date 2019年10月12日
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private AuthenticationFailHandler failHandler;
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
         //放行接口认证
//         .antMatchers("/wx/login").permitAll()
         // 需要认证权限
//         .antMatchers("/wx/login").hasAuthority("adminasd")
         //接口没有角色限制，但需要经过认证，只要携带token就可以放行
         .anyRequest()
         .authenticated()
         //关闭跨站请求防护
         .and()
         .csrf().disable();
    }
	
	/**
	 * 自定义token异常信息
	 */
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(failHandler).accessDeniedHandler(failHandler);
    }
}
