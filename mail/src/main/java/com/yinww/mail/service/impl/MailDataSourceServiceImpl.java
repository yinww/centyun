package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.MailDataSource;
import com.yinww.mail.mapper.MailDataSourceMapper;
import com.yinww.mail.service.MailDataSourceService;

@Service
public class MailDataSourceServiceImpl implements MailDataSourceService {
	
	@Autowired
	private MailDataSourceMapper mailDataSourceMapper;

	@Override
	public void addMailDataSource(MailDataSource mailDataSource) {
		mailDataSourceMapper.addMailDataSource(mailDataSource);
	}

	@Override
	public void updateMailDataSource(MailDataSource mailDataSource) {
		mailDataSourceMapper.updateMailDataSource(mailDataSource);
	}

	@Override
	public void deleteMailDataSource(MailDataSource mailDataSource) {
		mailDataSourceMapper.deleteMailDataSource(mailDataSource);
	}

	@Override
	public MailDataSource getMailDataSourceById(Long id, Long tenantId) {
		return mailDataSourceMapper.getMailDataSourceById(id, tenantId);
	}

	@Override
	public List<MailDataSource> getMailDataSources(Long tenantId) {
		return mailDataSourceMapper.getMailDataSources(tenantId);
	}

}
