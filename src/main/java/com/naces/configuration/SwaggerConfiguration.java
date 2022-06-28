package com.naces.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Value("${api.info.title}")
    private String title;

    @Value("${api.info.version}")
    private String version;

    @Value("${api.info.description}")
    private String description;

    @Value("${api.info.terms}")
    private String terms;

    @Value("${api.info.contact.name}")
    private String name;

    @Value("${api.info.contact.url}")
    private String url;

    @Value("${api.info.contact.email}")
    private String email;

    @Value("${api.info.license}")
    private String license;

    @Value("${api.info.license.url}")
    private String licenseUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(
                RequestHandlerSelectors.basePackage("com.naces.controller")).paths(PathSelectors.regex("/api/v1/naces" +
                ".*"))
            .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(title, description, version, terms, new Contact(name, url, email), license, licenseUrl,
            Collections.emptyList());
    }
}
