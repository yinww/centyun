package com.yinww.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yinww.web.core.security.CaptchaAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private AuthenticationSuccessHandler accountAuthenticationSuccessHandler;
	
    @Autowired
    private AuthenticationFailureHandler accountAuthenticationFailHander;
	
	@Autowired
	private AuthenticationProvider accountAuthenticationProvider;
	
	@Value("${security.ignores}")
	private String ignores;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		    .formLogin()
		        .loginPage("/login").loginProcessingUrl("/login/form")//.failureUrl("/login-error")
//		        .successHandler(accountAuthenticationSuccessHandler)
//		        .failureHandler(accountAuthenticationFailHander)
		    .permitAll()
		.and().headers().frameOptions().sameOrigin()
		.and().authorizeRequests().antMatchers("/encode/**", "/account/getAccountByToken", "/account/updateLanguage", "/captcha-image", "/change-lang", "/login/**").permitAll()
		    .anyRequest().access("@securityService.hasPermission(request, authentication)")  //必须经过认证以后才能访问
		.and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(accountAuthenticationProvider);
	}
	
	public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
		CaptchaAuthenticationFilter authenticationFilter = new CaptchaAuthenticationFilter();
	    authenticationFilter.setAuthenticationManager(authenticationManager());
	    //只有post请求才拦截
	    authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login/form", "POST"));
	    authenticationFilter.setAuthenticationSuccessHandler(accountAuthenticationSuccessHandler);
	    authenticationFilter.setAuthenticationFailureHandler(accountAuthenticationFailHander);
	    return authenticationFilter;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    String[] ignoreArray = ignores.split(",");
	    for (String ignore : ignoreArray) {
	      web.ignoring().antMatchers(ignore);
	    }
	}
}
