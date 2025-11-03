package com.at.t.eCommerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "jwt")
public record JWTProperties(
    String secret,
    long accessTokenExpiration,
    long refreshTokenExpiration
) {}
