package com.yinww.web.core.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinww.web.core.domain.Account;
import com.yinww.web.core.domain.Module;

@FeignClient(name = "login")
public interface LoginFeignClient {

	@RequestMapping("/getAvailableModules")
	public List<Module> getAvailableModules();
	
	@RequestMapping("/getAccount")
	public Account getAccount();
	
}
