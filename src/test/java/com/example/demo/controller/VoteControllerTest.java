package com.example.demo.controller;

import com.example.demo.TestData.VoteTestData;
import com.example.demo.model.Vote;
import com.example.demo.service.MenuService;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Test;

import static com.example.demo.TestData.MenuTestData.MENU_ID;
import static com.example.demo.TestData.UserTestData.*;
import static com.example.demo.TestData.VoteTestData.*;
import static com.example.demo.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoteControllerTest extends AbstractControllerTest{

    private static final String REST_VOTE_URL = "/votes" + '/';

    @Autowired
    private VoteService service;

    @Autowired
    private MenuService menuService;

    @Test
    public void findById() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_VOTE_URL + VOTE_ID)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value((VOTE_ID)));;

    }

    @Test
    public void create() throws Exception{
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_VOTE_URL + 100010)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin1))
                .content(writeValue(newVote)))
                .andExpect(status().isCreated())
                .andDo(print());

        Vote created = readFromJson(action, Vote.class);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
    }





}