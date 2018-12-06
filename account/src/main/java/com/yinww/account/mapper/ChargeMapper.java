package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.domain.Charge;
import com.yinww.account.table.KeyValuePair;

@Mapper
public interface ChargeMapper {

	List<Charge> getPageCharges(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

	Charge getChargeById(@Param("id") Long id);

	void addCharge(Charge charge);

	void updateStatus(@Param("ids") List<Long> ids, @Param("status") int status, @Param("editor") String editor);

}
