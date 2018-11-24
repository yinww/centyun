package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Consume;
import com.yinww.account.mapper.ConsumeMapper;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.UUIDGenerator;

@Service
public class ConsumeService {
	
	@Autowired
	private ConsumeMapper consumeMapper;

	public PageInfo<Consume> getPageConsumes(DTRequestParams dtParams, String tenantId) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Consume> consumes = consumeMapper.getPageConsumes(tenantId, StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Consume> pageInfo = new PageInfo<>(consumes);
		return pageInfo;
	}


	public Consume getConsumeById(String id) {
		return consumeMapper.getConsumeById(id);
	}

	public void saveConsume(Consume consume) {
		// 获取当前用户
		consume.setId(UUIDGenerator.getUUID());
		consumeMapper.addConsume(consume);
	}

}
