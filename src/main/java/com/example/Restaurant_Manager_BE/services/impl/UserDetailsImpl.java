package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
//@Service
//@Component("userDetailsService")
public class UserDetailsImpl implements UserDetailsService {
//    private final AccountRepository accountRepository;
//    private final RoleServiceImpl roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AccountEntity accountEntity = accountRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        List<String> keyPermission = roleService.getPermissionKey(accountEntity.getRole().getId());
//        List<GrantedAuthority> updatedAuthorities = keyPermission.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
//        return new User(accountEntity.getUsername(),
//                        accountEntity.getPassword(),
//                        updatedAuthorities);
//    }
        return null;

}}
