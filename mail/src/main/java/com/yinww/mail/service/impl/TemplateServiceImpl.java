package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.Template;
import com.yinww.mail.mapper.TemplateMapper;
import com.yinww.mail.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	private TemplateMapper templateMapper;

	@Override
	public void addTemplate(Template template) {
		templateMapper.addTemplate(template);
	}

	@Override
	public void updateTemplate(Template template) {
		// TODO Auto-generated method stub
		templateMapper.updateTemplate(template);
	}

	@Override
	public void discardTemplate(Template template) {
		// TODO Auto-generated method stub
		templateMapper.discardTemplate(template);
	}

	@Override
	public Template getTemplateById(Long id, Long tenantId) {
		return templateMapper.getTemplateById(id, tenantId);
	}

	@Override
	public List<Template> getTemplates(Long tenantId) {
		return templateMapper.getTemplates(tenantId);
	}

}
