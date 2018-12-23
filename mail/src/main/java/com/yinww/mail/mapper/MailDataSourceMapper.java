package com.yinww.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.mail.domain.MailDataSource;

@Mapper
public interface MailDataSourceMapper {
	
	void addMailDataSource(MailDataSource mailDataSource);
	
	void updateMailDataSource(MailDataSource mailDataSource);
	
	void deleteMailDataSource(MailDataSource mailDataSource);
	
	MailDataSource getMailDataSourceById(Long id, Long tenantId);
	
	List<MailDataSource> getMailDataSources(Long tenantId);

}
