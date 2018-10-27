package com.yinww.account.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/encode")
public class EncodeController {

	@Autowired
    private StringEncryptor stringEncryptor;
	
	@RequestMapping(value = "/encrypt/{value}", method = RequestMethod.POST)
	public String encrypt(@PathVariable String value) {
		String encrypt = stringEncryptor.encrypt(value);
		return encrypt;
	}
	
	@RequestMapping(value = "/decrypt/{value}", method = RequestMethod.POST)
	public String decrypt(@PathVariable String value) {
		String decrypt = stringEncryptor.decrypt(value);
		return decrypt;
	}

}
