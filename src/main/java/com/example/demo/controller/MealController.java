package com.example.demo.controller;

import com.example.demo.model.Meal;
import com.example.demo.service.MealService;
import com.example.demo.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.example.demo.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = "/admin/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {

    @Autowired
    private MealService service;

    @GetMapping("/{id}")
    public Meal findById(@PathVariable int id) {
        Meal result = service.findById(id);
        if (result == null) {
            throw new NotFoundException("Object not found");
        }
        return result;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> create(@RequestBody Meal meal, @RequestParam int menuId) {
        Meal created = service.create(meal, menuId);
        URI uriOfNewResorce = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/meals/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResorce).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}