package com.example.demo.model;

import com.example.demo.repository.MytryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MytryTest {
    @Autowired
    private MytryRepository repository;

    @Test
    public void findById(){
        Mytry mytry = repository.getOne(111001);
    }

}