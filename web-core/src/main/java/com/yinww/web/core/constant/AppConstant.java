package com.yinww.web.core.constant;

public interface AppConstant {
	
	// constants of yww sub-system
	String SERVICES = "yww-services";   // 服务中心
	String CONFIG = "yww-config"; // 配置中心
	String LOGIN = "yww-login";   // 云登录
	String USER = "yww-tenant"; // 云租户
	String AA = "yww-aa";     // 云认证
	String CRM = "yww-crm";   // 云CRM
	String CMS = "yww-cms";   // 云网站
	String FILE = "yww-file"; // 云文件
	String SMS = "yww-sms";   // 云短信
	String MAIL = "yww-mail"; // 云邮件
	String PAY = "yww-pay";   // 云支付
	String LOG = "yww-log";   // 云日志
	String TRACE = "yww-trace";   // 云溯源
	String XIU = "yww-xiu";   // 云秀
	String HY = "yww-hy";     // 云会议
	String WJ = "yww-wj";     // 云问卷

	String UTF8 = "UTF-8";
	String LOCALHOST = "localhost";
	String EMPTY = "";
	String DOT = ".";
	String COMMA = ",";
	
	String TOKEN = "cty_token";
	String LOGIN_ACCOUNT = "login_account";
	
	int DEFAULT_PAGE_START = 1;
	int DEFAULT_PAGE_SIZE = 10;
	
	// file ext
	String REG_FILE_EXT = "^.*?\\.(jpg|jpeg|bmp|gif|png|docx|doc|xlsx|xls|txt|zip|pptx|ppt|rar)$";
	String REG_EXEL_EXT = "^.*?\\.(xlsx|xls)$";
	String REG_CONTENT_EXT = "^.*?\\.(txt|html|htm|zip)$";
	String REG_IMG_EXT = "^.*?\\.(jpg|jpeg|bmp|gif|png)$";

}
