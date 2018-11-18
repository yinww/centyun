package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.web.core.domain.Account;

@Mapper
public interface AccountMapper {
	
	void addAccount(Account account);
	
	void updateAccount(Account account);

	List<Account> getAccounts(@Param("tenantId") String tenantId);

	Account getAccount(@Param("id") String accountId);

	void addMainAccount(Account account);

}
