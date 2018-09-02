package com.yinww.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.yinww.web.core.WebCoreConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import(WebCoreConfig.class)
public class SpiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderApplication.class, args);
	}

}
