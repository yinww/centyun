package com.yinww.account.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.github.pagehelper.PageInfo;
import com.yinww.account.service.AccountService;
import com.yinww.web.core.constant.ResultEntity;
import com.yinww.web.core.domain.Account;

@Controller
public class AccountController {
	
	private Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	/**
	 * 切换语言
	 *
	 * @param lang, zh - 中文, en - 英文
	 */
	@RequestMapping(value = "/changeLang", method = RequestMethod.POST)
	@ResponseBody
	public ResultEntity changeLanauage(HttpSession session, String lang) {
		if ("zh".equals(lang)) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
		} else if ("en".equals(lang)) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
		}
		return new ResultEntity(HttpStatus.SC_OK);
	}

	@RequestMapping(value = "/accounts/{tenantId}", method = RequestMethod.POST)
	@ResponseBody
	public Object getAccounts(@PathVariable(value = "tenantId") String tenantId
			, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size) {
		if(page == null || page < 1) {
			page = 1;
		}
		if(size == null) {
//			size = AppConstant.DEFAULT_PAGE_SIZE;
			size = 2;
		}
		PageInfo<Account> accounts = accountService.getAccounts(tenantId, page, size);
		return accounts;
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public Object getAccount(@PathVariable(value = "accountId") String accountId) {
		Account account = accountService.getAccount(accountId);
		return account;
	}

	@RequestMapping(value = "/save-account", method = RequestMethod.POST)
	@ResponseBody
	public Object saveAccount(Account account) {
		log.info(account.toString());
		accountService.saveAccount(account);
		return account;
	}

}
