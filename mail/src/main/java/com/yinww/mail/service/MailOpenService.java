package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.MailOpen;

public interface MailOpenService {
	
	void addMailOpen(MailOpen mailOpen);
	
	MailOpen getMailOpenById(Long id, Long tenantId);
	
	List<MailOpen> getMailOpens(Long tenantId);

}
