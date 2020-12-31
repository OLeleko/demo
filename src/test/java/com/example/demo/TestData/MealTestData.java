package com.example.demo.TestData;

import com.example.demo.TestMatcher;
import com.example.demo.model.Meal;

import java.math.BigDecimal;

import static com.example.demo.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final TestMatcher<Meal> MEAL_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Meal.class, "menu");
    public static final int MEAL_ID = START_SEQ + 17;
    public static final Meal meal1 = new Meal(MEAL_ID, "meal1_1_1", new BigDecimal("3.23"));
    public static Meal getNew(){
        return new Meal(null, "New_meal", new BigDecimal("15.01"));
    }
}
