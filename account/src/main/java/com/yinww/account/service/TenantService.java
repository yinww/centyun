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
import com.yinww.account.constant.AccountConstant;
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
import com.yinww.web.core.exception.BadRequestException;

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

	public List<KeyValuePair> getAllTenants() {
		List<KeyValuePair> tenants = tenantMapper.getAllTenants();
		return tenants;
	}

	@Transactional
	public void saveTenant(Tenant tenant) {
		// 获取当前用户
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		
		if(checkTenant(tenant)) {
			throw new BadRequestException(AccountConstant.TENANT_EXISTED);
		}
		if(CommonUtil.isEmpty(tenant.getId())) {
			tenant.setId(UUIDGenerator.getUUID());
			tenant.setCreator(manager.getLoginName());
			tenantMapper.addTenant(tenant);
			Account account = new Account(UUIDGenerator.getUUID(), tenant.getId(), tenant.getMainAccount(), manager.getLoginName());
			account.setPassword(EncryptUtils.encrypt(tenant.getMainAccountPwd()));
			account.setMobile(tenant.getMobile());
			account.setPhone(tenant.getPhone());
			account.setEmail(tenant.getEmail());
			accountMapper.addMainAccount(account);
		} else {
			tenant.setEditor(manager.getLoginName());
			tenantMapper.updateTenant(tenant);
		}
	}

	private boolean checkTenant(Tenant tenant) {
		int count = tenantMapper.getTenantByName(tenant);
		return count > 0;
	}

	public Tenant getTenantById(String id) {
		return tenantMapper.getTenantById(id);
	}

	public void deleteTenant(List<String> ids) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		tenantMapper.updateStatus(ids, AccountConstant.TENANT_STATUS_DELETED, manager.getLoginName());
	}
}
