package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.ExcludeMail;
import com.yinww.mail.mapper.ExcludeMailMapper;
import com.yinww.mail.service.ExcludeMailService;

@Service
public class ExcludeMailServiceImpl implements ExcludeMailService {
	
	@Autowired
	private ExcludeMailMapper excludeMailMapper;

	@Override
	public void addExcludeMail(ExcludeMail excludeMail) {
		excludeMailMapper.addExcludeMail(excludeMail);
	}

	@Override
	public ExcludeMail getExcludeMailById(Long id, Long tenantId) {
		return excludeMailMapper.getExcludeMailById(id, tenantId);
	}

	@Override
	public List<ExcludeMail> getExcludeMails(Long tenantId) {
		return excludeMailMapper.getExcludeMails(tenantId);
	}

}
