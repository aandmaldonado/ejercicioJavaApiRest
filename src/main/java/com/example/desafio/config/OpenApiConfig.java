package com.example.desafio.config;

import com.example.desafio.swagger.OpenApiProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(OpenApiProperties.class)
public class OpenApiConfig {
    @Value("${openapi.dev-url}")
    private String devUrl;

    @Value("${openapi.dev-url.description}")
    private String devUrlDesc;

    @Bean
    public OpenAPI apiInfo(OpenApiProperties openApiProperties) {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(devUrlDesc);
        return new OpenAPI()
                .info(new Info()
                        .title(openApiProperties.name())
                        .version(openApiProperties.version())
                        .description(openApiProperties.description())
                        .contact(new Contact()
                                .name(openApiProperties.contact().name())
                                .email(openApiProperties.contact().mail())))
                .servers(List.of(devServer));
    }
}
