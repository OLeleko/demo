package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;


    public VoteService(VoteRepository voteRepository, UserRepository userRepository, MenuRepository menuRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Vote create(Vote vote, int user_id, int menu_id){
        if(!vote.isNew()){
            return null;
        }
        User user = userRepository.getOne(user_id);
        Menu menu = menuRepository.getById(menu_id);
        vote.setUser(user);
        vote.setMenu(menu);
        return voteRepository.save(vote);
    }

    public Vote findById(int id){
        return voteRepository.getById(id);
    }

    public List<Vote> findByDate(LocalDate date){
        return voteRepository.getByDate(date);
    }
    @Transactional
    public Vote update(Vote vote, int user_id, int menu_id){
        if(vote.isNew() || vote.getUser().getId() != user_id){
            return null;
        }
        Menu menu = menuRepository.getById(menu_id);
        vote.setMenu(menu);
        return vote;
    }
}
