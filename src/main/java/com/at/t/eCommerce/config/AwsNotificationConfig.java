package com.at.t.eCommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsNotificationConfig {

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
            .region(Region.of("us-east-1"))
            .credentialsProvider(ProfileCredentialsProvider.create("ecommerce-dev"))
            .build();
    }

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
            .region(Region.of("us-east-1"))
            .credentialsProvider(ProfileCredentialsProvider.create("ecommerce-dev"))
            .build();
    }
}
