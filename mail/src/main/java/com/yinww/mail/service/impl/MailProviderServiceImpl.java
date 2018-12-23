package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.MailProvider;
import com.yinww.mail.mapper.MailProviderMapper;
import com.yinww.mail.service.MailProviderService;

@Service
public class MailProviderServiceImpl implements MailProviderService {
	
	@Autowired
	private MailProviderMapper mailProviderMapper;

	@Override
	public List<MailProvider> getMailProviders() {
		return mailProviderMapper.getMailProviders();
	}

}
