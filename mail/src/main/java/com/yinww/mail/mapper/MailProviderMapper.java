package com.yinww.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.mail.domain.MailProvider;

@Mapper
public interface MailProviderMapper {
	
	List<MailProvider> getMailProviders();

}
