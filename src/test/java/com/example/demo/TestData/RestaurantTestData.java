package com.example.demo.TestData;

import com.example.demo.model.Restaurant;

import static com.example.demo.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ + 6;
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "rest2");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }
}
