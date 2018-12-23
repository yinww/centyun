package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.Mail;

public interface MailService {
	
	void addMail(Mail mail);
	
	void updateMail(Mail mail);
	
	Mail getMailById(Long id, Long packageId, Long tenantId);

	List<Mail> getMails(Long packageId);

}
