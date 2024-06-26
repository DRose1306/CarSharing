package com.example.carsharing.controller;


import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;
import com.example.carsharing.utils.ExpectedData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user1", password = "user1", roles = "ADMIN")
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getPaymentByIdTest() throws Exception {
        Payment expectedPayment = ExpectedData.returnPaymentById();

        String paymentJson = objectMapper.writeValueAsString(expectedPayment);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/payments/get/{id}", expectedPayment.getPaymentId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(paymentJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualPaymentJSON = mvcResult.getResponse().getContentAsString();

        Payment actualPayment = objectMapper.readValue(actualPaymentJSON, Payment.class);
        System.out.println("Expected payment: " + expectedPayment);
        System.out.println("Actual payment: " + actualPayment);

        Assertions.assertEquals(expectedPayment, actualPayment);
    }

    @Test
    void getPaymentByIdTestWithException() throws Exception {

        mockMvc.perform(get("/payments/get/1f486486-97dc-4f50-8fb1-cd87d5dd37e2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void deletePaymentByIdTestPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/payments/delete/92683b96-579e-4fee-9329-b442639582e7"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Payment with this ID was deleted SUCCESSFULLY"))
                .andReturn();
    }

    @Test
    void deletePaymentByIdTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/payments/delete/93683b96-579e-4fee-9329-b442639582e7"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"PAYMENT_NOT_EXIST_EXCEPTION\",\"errorCode\":\"NOT_FOUND\"}"))
                .andReturn();
    }

    @Test
    void createPaymentTest() throws Exception {
        PaymentCreateDto paymentCreateDto = ExpectedData.returnPaymentCreateDto();

        String paymentWrite = objectMapper.writeValueAsString(paymentCreateDto);

        MvcResult createPaymentResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/payments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paymentWrite))
                .andReturn();

        String jsonResult = createPaymentResult.getResponse().getContentAsString();
        PaymentAfterCreationDto paymentAfterCreationDto = objectMapper.readValue(jsonResult, PaymentAfterCreationDto.class);

        Assertions.assertEquals(201, createPaymentResult.getResponse().getStatus());
        Assertions.assertNotNull(paymentAfterCreationDto.getPaymentDate());

        System.out.println(jsonResult);
    }

    @Test
    void updatePaymentByIdTest() throws Exception {
        MvcResult mvcResultBeforeUpdate = mockMvc
                .perform(MockMvcRequestBuilders.get("/payments/get/92683b96-579e-4fee-9329-b442639582e7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String paymentJSONBeforeUpdate = mvcResultBeforeUpdate.getResponse().getContentAsString();
        Payment paymentBeforeUpdate = objectMapper.readValue(paymentJSONBeforeUpdate, Payment.class);
        System.out.println("Payment Before Update: " + paymentBeforeUpdate);

        Payment payment = ExpectedData.returnPaymentById();
        payment.setPaymentDate(LocalDateTime.parse("2024-04-10T19:00:00"));

        String updatedPaymentJSON = objectMapper.writeValueAsString(payment);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/payments/update/92683b96-579e-4fee-9329-b442639582e7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPaymentJSON))
                .andExpect(status().isOk())
                .andReturn();

        String updatedPaymentFromDB = updateResult.getResponse().getContentAsString();
        Payment paymentAfterUpdate = objectMapper.readValue(updatedPaymentFromDB, Payment.class);
        System.out.println("Payment After Update: " + paymentAfterUpdate);

        Assertions.assertEquals(payment, paymentAfterUpdate);
    }

    @Test
    void updatePaymentByIdWithException() throws Exception {
        String nonExistId = "1f486486-97dc-4f50-8fb1-cd87d5dd37e2";
        String requestBody = "{\"payment_date\": 2024-04-212 17:30:00}";

        mockMvc.perform(MockMvcRequestBuilders.put("/payments/update/" + nonExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }
}