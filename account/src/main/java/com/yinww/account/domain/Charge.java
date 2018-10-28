package com.yinww.account.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Charge 充值
 * 
 * @author yinww
 * 
 */
@Alias("charge")
public class Charge implements Serializable {

	private static final long serialVersionUID = 7421627264111503805L;
	
	private String id;
	private String tenantId;
	private String tenantName;
	private String productId;
	private String productName;
	private float money; // 充值金额
	private int quota; // 充值额度
	private Date expiredTime;
	private int status;
	private String chargeManagerId;
	private String chargeManagerName;
	private Date chargeTime;
	
	public Charge() {
	}

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

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getChargeManagerId() {
		return chargeManagerId;
	}

	public void setChargeManagerId(String chargeManagerId) {
		this.chargeManagerId = chargeManagerId;
	}

	public String getChargeManagerName() {
		return chargeManagerName;
	}

	public void setChargeManagerName(String chargeManagerName) {
		this.chargeManagerName = chargeManagerName;
	}

	public Date getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}
	
	@Override
	public String toString() {
		return "Charge{tenantName=" + tenantName + ", money=" + money + ", expiredTime=" + expiredTime + "}";
	}

}