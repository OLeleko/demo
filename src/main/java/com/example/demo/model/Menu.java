package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"regist_date", "restaurant_id"}, name = "menu_idx")})
public class Menu extends AbstractBaseEntity {

    @Column(name = "regist_date", nullable = false)
    @NotNull
    private LocalDate regist_date;

    @Column(name = "restaurant_name")
    private String restaurant_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @OrderBy("price DESC")
    @JsonManagedReference
    private List<Meal> meals;

    public Menu() {
    }

    public Menu(Integer id, @NotNull LocalDate regist_date) {
        super(id);
        this.regist_date = regist_date;
    }

    public LocalDate getRegist_date() {
        return regist_date;
    }

    public void setRegist_date(LocalDate regist_date) {
        this.regist_date = regist_date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "regist_date=" + regist_date +
                ", restaurant_name=" + restaurant_name +
                ", id=" + id +
                '}';
    }
}
