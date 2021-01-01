package com.example.demo.model;

import com.example.demo.TestData.UserTestData;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.TestData.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findById() {
        User user = repository.getOne(USER_ID);
        assertThat(user).isEqualTo(user1);
    }

    @Test
    public void create() {
        User created = repository.save(UserTestData.getNew());
        int newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        assertThat(created).isEqualTo(newUser);
    }
}