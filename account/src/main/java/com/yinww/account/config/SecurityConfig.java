package com.yinww.account.config;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.yinww.account.security.CaptchaAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private AuthenticationSuccessHandler accountAuthenticationSuccessHandler;
	
    @Autowired
    private AuthenticationFailureHandler accountAuthenticationFailHander;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
    private AuthenticationFailureHandler captchaAuthenticationFailHander;
	
	@Autowired
    private AuthenticationSuccessHandler captchaAuthenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		        .loginPage("/login").loginProcessingUrl("/login/form")//.failureUrl("/login-error")
		        .successHandler(accountAuthenticationSuccessHandler)
		        .failureHandler(accountAuthenticationFailHander)
		    .permitAll()
		.and().authorizeRequests().antMatchers("/captcha-image", "/changeLang").permitAll()
		    .anyRequest().access("@securityService.hasPermission(request, authentication)")  //必须经过认证以后才能访问
		.and().csrf().disable();
		
		http.addFilterBefore(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
		CaptchaAuthenticationFilter authenticationFilter = new CaptchaAuthenticationFilter();
	    authenticationFilter.setAuthenticationManager(authenticationManager());
	    //只有post请求才拦截
	    authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login/form", "POST"));
	    authenticationFilter.setAuthenticationSuccessHandler(captchaAuthenticationSuccessHandler);
	    authenticationFilter.setAuthenticationFailureHandler(captchaAuthenticationFailHander);
	    return authenticationFilter;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**").antMatchers("/js/**").antMatchers("/lib/**").antMatchers("/images/**");
	}
}
