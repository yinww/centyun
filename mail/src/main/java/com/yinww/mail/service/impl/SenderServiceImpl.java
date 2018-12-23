package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.Sender;
import com.yinww.mail.mapper.SenderMapper;
import com.yinww.mail.service.SenderService;

@Service
public class SenderServiceImpl implements SenderService {
	
	@Autowired
	private SenderMapper senderMapper;

	@Override
	public void addSender(Sender sender) {
		senderMapper.addSender(sender);
	}

	@Override
	public void updateSender(Sender sender) {
		senderMapper.updateSender(sender);
	}

	@Override
	public void deleteSender(Sender sender) {
		senderMapper.deleteSender(sender);
	}

	@Override
	public Sender getSenderById(Long id, Long tenantId) {
		return senderMapper.getSenderById(id, tenantId);
	}

	@Override
	public List<Sender> getSenders(Long tenantId) {
		return senderMapper.getSenders(tenantId);
	}

}
