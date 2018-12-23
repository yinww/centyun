package com.yinww.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.mail.domain.ExcludeMail;

@Mapper
public interface ExcludeMailMapper {
	
	void addExcludeMail(ExcludeMail excludeMail);
	
	ExcludeMail getExcludeMailById(Long id, Long tenantId);
	
	List<ExcludeMail> getExcludeMails(Long tenantId);

}
