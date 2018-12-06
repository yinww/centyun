package com.yinww.web.core.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yinww.web.core.domain.Account;

@FeignClient(name = "account")
public interface AccountFeignClient {

	@RequestMapping("/account/getAccountByToken")
	public Account getAccountByToken(@RequestParam("token") String token);

	@RequestMapping("/account/updateLanguage")
	public void updateLanguage(@RequestParam("id") Long id, @RequestParam("language") String language);
}
