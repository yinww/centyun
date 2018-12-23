package com.yinww.mail.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.mail.domain.Webhook;

@Mapper
public interface WebhookMapper {
	
	void addWebhook(Webhook webhook);

}
