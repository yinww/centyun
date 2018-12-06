package com.yinww.web.core.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer(request.getScheme());
		sb.append("://").append(request.getServerName());
		int port = request.getServerPort();
		if (port != 80 && port != 443) {
			sb.append(":").append(port);
		}
		sb.append(request.getContextPath()).append(request.getRequestURI());
		return sb.toString();
	}

}
