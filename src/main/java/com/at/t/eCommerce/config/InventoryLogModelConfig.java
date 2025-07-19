package com.at.t.eCommerce.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryLogModelConfig {

	@Value("${change.types}")
	private String changeTypes;

	public List<String> getTypes() {

		return Stream.of(changeTypes.split(",")).map(String::trim).map(String::toUpperCase).collect(Collectors.toList());
	}

}
