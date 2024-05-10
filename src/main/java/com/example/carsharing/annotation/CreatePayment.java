package com.example.carsharing.annotation;

import com.example.carsharing.entity.Payment;
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
        summary = "Create a new payment",
        description = "Creation of a new payment and return",
        tags = {"PAYMENT"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The payment to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Payment.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                    "amount": 900.0,
                                                    "paymentDate": "2024-04-22T12:00:00",
                                                    "paymentMethod": "VISA",
                                                    "userId": "55035fe9-37e3-466f-ba4a-197f23fc5700"
                                                }"""
                                ),
                                @ExampleObject(name = "Existing payment date and user",
                                        value = """
                                                {
                                                    "amount": 100.0,
                                                    "paymentDate": "2024-04-10 19:00:00",
                                                    "paymentMethod": "VISA",
                                                    "userId": "7270910c-cc71-4634-97a0-a242eb5b6064"
                                                }"""
                                ),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "The payment created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Payment.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "The payment already exist",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        }
)
public @interface CreatePayment {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
