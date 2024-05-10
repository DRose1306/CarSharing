package com.example.carsharing.annotation;

import com.example.carsharing.entity.User;
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
        summary = "Update  user by id",
        description = "The request includes updating the user field and returning the updated user.",
        tags = {"USER"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the user",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Good Id",
                                        value = "7270910c-cc71-4634-97a0-a242eb5b6064"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Request to update user",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = User.class),
                        examples = {
                                @ExampleObject(
                                        name = "Update user request",
                                        value = """
                                                {
                                                   "firstName": "John",
                                                   "lastName": "Doe",
                                                   "userInfo": {
                                                         "dateOfBirth": "1985-03-15",
                                                         "phoneNumber": "555-666-777",
                                                         "email": "john@example.com",
                                                         "login": "JD",
                                                         "password": "hashed_password_here",
                                                         "cardNumber": "9876 5432 1098 7654",
                                                         "driverLicense": "A",
                                                         "driverLicenseIdentifier": "US987654321098",
                                                         "address": {
                                                               "street": "Main Street",
                                                               "houseNumber": "123",
                                                               "city": "Anytown",
                                                               "zipCode": "12345",
                                                               "country": "USA"
                                                         }
                                                   }
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = User.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "User not found",
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
public @interface UpdateUser {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}

