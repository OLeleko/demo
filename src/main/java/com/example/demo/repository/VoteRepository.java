package com.example.demo.repository;

import com.example.demo.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v JOIN FETCH v.user u JOIN FETCH v.menu m JOIN FETCH m.restaurant WHERE v.id=:id")
    Vote getById(@Param("id") Integer id);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user u JOIN FETCH v.menu m JOIN FETCH m.restaurant WHERE v.vote_date=:vote_date")
    List<Vote> getByDate(@Param("vote_date")LocalDate vote_date);
}
