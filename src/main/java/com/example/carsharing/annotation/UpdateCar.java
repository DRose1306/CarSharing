package com.example.carsharing.annotation;

import com.example.carsharing.entity.Car;
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
        summary = "Update  car by id",
        description = "The request includes updating the car field and returning the updated car.",
        tags = {"CAR"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the car",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(format = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Good Id",
                                        value = "3c004a2b-3ff3-4413-8ce3-e72ec557b6fc"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Request to update car",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Car.class),
                        examples = {
                                @ExampleObject(
                                        name = "Update car request",
                                        value = """
                                                {
                                                    "yearOfRelease": "2023",
                                                    "licensePlate": "HB HB2",
                                                    "status": "IN_USE",
                                                     "brand": "AUDI"
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Car updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Car.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Car not found",
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
public @interface UpdateCar {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
