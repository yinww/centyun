package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.domain.Consume;
import com.yinww.account.table.KeyValuePair;

@Mapper
public interface ConsumeMapper {

	List<Consume> getPageConsumes(@Param("tenantId") Long tenantId, @Param("searchValue") String searchValue, @Param("orders") List<KeyValuePair> orders);

	Consume getConsumeById(@Param("id") Long id);

	void addConsume(Consume consume);

}
