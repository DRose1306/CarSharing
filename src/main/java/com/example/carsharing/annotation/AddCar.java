package com.example.carsharing.annotation;

import com.example.carsharing.entity.Car;
import com.example.carsharing.controller.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Add a new car",
        description = "Adding of a new car and return",
        tags = {"CAR"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The car to be added",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Car.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                    "yearOfRelease": "2022",
                                                    "licensePlate": "B B1",
                                                    "brand": "HONDA",
                                                }"""),
                                @ExampleObject(name = "Request with existing license plate",
                                        value = """
                                                {
                                                    "yearOfRelease": "2020",
                                                    "licensePlate": "B B1",
                                                    "brand": "AUDI",
                                                }"""),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "The car created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Car.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "The car already exist",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        }
)
public @interface AddCar {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
