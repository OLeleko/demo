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
    public Vote create(int menuId, int userId) {
        Vote vote = new Vote();
        User user = userRepository.getOne(userId);
        Menu menu = menuRepository.getById(menuId);
        vote.setUser(user);
        vote.setMenu(menu);
        vote.setVote_date(LocalDate.now());
        return voteRepository.save(vote);
    }

    public Vote findById(int id, int userId) {
        return voteRepository.getById(id, userId);
    }

    public Vote findByDate(LocalDate date, int userId) {
        return voteRepository.getByDate(date, userId);
    }

    @Transactional
    public Vote update(int menuId, int userId) {
        LocalTime time = LocalTime.parse(time_vote);
        if (LocalTime.now().isAfter(time)) {
            throw new LateUpdateException("It's too late to update vote for today.");
        }
        Vote vote = findByDate(LocalDate.now(), userId);
        Menu menu = menuRepository.getById(menuId);
        vote.setMenu(menu);
        return vote;
    }

    public List<Vote> findAll(int userId) {
        return voteRepository.getAll(userId);
    }

    public List<Vote> findBetween(LocalDate start_date, LocalDate end_date, int userId) {
        return voteRepository.getBetween(start_date, end_date, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }
}
