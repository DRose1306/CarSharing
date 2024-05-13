package com.example.carsharing.controller;


import com.example.carsharing.dto.CarAfterCreationDto;
import com.example.carsharing.dto.CarCreateDto;
import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.enums.CarStatus;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void showCarByIdTest() throws Exception {
        Car expectedCar = ExpectedData.returnCarById();

        String carJson = objectMapper.writeValueAsString(expectedCar);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/car/show_car/{id}", expectedCar.getCarId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(carJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualCarJSON = mvcResult.getResponse().getContentAsString();

        Car actualCar = objectMapper.readValue(actualCarJSON, Car.class);
        System.out.println("Expected car: " + expectedCar);
        System.out.println("Actual car: " + actualCar);

        Assertions.assertEquals(expectedCar, actualCar);
    }

    @Test
    void showCarByIdTestWithException() throws Exception {

        mockMvc.perform(get("/car/show_car/1f486486-97dc-4f50-8fb1-cd87d5dd37e2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void deleteCarByIdTestPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/car/delete_car/2e88a78d-b4a7-4a00-b590-4d0f7abe6c04"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Car with this ID was deleted SUCCESSFULLY"))
                .andReturn();
    }

    @Test
    void deleteUserByIdTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/car/delete_car/8888bf3e-73a9-47da-8bae-e2e253a30ddd"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"EMPLOYEE_NOT_EXIST\",\"errorCode\":\"NOT_FOUND\"}"))
                .andReturn();
    }

    @Test
    void createCarTest() throws Exception {
        CarCreateDto carCreateDto = ExpectedData.returnCarCreateDto();

        String carWrite = objectMapper.writeValueAsString(carCreateDto);

        MvcResult createCarResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/car/add_car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carWrite))
                .andReturn();

        String jsonResult = createCarResult.getResponse().getContentAsString();
        CarAfterCreationDto carAfterCreationDto = objectMapper.readValue(jsonResult, CarAfterCreationDto.class);

        Assertions.assertEquals(201, createCarResult.getResponse().getStatus());
        Assertions.assertNotNull(carAfterCreationDto.getLicensePlate());

        System.out.println(jsonResult);
    }

    @Test
    void updateCarByIdTest() throws Exception{
        MvcResult mvcResultBeforeUpdate = mockMvc
                .perform(MockMvcRequestBuilders.get("/car/update_car/2e88a78d-b4a7-4a00-b590-4d0f7abe6c04")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String carJSONBeforeUpdate = mvcResultBeforeUpdate.getResponse().getContentAsString();
        Car carBeforeUpdate = objectMapper.readValue(carJSONBeforeUpdate, Car.class);
        System.out.println("Car Before Update: " + carBeforeUpdate);

        Car car = ExpectedData.returnCarById();
        carBeforeUpdate.setStatus(CarStatus.UNDER_REPAIR);

        String updatedCarJSON = objectMapper.writeValueAsString(car);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/car/update_car/2e88a78d-b4a7-4a00-b590-4d0f7abe6c04")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCarJSON))
                .andExpect(status().isOk())
                .andReturn();

        String updatedCarFromDB = updateResult.getResponse().getContentAsString();
        Car carAfterUpdate = objectMapper.readValue(updatedCarFromDB, Car.class);
        System.out.println("Car After Update: " + carAfterUpdate);

        Assertions.assertEquals(car,carAfterUpdate);
    }

    @Test
    void updateUserByIdWithException() throws Exception{
        String nonExistId = "1f486486-97dc-4f50-8fb1-cd87d5dd37e2";
        String requestBody = "{\"car_status\": UNDER_REPAIR}";

        mockMvc.perform(MockMvcRequestBuilders.put("/car/update_car/" + nonExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }
}