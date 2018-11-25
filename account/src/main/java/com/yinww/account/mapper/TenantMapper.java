package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.domain.Tenant;
import com.yinww.account.table.KeyValuePair;

@Mapper
public interface TenantMapper {
	
	void addTenant(Tenant tenant);
	
	void updateTenant(Tenant tenant);

	Tenant getTenantById(@Param("id") String id);

	List<Tenant> getTenants(@Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

	void updateStatus(@Param("ids") List<String> ids, @Param("status") int status, @Param("editor") String editor);

	List<KeyValuePair> getAllTenants();

}
