package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
/*import org.springframework.stereotype.Service;*/

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Transactional
    public Menu create(Menu menu, int restaurant_id){
        if(!menu.isNew()){
            return null;
        }
        Restaurant rest = restaurantRepository.getOne(restaurant_id);
        menu.setRestaurant(rest);
        return menuRepository.save(menu);

    }


    public Menu findById(int id){
        return menuRepository.getById(id);
    }
    
    public List<Menu> findByDate(LocalDate date){
        return menuRepository.getByDate(date);
    }
}