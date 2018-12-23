package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.Sender;

public interface SenderService {
	
	void addSender(Sender sender);
	
	void updateSender(Sender sender);
	
	void deleteSender(Sender sender);
	
	Sender getSenderById(Long id, Long tenantId);
	
	List<Sender> getSenders(Long tenantId);

}
