package com.wx.spring.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.wx.spring.contants.Constant;
import com.wx.spring.oauth2.handler.AuthenticationFailHandler;

/**
 * 认证服务器
 * @Description:
 * @Title: AuthorizationConfig
 * @author wangxin
 * @date 2019年10月12日
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig  extends AuthorizationServerConfigurerAdapter{

	// 密码模式
	public static final String GRANT_TYPE_PASSWORD = "password";
	
	//
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String REFRESH_TOKEN = "refresh_token";
    
    //简化模式
    public static final String IMPLICIT = "implicit";
    public static final String SCOPE = "all";
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 3 * 60 * 60;
    public static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 4 * 60 * 60;
	 
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCrypt;
    
	@Autowired
	public UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationFailHandler failHandler;
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 密码模式
		clients.inMemory()
               .withClient(Constant.CLIENT_ID)
               .secret(bCrypt.encode(Constant.SECRET))
               .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
               .scopes(SCOPE)
               // token 有效时间
               .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
               // token 刷新
               .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
      
        /**
         * 自定义登录异常
         */
        endpoints.exceptionTranslator(failHandler);
	}
}
