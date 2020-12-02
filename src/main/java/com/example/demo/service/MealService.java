package com.example.demo.service;

import com.example.demo.model.Meal;
import com.example.demo.model.Menu;
import com.example.demo.repository.MealRepository;
import com.example.demo.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;


    public MealService(MealRepository mealRepository, MenuRepository menuRepository) {
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Meal create(Meal meal, int menu_id){
        if(!meal.isNew()){
            return null;
        }
        Menu menu = menuRepository.getById(menu_id);
        meal.setMenu(menu);
        return mealRepository.save(meal);
    }

    @Transactional
    public Meal findById(int id){

        return mealRepository.getById(id);

    }


}
