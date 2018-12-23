package com.yinww.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.Webhook;
import com.yinww.mail.mapper.WebhookMapper;
import com.yinww.mail.service.WebhookService;

@Service
public class WebhookServiceImpl implements WebhookService {
	
	@Autowired
	private WebhookMapper webhookMapper;

	@Override
	public void addWebhook(Webhook webhook) {
		webhookMapper.addWebhook(webhook);
	}

}
