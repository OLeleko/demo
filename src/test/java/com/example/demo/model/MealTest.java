package com.example.demo.model;

import com.example.demo.TestData.MealTestData;
import com.example.demo.repository.MealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.TestData.MealTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MealTest {

    @Autowired
    private MealRepository repository;

    @Test
    public void findById(){
        Meal meal = repository.getOne(MEAL_ID);
        assertThat(meal).isEqualTo(meal1);
    }

    @Test
    public void create() {
        Meal created = repository.save(MealTestData.getNew());
        int newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertThat(created).isEqualTo(newMeal);
    }


}