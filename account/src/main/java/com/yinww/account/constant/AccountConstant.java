package com.yinww.account.constant;

public interface AccountConstant {
	String AUTH_FAIL = "common.auth_fail";
	
	String TENANT_EXISTED = "tenant.existed";
	String ACCOUNT_EXISTED = "account.existed";
	String PRODUCT_EXISTED = "product.existed";
	
	int CHARGE_STATUS_OK = 1; // 充值成功
	int CHARGE_STATUS_DELETED = 2; // 取消充值
	
	int TENANT_STATUS_REGISTED = 0;  // 0已注册
	int TENANT_STATUS_AUDITED = 1;   // 1已审核
	int TENANT_STATUS_CERTIFIED = 2; // 2已认证
	int TENANT_STATUS_LOCKED = 3;    // 3已冻结
	int TENANT_STATUS_DELETED = 4;   // 4已注销

	int ACCOUNT_STATUS_REGISTED = 0;  // 0已注册
	int ACCOUNT_STATUS_AUDITED = 1;   // 1已审核
	int ACCOUNT_STATUS_CERTIFIED = 2; // 2已认证
	int ACCOUNT_STATUS_LOCKED = 3;    // 3已冻结
	int ACCOUNT_STATUS_DELETED = 4;   // 4已注销

	int PRODUCT_STATUS_OK = 1; // 正常
	int PRODUCT_STATUS_OFFLINE = 2; // 下线停用
	int PRODUCT_STATUS_DEPRECATED = 3; // 可用，但已有新版

}
