package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.MenuService;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.validation.Valid;
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

    @Autowired
    private MenuService menuService;

    @GetMapping()
    public List<Vote> findAll(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
       int userId = user.getId();
       return service.findAll(userId);
    }

    @GetMapping("/{id}")
    public Vote findById(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable int id){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return service.findById(id, userId);
    }

    @GetMapping("/date")
    public Vote findByDate(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return service.findByDate(date, userId);
    }

    @GetMapping("/filter")
    public List<Vote> findBetween(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                  @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return service.findBetween(start, end, userId);
    }

    @GetMapping("/menus")
    public List<Menu> todayMenus(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        return menuService.findByDate(date);
    }


    @PostMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestBody Vote vote, @PathVariable int menuId){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        Vote created = service.create(vote, menuId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @PathVariable int id){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        service.delete(id, userId);
    }

   /*@PutMapping(value = "/{id}/{menu_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void update(@RequestBody Vote vote, @PathVariable int id, @PathVariable int menu_id){
        int userId = START_SEQ;
        assureIdConsistent(vote, id);
        service.update(vote, menu_id, userId);
    }*/

    @PutMapping(value = "/{id}/{menu_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void update(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                        @PathVariable int id, @PathVariable int menu_id){
        User user = customUserDetails.getUser();
        int userId = user.getId();
        service.update(id, menu_id, userId);
    }

}
