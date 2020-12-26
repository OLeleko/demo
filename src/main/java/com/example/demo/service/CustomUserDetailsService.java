package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = repository.findByName(name);
        CustomUserDetails userDetails = null;
        if(user != null){
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        }else{
            throw new UsernameNotFoundException("User not exist with name: " + name);
        }
        return userDetails;
    }
}
