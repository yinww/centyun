package com.yinww.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Tenant;
import com.yinww.account.service.TenantService;
import com.yinww.web.core.constant.AppConstant;

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {
	
	private Logger log = LoggerFactory.getLogger(TenantController.class);
	
	@Autowired
	private TenantService tenantService;

	// setSession和getSession不处理实际的业务, 仅做session共享的测试
	@RequestMapping(value = "/setsession", method = RequestMethod.POST)
	public Object setSession(@RequestParam(required=false) Integer page, HttpSession session) {
		session.setAttribute("page", page);
		return session.getId();
	}
	
	@RequestMapping(value = "/getsession", method = RequestMethod.POST)
	public Object getSession(HttpSession session) {
		Object page = session.getAttribute("page");
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", session.getId());
		map.put("page", page);
		return map;
	}

	@RequestMapping(value = "/tenants", method = RequestMethod.POST)
	public Object getTenants(@RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size) {
		if(page == null || page < 1) {
			page = 1;
		}
		if(size == null || size < 1) {
			size = AppConstant.DEFAULT_PAGE_SIZE;
		}
		PageInfo<Tenant> tenants = tenantService.getTenants(page, size);
		return tenants;
	}

	@RequestMapping(value = "/save-tenant", method = RequestMethod.POST)
	public Object saveTenant(Tenant tenant) {
		log.info(tenant.toString());
		tenantService.saveTenant(tenant);
		return tenant;
	}
}
