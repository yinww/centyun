package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.MailOpen;
import com.yinww.mail.mapper.MailOpenMapper;
import com.yinww.mail.service.MailOpenService;

@Service
public class MailOpenServiceImpl implements MailOpenService {
	
	@Autowired
	private MailOpenMapper mailOpenMapper;

	@Override
	public void addMailOpen(MailOpen mailOpen) {
		mailOpenMapper.addMailOpen(mailOpen);
	}

	@Override
	public MailOpen getMailOpenById(Long id, Long tenantId) {
		return mailOpenMapper.getMailOpenById(id, tenantId);
	}

	@Override
	public List<MailOpen> getMailOpens(Long tenantId) {
		return mailOpenMapper.getMailOpens(tenantId);
	}

}
