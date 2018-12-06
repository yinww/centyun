package com.yinww.account.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.service.AccountService;
import com.yinww.account.service.TenantService;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.DataTableResult;
import com.yinww.util.CommonUtil;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.constant.ResultEntity;
import com.yinww.web.core.domain.Account;
import com.yinww.web.core.exception.BadRequestException;
import com.yinww.web.core.util.EncryptUtils;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private TenantService tenantService;

	@RequestMapping(value = "/index.html")
	public ModelAndView index(@RequestParam(required=false) Long tenantId) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/account/index.html"));
		model.addObject("tenantId", tenantId == null || tenantId <= 0 ? 0 : tenantId);
		model.addObject("tenants", tenantService.getAllTenants());
        model.setViewName("account/account-index");
        return model;
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	@ResponseBody
	public Object getAccounts(@ModelAttribute DTRequestParams dtParams, @RequestParam(required=false) Long tenantId) {
		PageInfo<Account> tenants = accountService.getPageAccounts(dtParams, tenantId == null || tenantId <= 0 ? null : tenantId);
        return new DataTableResult<Account>(tenants, dtParams.getDraw());
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public Object getAccount(@PathVariable(value = "accountId") Long accountId) {
		Account account = accountService.getAccountById(accountId);
		return account;
	}

	@RequestMapping(value = "/getAccountByToken")
	@ResponseBody
	public Object getAccountByToken(@RequestParam(value = "token") String token) {
		Account account = accountService.getAccountByToken(token);
		return account;
	}

	@RequestMapping(value = "/add.html")
	public ModelAndView add(@RequestParam(required=false, value="tenantId") String tenantId) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/account/index.html"));
		model.addObject("tenants", tenantService.getAllTenants());
		if(!CommonUtil.isEmpty(tenantId)) {
			model.addObject("tenantId", tenantId);
		}
        model.setViewName("account/account-add");
        return model;
	}

	@RequestMapping(value = "/edit.html")
	public ModelAndView edit(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/account/index.html"));
		Account account = accountService.getAccountById(id);
		model.addObject("account", account);
        model.setViewName("account/account-edit");
        return model;
	}

	@RequestMapping(value = "/view.html")
	public ModelAndView view(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/account/index.html"));
		Account account = accountService.getAccountById(id);
		model.addObject("account", account);
        model.setViewName("account/account-view");
        return model;
	}

	@RequestMapping(value = "/save-account", method = RequestMethod.POST)
	@ResponseBody
	public Object saveAccount(Account account, HttpServletRequest request) {
		try {
			accountService.saveAccount(account);
		} catch (BadRequestException e) {
			log.error(e.getMessage(), e);
			ResultEntity result = new ResultEntity();
			result.setData(getMessage(e.getMessage(), request));
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			return result;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ResultEntity result = new ResultEntity();
			result.setData(e.getMessage());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			return result;
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

	@RequestMapping(value = "/updateLanguage")
	@ResponseBody
	public Object updateLanguage(@RequestParam("id") Long id, @RequestParam("language") String language) {
		try {
			accountService.updateLanguage(id, language);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ResultEntity result = new ResultEntity();
			result.setData(e.getMessage());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			return result;
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

	@RequestMapping(value = "/delete-account", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteAccount(String ids, HttpServletRequest request) {
		if(!CommonUtil.isEmpty(ids)) {
			try {
				List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
				accountService.deleteAccount(CommonUtil.strings2Longs(list));
			} catch (BadRequestException e) {
				log.error(e.getMessage(), e);
				ResultEntity result = new ResultEntity();
				result.setData(getMessage(e.getMessage(), request));
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				return result;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				ResultEntity result = new ResultEntity();
				result.setData(e.getMessage());
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				return result;
			}
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

	@RequestMapping(value = "/repasswd", method = RequestMethod.POST)
	@ResponseBody
	public Object repasswd(@RequestParam("ids") String ids, @RequestParam("pwd") String pwd, HttpServletRequest request) {
		if(!CommonUtil.isEmpty(ids)) {
			try {
				List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
				accountService.repasswd(CommonUtil.strings2Longs(list), EncryptUtils.encrypt(pwd));
			} catch (BadRequestException e) {
				log.error(e.getMessage(), e);
				ResultEntity result = new ResultEntity();
				result.setData(getMessage(e.getMessage(), request));
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				return result;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				ResultEntity result = new ResultEntity();
				result.setData(e.getMessage());
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				return result;
			}
		}
		return new ResultEntity(HttpStatus.OK.value(), getMessage("common.reasswdSuccess", request));
	}

}
