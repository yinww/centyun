package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.InvalidMail;
import com.yinww.mail.mapper.InvalidMailMapper;
import com.yinww.mail.service.InvalidMailService;

@Service
public class InvalidMailServiceImpl implements InvalidMailService {
	
	@Autowired
	private InvalidMailMapper invalidMailMapper;

	@Override
	public void addInvalidMail(InvalidMail invalidMail) {
		invalidMailMapper.addInvalidMail(invalidMail);
	}

	@Override
	public InvalidMail getInvalidMailById(Long id, Long tenantId) {
		return invalidMailMapper.getInvalidMailById(id, tenantId);
	}

	@Override
	public List<InvalidMail> getInvalidMails(Long tenantId) {
		return invalidMailMapper.getInvalidMails(tenantId);
	}

}
