package com.yinww.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yinww.web.core.client.AccountFeignClient;
import com.yinww.web.core.domain.Account;

@Service
public class AccountService implements UserDetailsService {
	@Autowired
	private AccountFeignClient accountFeignClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountFeignClient.getAccountByToken(username);
		return account;
	}

}
