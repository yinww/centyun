package com.yinww.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.yinww.web.core.WebCoreConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import(WebCoreConfig.class)
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class MailApplication {
	public static final String APPNAME = "mail";

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

}
