package com.yinww.account.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Charge;
import com.yinww.account.service.ChargeService;
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

	@RequestMapping(value = "/index.html")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/charge/index.html"));
        model.setViewName("charge/charge-index");
        return model;
	}


	@RequestMapping(value = "/charges")
	@ResponseBody
	public Object getCharges(@ModelAttribute DTRequestParams dtParams) {
		PageInfo<Charge> charges = chargeService.getCharges(dtParams);
        return new DataTableResult<Charge>(charges, dtParams.getDraw());
	}

	@RequestMapping(value = "/add.html")
	public ModelAndView add() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/charge/index.html"));
        model.setViewName("charge/charge-add");
        return model;
	}

	@RequestMapping(value = "/edit.html")
	public ModelAndView edit(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/charge/index.html"));
		Charge charge = chargeService.getChargeById(id);
		model.addObject("charge", charge);
        model.setViewName("charge/charge-edit");
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
}
