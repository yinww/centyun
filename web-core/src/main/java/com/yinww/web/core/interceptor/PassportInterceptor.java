package com.yinww.web.core.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yinww.web.core.client.AccountFeignClient;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.cookie.CookieUtils;
import com.yinww.web.core.domain.Account;
import com.yinww.web.core.util.HttpUtil;

/**
 * 身份认证拦截器, 如果没有账号登录则跳转到账号登录界面
 * @author yinww
 *
 */
public class PassportInterceptor implements HandlerInterceptor {
	private static Logger log = LoggerFactory.getLogger(PassportInterceptor.class);

	@Value("${LOGIN_URL}")
	private String LOGIN_URL; //单点登录地址
	
	@Autowired
	private AccountFeignClient accountFeignClient;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = CookieUtils.getCookieValue(request, AppConstant.TOKEN);
		String requestUrl = HttpUtil.getRequestUrl(request);
		if (StringUtils.isEmpty(token)) { // cookie中没有token则跳转到登录页
			log.info("redirect to login, because token does not exist, " + requestUrl);
			request.getSession().setAttribute(AppConstant.LOGIN_ACCOUNT, null);
			redirectLogin(request, response, requestUrl);
			return false;
		} else {
			Account account = (Account) request.getSession().getAttribute(AppConstant.LOGIN_ACCOUNT);
			if(account == null) {
				account = accountFeignClient.getAccountByToken(token);
				if (account == null) {
					log.info("redirect to login, because does not find account in session and by token, " + requestUrl);
					redirectLogin(request, response, requestUrl);
					return false;
				}
			}
			// 把账号放到session中
			request.getSession().setAttribute(AppConstant.LOGIN_ACCOUNT, account);
		}
		return true;
	}

	private void redirectLogin(HttpServletRequest request, HttpServletResponse response, String requestUrl)
			throws IOException {
		Object obj = request.getParameter("logout");
		if(obj != null){
			response.sendRedirect(LOGIN_URL+ "?logout=true&redirect=" + requestUrl);
		}else{
			response.sendRedirect(LOGIN_URL+ "?redirect=" + requestUrl);
		}
	}
}
