package com.niranjan.movie.service;

import com.niranjan.movie.entity.User;
import com.niranjan.movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user= userRepository.findByUsername(username);
       if(user.isPresent()){
           return new UserPrincipal();
       }
       else {
           throw  new UsernameNotFoundException("username not found");
       }

    }
}
