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
import com.yinww.account.domain.Manager;
import com.yinww.account.domain.Product;
import com.yinww.account.mapper.ProductMapper;
import com.yinww.account.table.DTRequestParams;
import com.yinww.account.table.KeyValuePair;
import com.yinww.util.SnowFlakeIdWorker;
import com.yinww.web.core.exception.BadRequestException;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	public PageInfo<Product> getProducts(DTRequestParams dtParams) {
		PageHelper.startPage(dtParams.getStart(), dtParams.getLength());
		String searchValue = dtParams.getSearchValue();
		List<KeyValuePair> orders = dtParams.getOrders();
		List<Product> products = productMapper.getPageProducts(StringUtils.isEmpty(searchValue) ? null: searchValue, 
				CollectionUtils.isEmpty(orders) ? null : orders);
		PageInfo<Product> pageInfo = new PageInfo<>(products);
		return pageInfo;
	}

	public Product getProductById(Long id) {
		return productMapper.getProductById(id);
	}

	public void saveProduct(Product product) {
		// 获取当前用户
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		if(checkProduct(product)) {
			throw new BadRequestException(AccountConstant.TENANT_EXISTED);
		}
		Long id = product.getId();
		if(id == null || id <= 0) {
			SnowFlakeIdWorker snowFlake = new SnowFlakeIdWorker(0, 0);
			product.setId(snowFlake.nextId());
			product.setCreator(manager.getLoginName());
			productMapper.addProduct(product);
		} else {
			product.setEditor(manager.getLoginName());
			productMapper.updateProduct(product);
		}
	}

	private boolean checkProduct(Product product) {
		int count = productMapper.getProductByName(product);
		return count > 0;
	}

	public void deleteProduct(List<Long> ids) {
		Manager manager = (Manager)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(manager == null) {
			throw new BadRequestException(AccountConstant.AUTH_FAIL);
		}
		productMapper.updateStatus(ids, AccountConstant.PRODUCT_STATUS_OFFLINE, manager.getLoginName());
	}

	public Object getAvailableProducts() {
		return productMapper.getAvailableProducts();
	}

}
