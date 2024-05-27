package com.example.carsharing.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.packageName:com.example.carsharing}")
    private String PACKAGE_NAME;
    public static final String CAR = "car service";
    public static final String PAYMENT = "payment service";
    public static final String TRIP = "trip service";

    public static final String USER = "user service";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_NAME))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(CAR, "API for working with cars"))
                .tags(new Tag(PAYMENT, "API for working with payments"))
                .tags(new Tag(TRIP, "API for working with promo trips"))
                .tags(new Tag(USER, "API for working with users"));
    }
}
