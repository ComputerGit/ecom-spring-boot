package com.at.t.eCommerce.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "discount")

public class DiscountConfig {

	@Value("${discount.types}")
	private String discountTypes;

	public List<String> getTypes() {

		return Stream.of(discountTypes.split(",")).map(String::trim).map(String::toUpperCase).collect(Collectors.toList());
	}

}
