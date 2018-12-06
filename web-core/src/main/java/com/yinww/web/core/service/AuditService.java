package com.yinww.web.core.service;

import com.yinww.web.core.domain.Audit;

public interface AuditService {
	
	void saveLoginAudit(Audit audit);

}
