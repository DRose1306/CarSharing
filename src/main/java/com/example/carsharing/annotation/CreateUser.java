package com.example.carsharing.annotation;

import com.example.carsharing.entity.User;
import com.example.carsharing.controller.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create new user",
        description = "Create new user and return him",
        tags = {"USER"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The user to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = User.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                    "firstName" : "Max",
                                                    "lastName" : "Mustermann",
                                                    "dateOfBirth" : "2000-01-01",
                                                    "email" : "maxmustermann@gmail.com",
                                                    "password" : "max",
                                                    "driverLicense" : "DE789012345680",
                                                    "driverLicenseIdentifier" : "B",
                                                }"""),
                                @ExampleObject(name = "Request with existing email",
                                        value = """
                                                {
                                                    "firstName" : "Olivia",
                                                    "lastName" : "Martinez",
                                                    "dateOfBirth" : "1987-04-30",
                                                    "email" : "maxmustermann@gmail.com",
                                                    "password" : "max",
                                                    "driverLicense" : "DE789012345680",
                                                    "driverLicenseIdentifier" : "B",
                                                }"""),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "User created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = User.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "User already exist",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface CreateUser {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
