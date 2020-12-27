package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.Restaurant;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
/*import org.springframework.stereotype.Service;*/
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
    public Menu create(Menu menu, int restaurant_id){
        if(!menu.isNew()){
            return null;
        }
        Restaurant rest = restaurantRepository.getOne(restaurant_id);
        menu.setRestaurant(rest);
        menu.setRestaurant_name(rest.getName());
        return menuRepository.save(menu);

    }

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public Menu findById(int id){
        return menuRepository.getById(id);
    }


    @Cacheable("findByDate")
    public List<Menu> findByDate(LocalDate date){

        return menuRepository.getByDate(date);
    }

   /* @Transactional
    public void update(Menu menu){
        Assert.notNull(menu, "menu must not be null");
        menuRepository.save(menu);
    }
*/
    public void delete(int id){
        checkNotFoundWithId(menuRepository.delete(id), id);
    }

}
