package com.example.demo.controller;

import com.example.demo.TestData.RestaurantTestData;
import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.demo.TestData.RestaurantTestData.RESTAURANT_ID;
import static com.example.demo.TestData.RestaurantTestData.RESTAURANT_MATCHER;
import static com.example.demo.TestData.UserTestData.admin1;
import static com.example.demo.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_Restaurant_URL = "/admin/restaurants" + '/';

    @Autowired
    private RestaurantService service;

    @Test
    public void findById() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_Restaurant_URL + RESTAURANT_ID)
                .with(userHttpBasic(admin1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value((RESTAURANT_ID)));
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_Restaurant_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin1))
                .content(writeValue(newRestaurant)))
                .andExpect(status().isCreated())
                .andDo(print());

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
    }
}