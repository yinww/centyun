package com.yinww.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.yinww.web.core.WebCoreConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import(WebCoreConfig.class)
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class AccountApplication {
	
	public static final String APPNAME = "account";

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
	
}
