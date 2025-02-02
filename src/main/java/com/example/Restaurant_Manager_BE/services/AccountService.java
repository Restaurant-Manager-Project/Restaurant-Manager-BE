package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService {

    UserDetailsService userDetailsService();

//    AccountEntity createAccount(AccountDTO accountDTO);
//
//
//    ResponseEntity<APIResponse> updateAccount(AccountDTO accountDTO);

}
