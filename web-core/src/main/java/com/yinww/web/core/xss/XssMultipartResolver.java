package com.yinww.web.core.xss;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.yinww.web.core.config.AntiInjectConfig;
import com.yinww.web.core.xss.util.XssUtil;

/**
 * 防xss的multipart解析器
 * 
 */
public class XssMultipartResolver extends CommonsMultipartResolver {
	@Autowired
	private AntiInjectConfig antiInjectConfig;

	public XssMultipartResolver() {
	}

	@Override
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
		MultipartParsingResult parsingResult = parseRequest(request);
		return new DefaultMultipartHttpServletRequest(request, parsingResult.getMultipartFiles(),
				parsingResult.getMultipartParameters(), parsingResult.getMultipartParameterContentTypes()) {

			@Override
			public String getParameter(String name) {
				String value = super.getParameter(name);
				return XssUtil.cleanXSS(name, value, antiInjectConfig);
			}

			@Override
			public String[] getParameterValues(String name) {
				String[] values = super.getParameterValues(name);
				if (values == null)
					return null;

				String[] result = new String[values.length];
				for (int i = 0; i < result.length; i++) {
					result[i] = XssUtil.cleanXSS(name, values[i], antiInjectConfig);
				}
				return result;
			}

			@Override
			public String getHeader(String name) {
				String value = super.getHeader(name);
				return XssUtil.cleanXSS(name, value, antiInjectConfig);
			}
		};
	}
}