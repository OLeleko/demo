package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name ="restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_name")})
public class Restaurant extends AbstractNamedEntity{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("regist_date DESC")
    private List<Menu> menus;

    public Restaurant() {

    }

    public Restaurant(Integer id, @NotBlank @Size(min = 2) String name) {
        super(id, name);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
