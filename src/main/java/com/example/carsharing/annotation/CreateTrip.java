package com.example.carsharing.annotation;

import com.example.carsharing.entity.Trip;
import com.example.carsharing.handler.ResponseExceptionHandler;
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
        summary = "Create a new trip",
        description = "Creation of a new trip and return",
        tags = {"TRIP"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The trip to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Trip.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                  "startTime": "2024-05-10T13:00:00",
                                                  "endTime": "2024-05-11T14:00:00",
                                                  "userId": "7270910c-cc71-4634-97a0-a242eb5b6064",
                                                  "carId": "88a71c7e-d011-40e3-b9b5-78315c983b21"
                                                }
                                                """
                                ),
                                @ExampleObject(name = "Existing start time and user",
                                        value = """
                                                {
                                                  "startTime": "2024-08-10T00:00:00",
                                                  "endTime": "2024-08-11T14:00:00",
                                                  "userId": "7881bf3e-73a9-47da-8bae-e2e253a30ddd",
                                                  "carId": "ef6869b7-2402-48c7-bff4-141563be2d8c"
                                                }
                                                """
                                ),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "The trip created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Trip.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "The trip already exist",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        }
)
public @interface CreateTrip {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}

