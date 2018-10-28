package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.account.domain.Audit;

@Mapper
public interface AuditMapper {
	
	void addAudit(Audit audit);

	List<Audit> getAudits();

}
