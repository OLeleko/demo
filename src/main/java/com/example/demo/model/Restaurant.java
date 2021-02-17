package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name ="restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_name")})
public class Restaurant extends AbstractNamedEntity{

    public Restaurant() {

    }

    public Restaurant(Integer id, @NotBlank @Size(min = 2) String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
