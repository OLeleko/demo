package com.example.demo.controller;

import com.example.demo.model.Vote;
import com.example.demo.service.VoteService;
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

import static com.example.demo.model.AbstractBaseEntity.START_SEQ;
import static com.example.demo.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    @Autowired
    private VoteService service;

    @GetMapping()
    public List<Vote> findAll(){
       int userId = START_SEQ;
       return service.findAll(userId);
    }

    @GetMapping("/{id}")
    public Vote findById(@PathVariable int id){
        int userId = START_SEQ;
        return service.findById(id, userId);
    }

    @GetMapping("/date")
    public Vote findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        int userId = START_SEQ;
        return service.findByDate(date, userId);
    }

    @GetMapping("/filter")
    public List<Vote> findBetween(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        int userId = START_SEQ;
        return service.findBetween(start, end, userId);
    }

    @PostMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote, @PathVariable int menuId){
        int userId = START_SEQ;
        Vote created = service.create(vote, menuId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        int userId = START_SEQ;
        service.delete(id, userId);
    }

   /*@PutMapping(value = "/{id}/{menu_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void update(@RequestBody Vote vote, @PathVariable int id, @PathVariable int menu_id){
        int userId = START_SEQ;
        assureIdConsistent(vote, id);
        service.update(vote, menu_id, userId);
    }*/

    @PutMapping(value = "/{id}/{menu_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void update(@PathVariable int id, @PathVariable int menu_id){
        int userId = START_SEQ;
        service.update(id, menu_id, userId);
    }

}
