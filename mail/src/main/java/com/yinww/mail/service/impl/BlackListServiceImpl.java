package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.BlackList;
import com.yinww.mail.mapper.BlackListMapper;
import com.yinww.mail.service.BlackListService;

@Service
public class BlackListServiceImpl implements BlackListService {
	
	@Autowired
	private BlackListMapper blackListMapper;

	@Override
	public void addBlackList(BlackList blackList) {
		blackListMapper.addBlackList(blackList);
	}

	@Override
	public void deleteBlackList(BlackList blackList) {
		blackListMapper.deleteBlackList(blackList);
	}

	@Override
	public BlackList getBlackListById(Long id, Long tenantId) {
		return blackListMapper.getBlackListById(id, tenantId);
	}

	@Override
	public List<BlackList> getBlackLists(Long tenantId) {
		return blackListMapper.getBlackLists(tenantId);
	}

}
