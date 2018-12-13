package com.yinww.login.config;

import java.nio.charset.Charset;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class KaptchaConfig {
	
	@Bean
    public Producer captchaProducer() {
	    DefaultKaptcha captchaProducer = new DefaultKaptcha();
	    Properties pro = new Properties();
	    pro.put("kaptcha.border", "yes");
        pro.put("kaptcha.border.color", "105,179,90");
        pro.put("kaptcha.textproducer.font.color", "blue");
        pro.put("kaptcha.image.width", "125");
        pro.put("kaptcha.image.height", "40");
        pro.put("kaptcha.textproducer.font.size", "35");
        pro.put("kaptcha.session.key", "code");
        pro.put("kaptcha.textproducer.char.length", "4");
        pro.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
	    Config config = new Config(pro);
	    captchaProducer.setConfig(config);
	    return captchaProducer;
	}

	@Bean
    public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
	}

}
