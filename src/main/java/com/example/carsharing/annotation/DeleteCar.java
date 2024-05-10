package com.example.carsharing.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping(method = RequestMethod.DELETE)
@Operation(
        summary = "Delete car by ID",
        description = "Delete an existing car by its unique identifier",
        tags = {"CAR"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the car",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Example existing Id",
                                        value = "88a71c7e-d011-40e3-b9b5-78315c983b21"
                                ),
                                @ExampleObject(
                                        name = "Example non-existing Id",
                                        value = "efff9467-a80e-447d-8763-ee7acfa5cc"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "The car deleted"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "The car does not exist"
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface DeleteCar {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
