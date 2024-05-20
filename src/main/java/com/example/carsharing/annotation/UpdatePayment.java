package com.example.carsharing.annotation;

import com.example.carsharing.entity.Payment;
import com.example.carsharing.controller.handler.ResponseExceptionHandler;
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
        summary = "Update  payment by id",
        description = "The request includes updating the payment field and returning the updated payment.",
        tags = {"PAYMENT"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the payment",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(format = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Good Id",
                                        value = "92683b96-579e-4fee-9329-b442639582e7"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Request to update payment",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Payment.class),
                        examples = {
                                @ExampleObject(
                                        name = "Update payment request",
                                        value = """
                                                {
                                                   "amount": 900.0,
                                                   "paymentDate": "2024-04-22T12:00:00",
                                                   "paymentMethod": "VISA",
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Payment updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Payment.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Payment not found",
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
public @interface UpdatePayment {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
