package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.MailClick;

public interface MailClickService {
	
	void addMailClick(MailClick MailClick);
	
	MailClick getMailClickById(Long id, Long tenantId);
	
	List<MailClick> getMailClicks(Long tenantId);
}
