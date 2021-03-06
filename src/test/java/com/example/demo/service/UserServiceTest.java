package com.example.demo.service;

import com.example.demo.TestData.UserTestData;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.TestData.UserTestData.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findById(){
        User user = userService.findById(USER_ID);
        USER_MATCHER.assertMatch(user, user1);
    }

    @Test
    public void create() {
        User created = userService.create(UserTestData.getNew());
        int newId = created.getId();
        User newUser = UserTestData.getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
    }
}