package com.yinww.account.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
import com.yinww.util.CommonUtil;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.constant.ResultEntity;
import com.yinww.web.core.exception.BadRequestException;

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
        model.setViewName("tenant/tenant-index");
        return model;
	}

	@RequestMapping(value = "/tenants")
	@ResponseBody
	public Object getTenants(@ModelAttribute DTRequestParams dtParams) {
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

	@RequestMapping(value = "/edit.html")
	public ModelAndView edit(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/tenant/index.html"));
		Tenant tenant = tenantService.getTenantById(id);
		model.addObject("tenant", tenant);
        model.setViewName("tenant/tenant-edit");
        return model;
	}

	@RequestMapping(value = "/view.html")
	public ModelAndView view(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/tenant/index.html"));
		Tenant tenant = tenantService.getTenantById(id);
		model.addObject("tenant", tenant);
        model.setViewName("tenant/tenant-view");
        return model;
	}

	@RequestMapping(value = "/save-tenant", method = RequestMethod.POST)
	@ResponseBody
	public Object saveTenant(@Validated Tenant tenant, HttpServletRequest request) {
		try {
			tenantService.saveTenant(tenant);
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

	@RequestMapping(value = "/delete-tenant", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteTenant(String ids, HttpServletRequest request) {
		if(!CommonUtil.isEmpty(ids)) {
			try {
				List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
				tenantService.deleteTenant(CommonUtil.strings2Longs(list));
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
}
