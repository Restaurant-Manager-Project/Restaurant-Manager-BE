package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("UserDetailsService")
public class UserDetailsImpl implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        AccountDTO accountDTO = modelMapper.map(accountEntity, AccountDTO.class);
        return modelMapper.map(accountEntity, AccountDTO.class);
    }
}
