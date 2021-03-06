package com.yinww.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.mail.domain.MailOpen;

@Mapper
public interface MailOpenMapper {
	
	void addMailOpen(MailOpen mailOpen);
	
	MailOpen getMailOpenById(Long id, Long tenantId);
	
	List<MailOpen> getMailOpens(Long tenantId);

}
