package com.example.demo.repository;

import com.example.demo.model.Meal;
import com.example.demo.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT ml FROM Meal ml JOIN FETCH ml.menu mn JOIN FETCH mn.restaurant WHERE ml.id=:id")
    Meal getById(@Param("id") Integer id);
}
