package com.raviraj.spring_revitalizing.service;

import com.raviraj.spring_revitalizing.entity.UserEntity;
import com.raviraj.spring_revitalizing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username."));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) //No roles currently
                .build();
    }
}
