package com.springboot.login.config;

import com.springboot.login.model.User;
import com.springboot.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.getUserbyName(username);
       if(user == null){
           throw new UsernameNotFoundException("Could not found the User!!");
       }

       CustomUserDetails customUserDetails = new CustomUserDetails(user);
       return customUserDetails;
    }
}
