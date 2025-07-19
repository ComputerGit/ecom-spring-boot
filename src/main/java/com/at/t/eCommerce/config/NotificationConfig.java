package com.at.t.eCommerce.config;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

	@Value("${read.status}")
	public String ReadStatus;

	public List<String> getReadStatus() {

		return Stream.of(ReadStatus.split(",")).map(String::trim).map(String::toUpperCase).collect(Collectors.toList());
	}

	@Value("${notification.types}")
	public String notificationTypes;

	public List<String> getNotificationTypes() {

		return Stream.of(notificationTypes.split(",")).map(String::trim).map(String::toUpperCase)
				.collect(Collectors.toList());
	}

}
