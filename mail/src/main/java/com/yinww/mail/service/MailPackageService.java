package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.MailPackage;

public interface MailPackageService {
	
	void addMailPackage(MailPackage mailPackage);
	
	MailPackage getMailPackageById(Long id, Long tenantId);
	
	List<MailPackage> getMailPackages(Long tenantId);

}
