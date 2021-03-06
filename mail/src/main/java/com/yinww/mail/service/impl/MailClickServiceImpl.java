package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.MailClick;
import com.yinww.mail.mapper.MailClickMapper;
import com.yinww.mail.service.MailClickService;

@Service
public class MailClickServiceImpl implements MailClickService {
	
	@Autowired
	private MailClickMapper mailClickMapper;

	@Override
	public void addMailClick(MailClick MailClick) {
		mailClickMapper.addMailClick(MailClick);
	}

	@Override
	public MailClick getMailClickById(Long id, Long tenantId) {
		return mailClickMapper.getMailClickById(id, tenantId);
	}

	@Override
	public List<MailClick> getMailClicks(Long tenantId) {
		return mailClickMapper.getMailClicks(tenantId);
	}

}
