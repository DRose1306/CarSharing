package com.example.carsharing.controller;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getUserByIdTest() throws Exception {
        User expectedUser = ExpectedData.returnUserById();

        String userJson = objectMapper.writeValueAsString(expectedUser);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/user/get/{id}", expectedUser.getUserId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualUserJSON = mvcResult.getResponse().getContentAsString();

        User actualUser = objectMapper.readValue(actualUserJSON, User.class);
        System.out.println("Expected user: " + expectedUser);
        System.out.println("Actual user: " + actualUser);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void getUserByIdTestWithException() throws Exception {

        mockMvc.perform(get("/user/get/1f486486-97dc-4f50-8fb1-cd87d5dd37e2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllUsersTest() throws Exception {
        User user = ExpectedData.returnUserById();
        List<User> userListExtend = Arrays.asList(user, user, user, user, user);
        MvcResult userResult = gelAllUsers();
        String userResultJSON = userResult.getResponse().getContentAsString();
        List<User> userList = objectMapper.readValue(userResultJSON, new TypeReference<>() {
        });
        assertEquals(userListExtend.size(), userList.size());
    }

    @Test
    void deleteUserByIdTestPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/55035fe9-37e3-466f-ba4a-197f23fc5700"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("User with this ID was deleted SUCCESSFULLY"))
                .andReturn();
    }

    @Test
    void deleteUserByIdTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/8888bf3e-73a9-47da-8bae-e2e253a30ddd"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"USER_NOT_EXIST_EXCEPTION\",\"errorCode\":\"NOT_FOUND\"}"))
                .andReturn();
    }

    @Test
    void createUserTest() throws Exception {
        UserRegistrationDto userRegistrationDto = ExpectedData.returnUserRegistrationDto();

        String userWrite = objectMapper.writeValueAsString(userRegistrationDto);

        MvcResult createUserResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/user/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userWrite))
                .andReturn();

        String jsonResult = createUserResult.getResponse().getContentAsString();
        UserAfterRegistrationDto userAfterRegistrationDto = objectMapper.readValue(jsonResult, UserAfterRegistrationDto.class);

        Assertions.assertEquals(201, createUserResult.getResponse().getStatus());
        Assertions.assertNotNull(userAfterRegistrationDto.getLogin());

        System.out.println(jsonResult);
    }

    @Test
    void updateUserByIdTest() throws Exception {
        MvcResult mvcResultBeforeUpdate = mockMvc
                .perform(MockMvcRequestBuilders.get("/user/get/cd8edecd-0d27-4228-8fe6-911c1cf7fd7c")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String userJSONBeforeUpdate = mvcResultBeforeUpdate.getResponse().getContentAsString();
        User userBeforeUpdate = objectMapper.readValue(userJSONBeforeUpdate, User.class);
        System.out.println("User Before Update: " + userBeforeUpdate);

        User user = ExpectedData.returnUserById();
        user.setFirstName("Oli");

        String updatedUserJSON = objectMapper.writeValueAsString(user);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/user/update/cd8edecd-0d27-4228-8fe6-911c1cf7fd7c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJSON))
                .andExpect(status().isOk())
                .andReturn();

        String updatedUserFromDB = updateResult.getResponse().getContentAsString();
        User userAfterUpdate = objectMapper.readValue(updatedUserFromDB, User.class);
        System.out.println("User After Update: " + userAfterUpdate);

        Assertions.assertEquals(user, userAfterUpdate);
    }

    @Test
    void updateUserByIdWithException() throws Exception {
        String nonExistId = "1f486486-97dc-4f50-8fb1-cd87d5dd37e2";
        String requestBody = "{\"first_name\": Mike}";

        mockMvc.perform(MockMvcRequestBuilders.put("/user/update/" + nonExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }

    private MvcResult gelAllUsers() throws Exception {
        return mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/get_all")).andExpect(status().isOk()).andReturn();
    }
}