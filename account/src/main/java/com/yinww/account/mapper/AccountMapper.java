package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.table.KeyValuePair;
import com.yinww.web.core.domain.Account;

@Mapper
public interface AccountMapper {
	
	void addAccount(Account account);
	
	void updateAccount(Account account);

	Account getAccountById(@Param("id") Long accountId);

	void addMainAccount(Account account);

	List<Account> getPageAccounts(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

	void updateStatus(@Param("ids") List<Long> ids, @Param("status") int status, @Param("editor") String editor);

	void repasswd(@Param("ids") List<Long> ids, @Param("passwd") String passwd);

	int getAccountByName(Account account);

	void updateLanguage(@Param("id") Long id, @Param("language") String language);

	Account getAccountByLoginName(@Param("loginName") String loginName);

}
