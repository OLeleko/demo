package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.util.LateUpdateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.example.demo.util.ValidationUtil.checkNotFoundWithId;

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

    @Value("${vote_time}")
    private String time_vote;

    @Transactional
    public Vote create(int menu_id, int user_id) {
        /*if (!vote.isNew()) {
            return null;
        }*/
        Vote vote = new Vote();
        User user = userRepository.getOne(user_id);
        Menu menu = menuRepository.getById(menu_id);
        vote.setUser(user);
        vote.setMenu(menu);
        vote.setVote_date(LocalDate.now());
        return voteRepository.save(vote);
    }

    public Vote findById(int id, int user_id) {
        return voteRepository.getById(id, user_id);
    }

    public Vote findByDate(LocalDate date, int user_id) {
        return voteRepository.getByDate(date, user_id);
    }

    @Transactional
    public Vote update(int menu_id, int user_id) {
        LocalTime time = LocalTime.parse(time_vote);
        if (LocalTime.now().isAfter(time)) {
            throw new LateUpdateException("It's too late to update vote for today.");
        }
        Vote vote = findByDate(LocalDate.now(), user_id);
        Menu menu = menuRepository.getById(menu_id);
        vote.setMenu(menu);
        return vote;
    }

    public List<Vote> findAll(int user_id) {
        return voteRepository.getAll(user_id);
    }

    public List<Vote> findBetween(LocalDate start_date, LocalDate end_date, int user_id) {
        return voteRepository.getBetween(start_date, end_date, user_id);
    }

    public void delete(int id, int user_id) {
        checkNotFoundWithId(voteRepository.delete(id, user_id), id);
    }
}
