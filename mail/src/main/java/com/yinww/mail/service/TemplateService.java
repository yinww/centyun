package com.yinww.mail.service;

import java.util.List;

import com.yinww.mail.domain.Template;

public interface TemplateService {
	
	void addTemplate(Template template);
	
	void updateTemplate(Template template);
	
	void discardTemplate(Template template);
	
	Template getTemplateById(Long id, Long tenantId);
	
	List<Template> getTemplates(Long tenantId);

}
