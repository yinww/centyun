package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.yinww.util.SnowFlakeIdWorker;
import com.yinww.web.core.domain.Account;
import com.yinww.web.core.exception.BadRequestException;

@Service
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountMapper accountMapper;
			
	public Account getAccountById(Long accountId) {
		return accountMapper.getAccountById(accountId);
	}

	public void saveAccount(Account account) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		if(checkAccount(account)) {
			throw new BadRequestException(AccountConstant.ACCOUNT_EXISTED);
		}
		Long id = account.getId();
		if(id == null || id <= 0) {
			SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(0, 0);
			account.setId(snowFlake.nextId());
			account.setCreator(manager.getLoginName());
			accountMapper.addAccount(account);
		} else {
			account.setEditor(manager.getLoginName());
			accountMapper.updateAccount(account);
		}
	}

	private boolean checkAccount(Account account) {
		int count = accountMapper.getAccountByName(account);
		return count > 0;
	}

	public PageInfo<Account> getPageAccounts(DTRequestParams dtParams, Long tenantId) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Account> accounts = accountMapper.getPageAccounts(tenantId, StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Account> pageInfo = new PageInfo<>(accounts);
		return pageInfo;
	}

	public void deleteAccount(List<Long> ids) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		accountMapper.updateStatus(ids, AccountConstant.ACCOUNT_STATUS_DELETED, manager.getLoginName());
	}

	public void repasswd(List<Long> ids, String passwd) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		accountMapper.repasswd(ids, passwd);
	}

	public Account getAccountByToken(String token) {
		Account account = new Account();
		account.setLoginName("test");
		account.setRealName("hello test");
		return account;
	}

	public void updateLanguage(Long id, String language) {
		accountMapper.updateLanguage(id, language);
	}

	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		Account account = accountMapper.getAccountByLoginName(loginName);
		return account;
	}

}
