package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<APIResponse> createAccount(AccountDTO accountDTO);
    ResponseEntity<APIResponse> updateAccount(AccountDTO accountDTO);

}
