package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.ExcludeMail;

public interface ExcludeMailService {
	
	void addExcludeMail(ExcludeMail excludeMail);
	
	ExcludeMail getExcludeMailById(Long id, Long tenantId);
	
	List<ExcludeMail> getExcludeMails(Long tenantId);

}
