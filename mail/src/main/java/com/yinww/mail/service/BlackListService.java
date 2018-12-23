package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.BlackList;

public interface BlackListService {
	
	void addBlackList(BlackList blackList);
	
	void deleteBlackList(BlackList blackList);
	
	BlackList getBlackListById(Long id, Long tenantId);
	
	List<BlackList> getBlackLists(Long tenantId);

}
