package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Manager;
import com.yinww.account.domain.Tenant;
import com.yinww.account.mapper.AccountMapper;
import com.yinww.account.mapper.TenantMapper;
import com.yinww.account.security.EncryptUtils;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.CommonUtil;
import com.yinww.util.UUIDGenerator;
import com.yinww.web.core.domain.Account;

@Service
public class TenantService {

	@Autowired
	private TenantMapper tenantMapper;
	
	@Autowired
	private AccountMapper accountMapper;

	public PageInfo<Tenant> getTenants(DTRequestParams dtParams) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Tenant> tenants = tenantMapper.getTenants(StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Tenant> pageInfo = new PageInfo<>(tenants);
		return pageInfo;
	}

	@Transactional
	public void saveTenant(Tenant tenant) {
		// 获取当前用户
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(CommonUtil.isEmpty(tenant.getId())) {
			tenant.setId(UUIDGenerator.getUUID());
			tenant.setCreator(manager.getLoginName());
			tenantMapper.addTenant(tenant);
			Account account = new Account(UUIDGenerator.getUUID(), tenant.getId(), tenant.getMainAccount(), manager.getLoginName());
			account.setPassword(EncryptUtils.encrypt(tenant.getMainAccountPwd()));
			accountMapper.addMainAccount(account);
		} else {
			tenant.setEditor(manager.getLoginName());
			tenantMapper.updateTenant(tenant);
		}
	}

	public Tenant getTenantById(String id) {
		return tenantMapper.getTenantById(id);
	}

	public void deleteTenant(List<String> ids) {
		tenantMapper.deleteTenant(ids);
	}
}
