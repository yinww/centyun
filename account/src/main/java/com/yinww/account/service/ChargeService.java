package com.yinww.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinww.account.constant.AccountConstant;
import com.yinww.account.domain.Charge;
import com.yinww.account.domain.Manager;
import com.yinww.account.mapper.ChargeMapper;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.SnowFlakeIdWorker;
import com.yinww.web.core.exception.BadRequestException;

@Service
public class ChargeService {
	
	@Autowired
	private ChargeMapper chargeMapper;

	public PageInfo<Charge> getPageCharges(DTRequestParams dtParams, Long tenantId) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Charge> charges = chargeMapper.getPageCharges(tenantId, StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Charge> pageInfo = new PageInfo<>(charges);
		return pageInfo;
	}

	public Charge getChargeById(Long id) {
		return chargeMapper.getChargeById(id);
	}

	public void saveCharge(Charge charge) throws Exception {
		// 获取当前用户
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(0, 0);
		charge.setId(snowFlake.nextId());
		charge.setChargeManager(manager.getLoginName());
		chargeMapper.addCharge(charge);
	}

	public void updateStatus(List<Long> ids, int status) throws Exception {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		chargeMapper.updateStatus(ids, status, manager.getLoginName());
	}

}
