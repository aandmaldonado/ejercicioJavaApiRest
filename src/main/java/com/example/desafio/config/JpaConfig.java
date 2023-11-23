package com.example.desafio.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnProperty(value = "ms.db.context.disable", havingValue = "false", matchIfMissing = false)
@EnableJpaRepositories(basePackages = {"com.example.desafio.repository"})
@EntityScan(basePackages = {"com.example.desafio.model"})
public class JpaConfig {
}
