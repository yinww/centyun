package com.yinww.code.controller;

import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class CodeController {
	private Logger log = LoggerFactory.getLogger(CodeController.class);

	@Autowired
    private StringEncryptor stringEncryptor;
	
	@RequestMapping(value = "/encrypt/{value}", method = RequestMethod.POST)
	public String encrypt(@PathVariable String value) {
		String encrypt = stringEncryptor.encrypt(value);
		log.info(value + "====" + encrypt);
		return encrypt;
	}
	
	@RequestMapping(value = "/decrypt/{value}", method = RequestMethod.POST)
	public String decrypt(@PathVariable String value) {
		String encrypt = stringEncryptor.decrypt(value);
		log.info(value + "====" + encrypt);
		return encrypt;
	}

}
