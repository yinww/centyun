package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Audit;
import com.yinww.account.mapper.AuditMapper;
import com.yinww.util.UUIDGenerator;

@Service
public class AuditService {

	@Autowired
	private AuditMapper auditMapper;

	public PageInfo<Audit> getAccounts(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Audit> audits = auditMapper.getAudits();
		PageInfo<Audit> pageInfo = new PageInfo<>(audits);
		return pageInfo;
	}

	public void saveAccount(Audit audit) {
		if(StringUtils.isEmpty(audit.getId())) {
			audit.setId(UUIDGenerator.getUUID());
			auditMapper.addAudit(audit);
		}
	}

}
