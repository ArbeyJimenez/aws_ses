package com.awsses.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;



@Configuration
public class AwsSesConfg {

	@Bean
	public SesClient amazonSES() {
		return SesClient.builder().region(Region.US_EAST_1).build();
	}
}
