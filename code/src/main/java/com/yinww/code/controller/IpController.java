package com.yinww.code.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IpController {
	
	@RequestMapping(value = "/ip/{value}", method = RequestMethod.POST)
	public String getCity(String ip) {
		return null;
	}

}
