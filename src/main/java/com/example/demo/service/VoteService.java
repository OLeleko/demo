package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.util.NotFoundException;
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

    @Transactional
    public Vote create(Vote vote, int menu_id, int user_id) {
        if (!vote.isNew()) {
            return null;
        }
        User user = userRepository.getOne(user_id);
        Menu menu = menuRepository.getById(menu_id);
        vote.setUser(user);
        vote.setMenu(menu);
        return voteRepository.save(vote);
    }

    public Vote findById(int id, int user_id) {
        return voteRepository.getById(id, user_id);
    }

    public Vote findByDate(LocalDate date, int user_id) {
        return voteRepository.getByDate(date, user_id);
    }

    @Transactional
    public Vote update(int id, int menu_id, int user_id) {
        LocalTime time = LocalTime.of(11, 00, 00);
        if (LocalTime.now().isAfter(time)) {
            throw new NotFoundException("It's too late to update vote for today.");
        }
        Vote vote = voteRepository.getById(id, user_id);
        Menu menu = menuRepository.getById(menu_id);
        if (vote == null || menu == null) {
            return null;
        }
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
