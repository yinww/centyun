package com.yinww.account.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Charge;
import com.yinww.account.service.ChargeService;
import com.yinww.account.service.ProductService;
import com.yinww.account.service.TenantService;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.DataTableResult;
import com.yinww.util.CommonUtil;
import com.yinww.web.core.constant.AppConstant;
import com.yinww.web.core.constant.ResultEntity;

@Controller
@RequestMapping(value = "/charge")
public class ChargeController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(ChargeController.class);
	
	@Autowired
	private ChargeService chargeService;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/index.html")
	public ModelAndView index(@RequestParam(required=false) String tenantId) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/charge/index.html"));
		model.addObject("tenantId", CommonUtil.isEmpty(tenantId) ? "" : tenantId);
		model.addObject("tenants", tenantService.getAllTenants());
        model.setViewName("charge/charge-index");
        return model;
	}

	@RequestMapping(value = "/charges")
	@ResponseBody
	public Object getCharges(@ModelAttribute DTRequestParams dtParams, @RequestParam(required=false) String tenantId) {
		PageInfo<Charge> charges = chargeService.getPageCharges(dtParams, tenantId);
        return new DataTableResult<Charge>(charges, dtParams.getDraw());
	}

	@RequestMapping(value = "/add.html")
	public ModelAndView add(@RequestParam(required=false, value="tenantId") String tenantId) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/charge/index.html"));
		if(CommonUtil.isEmpty(tenantId)) {
			model.addObject("tenants", tenantService.getAllTenants());
		} else {
			model.addObject("tenant", tenantService.getTenantById(tenantId));
		}
		model.addObject("products", productService.getAvailableProducts());
        model.setViewName("charge/charge-add");
        return model;
	}

	@RequestMapping(value = "/view.html")
	public ModelAndView view(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/charge/index.html"));
		Charge charge = chargeService.getChargeById(id);
		model.addObject("charge", charge);
        model.setViewName("charge/charge-view");
        return model;
	}

	@RequestMapping(value = "/save-charge", method = RequestMethod.POST)
	@ResponseBody
	public Object saveCharge(Charge charge) {
		try {
			chargeService.saveCharge(charge);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			ResultEntity result = new ResultEntity();
			result.setData(e.getMessage());
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			return result;
		}
		return new ResultEntity(HttpStatus.OK.value());
	}

	@RequestMapping(value = "/update-status", method = RequestMethod.POST)
	@ResponseBody
	public Object updateStatus(String ids, int status) {
		if(!CommonUtil.isEmpty(ids)) {
			try {
				List<String> list = Arrays.asList(ids.split(AppConstant.COMMA));
				chargeService.updateStatus(list, status);
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// 转换日期
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}
}
