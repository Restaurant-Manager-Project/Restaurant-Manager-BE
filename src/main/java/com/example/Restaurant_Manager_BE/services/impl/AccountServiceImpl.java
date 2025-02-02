package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
//    private final ModelMapper modelMapper;
//    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    @Override
//    public AccountEntity createAccount(AccountDTO accountDTO) {
//        AccountEntity accountEntity = modelMapper.map(accountDTO, AccountEntity.class);
//        accountEntity.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
//        return accountRepository.save(accountEntity);
//
//
//    }
//
//    @Override
//    public ResponseEntity<APIResponse> updateAccount(AccountDTO accountDTO) {
//        return null;
//    }
}
