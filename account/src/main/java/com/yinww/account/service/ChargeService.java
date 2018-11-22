package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.domain.Charge;
import com.yinww.account.domain.Manager;
import com.yinww.account.mapper.ChargeMapper;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.UUIDGenerator;

@Service
public class ChargeService {
	
	@Autowired
	private ChargeMapper chargeMapper;

	public PageInfo<Charge> getCharges(DTRequestParams dtParams) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Charge> charges = chargeMapper.getCharges(StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Charge> pageInfo = new PageInfo<>(charges);
		return pageInfo;
	}

	public Charge getChargeById(String id) {
		return chargeMapper.getChargeById(id);
	}

	public void saveCharge(Charge charge) {
		// 获取当前用户
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		charge.setId(UUIDGenerator.getUUID());
		charge.setChargeManagerId(manager.getLoginName());
		chargeMapper.addCharge(charge);
	}

	public void updateStatus(List<String> ids, int status) {
		chargeMapper.updateStatus(ids, status);
	}

}
