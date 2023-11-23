package com.example.desafio.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger.info")
public record OpenApiProperties(
    String version,
    String name,
    String description,
    OpenApiContactProperties contact) {
}
