package com.example.demo.service;

import com.example.demo.model.Meal;
import com.example.demo.repository.MealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.MealTestData.*;
import static com.example.demo.MenuTestData.MENU_ID;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealServiceTest {

    @Autowired
    private MealService service;

   @Test
    public void create() {
        Meal created = service.create(getNew(), MENU_ID);
        int newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertThat(created).isEqualTo(newMeal);
    }

    @Test
    public void findById() {
        Meal meal = service.findById(MEAL_ID);
        MEAL_MATCHER.assertMatch(meal, meal1);
    }
}