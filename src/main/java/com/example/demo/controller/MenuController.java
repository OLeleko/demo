package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    @Autowired
    private MenuService service;

    @GetMapping()
    public List<Menu> findAll() {
        return service.findAll();
    }

    @GetMapping("/filter")
    public List<Menu> findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.findByDate(date);
    }

    /*@GetMapping("/{id}")
    public Menu findById(@PathVariable int id) {
        Menu result = service.findById(id);
        return result;
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findById(@PathVariable int id) {
        Menu result = service.findById(id);
        if(result == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu, @RequestParam int restaurant_id) {
        Menu created = service.create(menu, restaurant_id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/menus/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
