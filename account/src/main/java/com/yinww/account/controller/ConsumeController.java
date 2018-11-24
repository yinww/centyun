package com.yinww.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Consume;
import com.yinww.account.service.ConsumeService;
import com.yinww.account.service.TenantService;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.DataTableResult;
import com.yinww.util.CommonUtil;

@Controller
@RequestMapping(value = "/consume")
public class ConsumeController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(ConsumeController.class);

	@Autowired
	private ConsumeService consumeService;

	@Autowired
	private TenantService tenantService;

	@RequestMapping(value = "/index.html")
	public ModelAndView index(@RequestParam(required=false) String tenantId) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/consume/index.html"));
		model.addObject("tenantId", CommonUtil.isEmpty(tenantId) ? "" : tenantId);
		model.addObject("tenants", tenantService.getAllTenants());
        model.setViewName("consume/consume-index");
        return model;
	}

	@RequestMapping(value = "/consumes")
	@ResponseBody
	public Object getConsumes(@ModelAttribute DTRequestParams dtParams, @RequestParam(required=false) String tenantId) {
		PageInfo<Consume> consumes = consumeService.getPageConsumes(dtParams, tenantId);
        return new DataTableResult<Consume>(consumes, dtParams.getDraw());
	}

	@RequestMapping(value = "/view.html")
	public ModelAndView view(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/consume/index.html"));
		try {
			Consume consume = consumeService.getConsumeById(id);
			model.addObject("consume", consume);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
        model.setViewName("consume/consume-view");
        return model;
	}
}
