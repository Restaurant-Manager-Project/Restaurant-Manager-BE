package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.RoleService;
import com.example.Restaurant_Manager_BE.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final RoleService roleService;
    private final AccountRepository accountRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody AccountDTO accountDTO){
        // Nạp data từ request vào secuity
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword());

        // Xác thực người dùng
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String accessToken = securityUtil.createToken(authentication);



        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(accessToken);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<APIResponse> role(@PathVariable Long id) {
        List<String> listKey = roleService.getPermissionKey(id);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(listKey);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/roles/{username}")
    public ResponseEntity<APIResponse> roles(@PathVariable String username) {
        AccountEntity listRole = accountRepository.findByUsername(username).get();

        APIResponse apiResponse = new APIResponse();
        apiResponse.setResult(listRole);
        return ResponseEntity.ok(apiResponse);
    }
}
