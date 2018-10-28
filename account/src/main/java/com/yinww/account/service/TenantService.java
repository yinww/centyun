package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Tenant;
import com.yinww.account.mapper.TenantMapper;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.CommonUtil;
import com.yinww.util.UUIDGenerator;

@Service
public class TenantService {

	@Autowired
	private TenantMapper tenantMapper;

	public PageInfo<Tenant> getTenants(DTRequestParams dtParams) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Tenant> tenants = tenantMapper.getTenants(StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Tenant> pageInfo = new PageInfo<>(tenants);
		return pageInfo;
	}

	public void saveTenant(Tenant tenant) {
		if(CommonUtil.isEmpty(tenant.getId())) {
			tenant.setId(UUIDGenerator.getUUID());
			tenantMapper.addTenant(tenant);
		} else {
			tenantMapper.updateTenant(tenant);
		}
	}
}
