package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.InvalidMail;

public interface InvalidMailService {
	
	void addInvalidMail(InvalidMail invalidMail);
	
	InvalidMail getInvalidMailById(Long id, Long tenantId);
	
	List<InvalidMail> getInvalidMails(Long tenantId);

}
