package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yinww.account.domain.Tenant;

@Mapper
public interface TenantMapper {
	
	void addTenant(Tenant tenant);
	
	void updateTenant(Tenant tenant);

	List<Tenant> getTenants();

}
