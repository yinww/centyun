package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.web.core.domain.Audit;

@Mapper
public interface AuditMapper {
	
	void addAudit(Audit audit);

	List<Audit> getAudits();

}
