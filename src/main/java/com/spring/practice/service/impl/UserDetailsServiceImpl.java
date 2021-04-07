package com.spring.practice.service.impl;

import com.spring.practice.entity.UserDetail;
import com.spring.practice.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail user = userDetailRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return userDetailRepository.findByUsername(username);
        }
    }
}
