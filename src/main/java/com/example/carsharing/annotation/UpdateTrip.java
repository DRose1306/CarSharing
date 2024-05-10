package com.example.carsharing.annotation;

import com.example.carsharing.entity.Trip;
import com.example.carsharing.handler.ResponseExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping(method = RequestMethod.PUT)
@Operation(
        summary = "Update  trip by id",
        description = "The request includes updating the trip field and returning the updated trip.",
        tags = {"TRIP"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the trip",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Good Id",
                                        value = "0628ad72-9f21-4dd4-98ea-ee08bcfbd36e"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Request to update trip",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Trip.class),
                        examples = {
                                @ExampleObject(
                                        name = "Update trip request",
                                        value = """
                                                {
                                                   "startTime": "2024-08-24 00:00:10",
                                                   "endTime": "2024-08-25 17:30:00",
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Trip updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Trip.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Trip not found",
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
public @interface UpdateTrip {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
