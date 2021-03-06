package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.Mail;
import com.yinww.mail.mapper.MailMapper;
import com.yinww.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private MailMapper mailMapper;

	@Override
	public void addMail(Mail mail) {
		mailMapper.addMail(mail);
	}

	@Override
	public void updateMail(Mail mail) {
		mailMapper.updateMail(mail);
	}

	@Override
	public Mail getMailById(Long id, Long packageId, Long tenantId) {
		return mailMapper.getMailById(id, packageId, tenantId);
	}

	@Override
	public List<Mail> getMails(Long packageId) {
		return mailMapper.getMails(packageId);
	}

}
