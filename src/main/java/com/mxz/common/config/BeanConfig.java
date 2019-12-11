package com.mxz.common.config;
/**
*@Description 
*@author mxz
*2018-07-27
**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mxz.Application;
import com.mxz.model.UserVO;

public class BeanConfig {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			UserVO user = restTemplate.getForObject(
					"http://localhost/user/hello", UserVO.class);
			log.info(user.toString());
		};
	}
}
