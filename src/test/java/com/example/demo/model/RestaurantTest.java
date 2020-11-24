package com.example.demo.model;

import com.example.demo.repository.RestaurantRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/*import static com.example.demo.RestaurantTestData.restaurant1;*/
import static org.assertj.core.api.Assertions.assertThat;
/*import static com.example.demo.RestaurantTestData.RESTAURANT_ID;*/
import static com.example.demo.RestaurantTestData.*;
/*import static org.junit.Assert.*;*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class RestaurantTest {

    @Autowired
    private RestaurantRepository repository;

    @Test
    public void findById(){
        Restaurant restaurant = repository.getOne(RESTAURANT_ID);
        assertThat(restaurant).isEqualTo(restaurant1);
    }

    @Test
    public  void create(){
        Restaurant created = repository.save(getNew());
        int newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        assertThat(created).isEqualTo(newRestaurant);
    }



}