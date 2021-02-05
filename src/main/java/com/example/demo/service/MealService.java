package com.example.demo.service;

import com.example.demo.model.Meal;
import com.example.demo.model.Menu;
import com.example.demo.repository.MealRepository;
import com.example.demo.repository.MenuRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.example.demo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final MenuRepository menuRepository;

    public MealService(MealRepository mealRepository, MenuRepository menuRepository) {
        this.mealRepository = mealRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    @CacheEvict(value = "findByDate", allEntries = true)
    public Meal create(Meal meal, int menu_id) {
        if (!meal.isNew()) {
            return null;
        }
        Menu menu = menuRepository.getById(menu_id);
        meal.setMenu(menu);
        return mealRepository.save(meal);
    }


    public Meal findById(int id) {
        return mealRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    @CacheEvict(value = "findByDate", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(mealRepository.delete(id), id);
    }

    @Transactional
    @CacheEvict(value = "findByDate", allEntries = true)
    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        mealRepository.save(meal);
    }
}
