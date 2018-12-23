package com.yinww.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.mail.domain.ProviderConfig;
import com.yinww.mail.mapper.ProviderConfigMapper;
import com.yinww.mail.service.ProviderConfigService;

@Service
public class ProviderConfigServiceImpl implements ProviderConfigService {
	
	@Autowired
	private ProviderConfigMapper providerConfigMapper;

	@Override
	public void addProviderConfig(ProviderConfig providerConfig) {
		providerConfigMapper.addProviderConfig(providerConfig);
	}

	@Override
	public void updateProviderConfig(ProviderConfig providerConfig) {
		providerConfigMapper.updateProviderConfig(providerConfig);
	}

	@Override
	public void deleteProviderConfig(ProviderConfig providerConfig) {
		providerConfigMapper.deleteProviderConfig(providerConfig);
	}

	@Override
	public ProviderConfig getProviderConfigById(Long id, Long tenantId) {
		return providerConfigMapper.getProviderConfigById(id, tenantId);
	}

	@Override
	public List<ProviderConfig> getProviderConfigs(Long tenantId) {
		return providerConfigMapper.getProviderConfigs(tenantId);
	}

}
