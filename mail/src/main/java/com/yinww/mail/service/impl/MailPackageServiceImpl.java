package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.MailPackage;
import com.yinww.mail.mapper.MailPackageMapper;
import com.yinww.mail.service.MailPackageService;

@Service
public class MailPackageServiceImpl implements MailPackageService {
	
	@Autowired
	private MailPackageMapper mailPackageMapper;

	@Override
	public void addMailPackage(MailPackage mailPackage) {
		mailPackageMapper.addMailPackage(mailPackage);
	}

	@Override
	public MailPackage getMailPackageById(Long id, Long tenantId) {
		return mailPackageMapper.getMailPackageById(id, tenantId);
	}

	@Override
	public List<MailPackage> getMailPackages(Long tenantId) {
		return mailPackageMapper.getMailPackages(tenantId);
	}

}
