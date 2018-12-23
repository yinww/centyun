package com.yinww.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityRequestMatcherProviderAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.yinww.web.core.WebCoreConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import(WebCoreConfig.class)
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class, SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class
		, SecurityFilterAutoConfiguration.class, SecurityRequestMatcherProviderAutoConfiguration.class})

public class MailApplication {
	public static final String APPNAME = "mail";

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

}
