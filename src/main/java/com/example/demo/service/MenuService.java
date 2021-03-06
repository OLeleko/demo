package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @CacheEvict(value = "findByDate", allEntries = true)
    public Menu create(Menu menu, int restaurantId) {
        if (!menu.isNew()) {
            return null;
        }
        Restaurant rest = restaurantRepository.findById(restaurantId).orElse(null);
        menu.setRestaurant(rest);
        return menuRepository.save(menu);
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Menu findById(int id) {
        return menuRepository.getById(id);
    }

    @Cacheable("findByDate")
    public List<Menu> findByDate(LocalDate date) {

        return menuRepository.getByDate(date);
    }

    @CacheEvict(value = "findByDate", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }
}
