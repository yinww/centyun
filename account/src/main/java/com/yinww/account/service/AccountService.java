package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.constant.AccountConstant;
import com.yinww.account.domain.Manager;
import com.yinww.account.mapper.AccountMapper;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.UUIDGenerator;
import com.yinww.web.core.domain.Account;
import com.yinww.web.core.exception.BadRequestException;

@Service
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;
			
	public Account getAccountById(String accountId) {
		return accountMapper.getAccountById(accountId);
	}

	public void saveAccount(Account account) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		if(StringUtils.isEmpty(account.getId())) {
			account.setId(UUIDGenerator.getUUID());
			account.setCreator(manager.getLoginName());
			accountMapper.addAccount(account);
		} else {
			account.setEditor(manager.getLoginName());
			accountMapper.updateAccount(account);
		}
	}

	public PageInfo<Account> getPageAccounts(DTRequestParams dtParams, String tenantId) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Account> accounts = accountMapper.getPageAccounts(tenantId, StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Account> pageInfo = new PageInfo<>(accounts);
		return pageInfo;
	}

	public void deleteAccount(List<String> ids) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		accountMapper.updateStatus(ids, AccountConstant.ACCOUNT_STATUS_DELETED, manager.getLoginName());
	}

	public void repasswd(List<String> ids, String passwd) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		accountMapper.repasswd(ids, passwd);
	}

}
