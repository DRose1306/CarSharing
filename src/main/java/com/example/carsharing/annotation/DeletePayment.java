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
        summary = "Delete payment by ID",
        description = "Delete an existing payment by its unique identifier",
        tags = {"PAYMENT"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the payment",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Example existing Id",
                                        value = "92683b96-579e-4fee-9329-b442639582e7"
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
                        description = "The payment deleted"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "The payment does not exist"
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface DeletePayment {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}

