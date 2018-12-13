package com.yinww.mail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {
	private Logger log = LoggerFactory.getLogger(MailController.class);

	@RequestMapping(value = {"/", "/index.html"})
	public String index() {
		return "index";
	}
}
