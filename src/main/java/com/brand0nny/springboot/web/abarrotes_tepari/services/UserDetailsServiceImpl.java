package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Role;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User appUser = userRepository
       .findByUsername(username)
       .orElseThrow(()-> new UsernameNotFoundException("Exception"));
    
    Set<GrantedAuthority> grandList = new HashSet<GrantedAuthority>();

    for(Role roles: appUser.getRoles()){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getDescription());
        grandList.add(grantedAuthority);

    }
    UserDetails user = (UserDetails) new User(username, appUser.getPassword(), grandList);
    return user;
    
    }
    

}
