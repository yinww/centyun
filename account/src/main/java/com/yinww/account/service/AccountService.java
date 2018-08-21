package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.mapper.AccountMapper;
import com.yinww.util.UUIDGenerator;
import com.yinww.web.core.domain.Account;

@Service
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;

	public PageInfo<Account> getAccounts(String tenantId, Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Account> accounts = accountMapper.getAccounts(tenantId);
		PageInfo<Account> pageInfo = new PageInfo<>(accounts);
		return pageInfo;
	}

	public Account getAccount(String accountId) {
		return accountMapper.getAccount(accountId);
	}

	public void saveAccount(Account account) {
		if(StringUtils.isEmpty(account.getId())) {
			account.setId(UUIDGenerator.getUUID());
			accountMapper.addAccount(account);
		} else {
			accountMapper.updateAccount(account);
		}
	}

}
