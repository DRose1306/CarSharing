package com.example.carsharing.annotation;


import com.example.carsharing.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET)
@Operation(summary = "Show all users", description = "Getting all users as a List", tags = {"USER"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "users received and returned",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = User.class), minItems = 2)
                        )
                ),
                @ApiResponse(
                        responseCode = "204",
                        description = "No data found for your request",
                        content = @Content()
                )
        })
public @interface GetAllUsers {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
