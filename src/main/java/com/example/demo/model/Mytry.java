package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "mytry")
public class Mytry {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "registered")
    private LocalDate registered;

    @Column(name = "today")
    private LocalDate today;

    public Mytry(int id, String name, LocalDate registered, LocalDate today) {
        this.id = id;
        this.name = name;
        this.registered = registered;
        this.today = today;
    }

    public Mytry() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "Mytry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registered=" + registered +
                ", today=" + today +
                '}';
    }
}
