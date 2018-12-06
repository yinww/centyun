package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.mapper.AuditMapper;
import com.yinww.util.SnowFlakeIdWorker;
import com.yinww.web.core.domain.Audit;
import com.yinww.web.core.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditMapper auditMapper;

	public PageInfo<Audit> getAudits(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Audit> audits = auditMapper.getAudits();
		PageInfo<Audit> pageInfo = new PageInfo<>(audits);
		return pageInfo;
	}

	public void saveAudit(Audit audit) {
		Long id = audit.getId();
		if(id == null || id <= 0) {
			SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(0, 0);
			audit.setId(snowFlake.nextId());
			auditMapper.addAudit(audit);
		}
	}

	@Override
	public void saveLoginAudit(Audit audit) {
		saveAudit(audit);
	}

}
