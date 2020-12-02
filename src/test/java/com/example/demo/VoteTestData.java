package com.example.demo;

import com.example.demo.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.demo.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Vote.class, "user", "menu");

    public static LocalDate dateVote = LocalDate.parse("2020-11-16");
    public static Vote getNew(){
        return new Vote(null, LocalDate.of(2020, 11, 18));
    }

    public static Vote getWithId(){
        Vote vote = new Vote(100044, LocalDate.of(2020, 11, 18));
        return vote;
    }

}