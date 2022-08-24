package com.doan.project.web.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doan.project.web.project.entities.User;
import com.doan.project.web.project.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
  
        Set<GrantedAuthority> grantedAuthorites = new HashSet<GrantedAuthority>();
        grantedAuthorites.add(new SimpleGrantedAuthority(user.getRole().getNameRole()));
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
        		user.getPassword(), grantedAuthorites);
    }


}
