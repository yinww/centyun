package com.yinww.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinww.login.domain.Module;
import com.yinww.login.mapper.ModuleMapper;

@Service
public class ModuleService {
	@Autowired
	private ModuleMapper moduleMapper;
	
	public List<Module> getAllModules() {
		return moduleMapper.getModules(null);
	}
	
	public List<Module> getAvailableModules() {
		return moduleMapper.getModules(1);
	}

}
