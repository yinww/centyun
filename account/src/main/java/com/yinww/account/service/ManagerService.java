package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yinww.account.domain.Manager;
import com.yinww.account.domain.Module;
import com.yinww.account.mapper.ManagerMapper;

@Service
public class ManagerService implements UserDetailsService {

	@Autowired
	private ManagerMapper managerMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager manager = managerMapper.getManager(username);
		return manager;
	}
	
	public List<Module> getModules() {
		List<Module> modules = managerMapper.getModules();
		return modules;
	}
	
	public void updateLanguage(Manager manager) {
		managerMapper.updateLanguage(manager);
	}

}
