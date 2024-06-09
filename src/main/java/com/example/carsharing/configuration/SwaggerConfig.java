package com.example.carsharing.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Carsharing",
                description = "There is a prototype of the BackEnd Carsharing Core Services data. <br />" +
                        "Data consist of addresses, authorities, cars, payments, reservations, roles, trips " +
                        "users and user info.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Urmat Bolotbek Uulu",
                        url = "https://github.com/DRose1306"
                )
        )
)
@Configuration
public class SwaggerConfig {
    @Value("${swagger.packageName:com.example.carsharing}")
    private String PACKAGE_NAME;
    public static final String CAR = "car service";
    public static final String PAYMENT = "payment service";
    public static final String TRIP = "trip service";

    public static final String USER = "user service";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan(PACKAGE_NAME)
                .addOpenApiCustomiser(openApi -> {
                    openApi.addTagsItem(new Tag().name(CAR).description("API for working with cars service"));
                    openApi.addTagsItem(new Tag().name(PAYMENT).description("API for working with payments service"));
                    openApi.addTagsItem(new Tag().name(TRIP).description("API for working with trips service"));
                    openApi.addTagsItem(new Tag().name(USER).description("API for working with users service"));
                })
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")));
    }
}
