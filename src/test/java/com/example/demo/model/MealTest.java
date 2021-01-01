package com.example.demo.model;

import com.example.demo.repository.MealRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MealTest {

    @Autowired
    private MealRepository mealRepository;

    @Test
    public void findById(){
        Meal meal = mealRepository.getOne(100019);
    }
}