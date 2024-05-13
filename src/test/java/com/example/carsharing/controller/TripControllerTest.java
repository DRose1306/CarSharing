package com.example.carsharing.controller;


import com.example.carsharing.dto.TripAfterCreationDto;
import com.example.carsharing.dto.TripCreateDto;
import com.example.carsharing.entity.Trip;
import com.example.carsharing.utils.ExpectedData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getTripByIdTest() throws Exception {
        Trip expectedTrip = ExpectedData.returnTripById();

        String tripJson = objectMapper.writeValueAsString(expectedTrip);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/trip/get/{id}", expectedTrip.getTripId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(tripJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualTripJSON = mvcResult.getResponse().getContentAsString();

        Trip actualTrip = objectMapper.readValue(actualTripJSON, Trip.class);
        System.out.println("Expected trip: " + expectedTrip);
        System.out.println("Actual trip: " + actualTrip);

        Assertions.assertEquals(expectedTrip, actualTrip);
    }

    @Test
    void getTripByIdTestWithException() throws Exception {

        mockMvc.perform(get("/trip/get/1f486486-97dc-4f50-8fb1-cd87d5dd37e2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void deleteTripByIdTestPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/trip/delete/0628ad72-9f21-4dd4-98ea-ee08bcfbd36e"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Trip with this ID was deleted SUCCESSFULLY"))
                .andReturn();
    }

    @Test
    void deleteTripByIdTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/trip/delete/8888bf3e-73a9-47da-8bae-e2e253a30ddd"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"EMPLOYEE_NOT_EXIST\",\"errorCode\":\"NOT_FOUND\"}"))
                .andReturn();
    }

    @Test
    void createTripTest() throws Exception {
        TripCreateDto tripCreateDto = ExpectedData.returnTripCreateDto();

        String tripWrite = objectMapper.writeValueAsString(tripCreateDto);

        MvcResult createTripResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/trip/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tripWrite))
                .andReturn();

        String jsonResult = createTripResult.getResponse().getContentAsString();
        TripAfterCreationDto tripAfterCreationDto = objectMapper.readValue(jsonResult, TripAfterCreationDto.class);

        Assertions.assertEquals(201, createTripResult.getResponse().getStatus());
        Assertions.assertNotNull(tripAfterCreationDto.getCost());

        System.out.println(jsonResult);
    }

    @Test
    void updateTripByIdTest() throws Exception{
        MvcResult mvcResultBeforeUpdate = mockMvc
                .perform(MockMvcRequestBuilders.get("/trip/update/0628ad72-9f21-4dd4-98ea-ee08bcfbd36e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String tripJSONBeforeUpdate = mvcResultBeforeUpdate.getResponse().getContentAsString();
        Trip tripBeforeUpdate = objectMapper.readValue(tripJSONBeforeUpdate, Trip.class);
        System.out.println("Trip Before Update: " + tripBeforeUpdate);

        Trip trip = ExpectedData.returnTripById();
        trip.setEndTime(LocalDateTime.parse("2024-08-25T17:30:00"));

        String updatedTripJSON = objectMapper.writeValueAsString(trip);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/trip/update/0628ad72-9f21-4dd4-98ea-ee08bcfbd36e")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTripJSON))
                .andExpect(status().isOk())
                .andReturn();

        String updatedTripFromDB = updateResult.getResponse().getContentAsString();
        Trip tripAfterUpdate = objectMapper.readValue(updatedTripFromDB, Trip.class);
        System.out.println("Trip After Update: " + tripAfterUpdate);

        Assertions.assertEquals(trip, tripAfterUpdate);
    }

    @Test
    void updateUserByIdWithException() throws Exception{
        String nonExistId = "1f486486-97dc-4f50-8fb1-cd87d5dd37e2";
        String requestBody = "{\"end_time\": 2024-08-25 17:30:00}";

        mockMvc.perform(MockMvcRequestBuilders.put("/trip/update/" + nonExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }
}