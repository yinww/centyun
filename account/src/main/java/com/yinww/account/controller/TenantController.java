package com.yinww.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Tenant;
import com.yinww.account.service.TenantService;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.DataTableResult;

@Controller
@RequestMapping(value = "/tenant")
public class TenantController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(TenantController.class);
	
	@Autowired
	private TenantService tenantService;

	// setSession和getSession不处理实际的业务, 仅做session共享的测试
	@RequestMapping(value = "/setsession", method = RequestMethod.POST)
	@ResponseBody
	public Object setSession(@RequestParam(required=false) Integer page, HttpSession session) {
		session.setAttribute("page", page);
		return session.getId();
	}
	
	@RequestMapping(value = "/getsession", method = RequestMethod.POST)
	@ResponseBody
	public Object getSession(HttpSession session) {
		Object page = session.getAttribute("page");
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", session.getId());
		map.put("page", page);
		return map;
	}

	@RequestMapping(value = "/index.html")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/tenant/index.html"));
        model.setViewName("index");
        return model;
	}

	@RequestMapping(value = "/tenants")
	@ResponseBody
	public Object getTenants(@ModelAttribute DTRequestParams dtParams, HttpServletRequest request) {
		PageInfo<Tenant> tenants = tenantService.getTenants(dtParams);
        return new DataTableResult<Tenant>(tenants, dtParams.getDraw());
	}

	@RequestMapping(value = "/add.html")
	public ModelAndView add() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/tenant/index.html"));
        model.setViewName("tenant/tenant-add");
        return model;
	}

	@RequestMapping(value = "/save-tenant", method = RequestMethod.POST)
	public Object saveTenant(Tenant tenant) {
		log.info(tenant.toString());
		tenantService.saveTenant(tenant);
		return tenant;
	}
}
