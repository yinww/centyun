package com.yinww.mail.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.mail.domain.InvalidMail;

@Mapper
public interface InvalidMailMapper {
	
	void addInvalidMail(InvalidMail invalidMail);
	
	InvalidMail getInvalidMailById(Long id, Long tenantId);
	
	List<InvalidMail> getInvalidMails(Long tenantId);

}
