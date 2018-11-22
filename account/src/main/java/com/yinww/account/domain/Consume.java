package com.yinww.account.domain;

import java.util.Date;

public class Consume {

	private String id;
	private String tenantId;
	private String tenantName;
	private String productId;
	private String productName;
	private String num;
	private Date occurTime;
	private String ipStr;
	private Long ip;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public String getIpStr() {
		return ipStr;
	}

	public void setIpStr(String ipStr) {
		this.ipStr = ipStr;
	}

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

}
