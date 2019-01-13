package com.yinww.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinww.mail.service.MailDataSourceService;
import com.yinww.mail.service.ProviderConfigService;
import com.yinww.mail.service.SenderService;

@Controller
@RequestMapping(value = "/config")
public class ConfigController extends BaseController {
	
	@Autowired
	private ProviderConfigService providerConfigService;

	@Autowired
	private MailDataSourceService mailDataSourceService;

	@Autowired
	private SenderService senderConfigService;
	
	@RequestMapping(value = "/index.html")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.addObject("modules", getModules("/config/index.html"));
        model.setViewName("config/provider-index");
        return model;
	}

}
