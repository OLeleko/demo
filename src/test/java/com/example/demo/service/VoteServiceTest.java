package com.example.demo.service;

import com.example.demo.model.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.TestData.UserTestData.USER_ID;
import static com.example.demo.TestData.VoteTestData.*;
import static com.example.demo.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteServiceTest {

    @Autowired
    private VoteService service;

   @Test
    public void create() {
        Vote created = service.create(getNew(),100008, START_SEQ + 4);
        int newId = created.getId();
        Vote newVote = getNew();
        newVote.setId(newId);
        assertThat(created).isEqualTo(newVote);
    }

    @Test
    public void findById(){
        Vote vote = service.findById(100044, USER_ID);
        VOTE_MATCHER.assertMatch(vote, getWithId());
    }

    @Test
    public void findByDate(){
        Vote vote = service.findByDate(dateVote, USER_ID);
        assertThat(vote.getId()).isEqualTo(100042);
    }
/*

    @Test
    public void update(){
        Vote vote = service.findById(100044);
        service.update(vote, 100000, 100013);
        assertThat(vote.getMenu().getId()).isEqualTo(100013);
    }
*/


}