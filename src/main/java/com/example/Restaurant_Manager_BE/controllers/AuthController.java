package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.dto.EmployeeDTO;
import com.example.Restaurant_Manager_BE.dto.request.SignInRequest;
import com.example.Restaurant_Manager_BE.dto.response.TokenResponse;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.AuthenService;
import com.example.Restaurant_Manager_BE.services.EmployeeService;
import com.example.Restaurant_Manager_BE.services.RoleService;
import com.example.Restaurant_Manager_BE.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final RoleService roleService;
    private final AccountRepository accountRepository;
    private final AuthenService authenService;
    private final SecurityUtil securityUtil;
    private final EmployeeService employeeService;
    @PostMapping("/access")
    public ResponseEntity<TokenResponse> access(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenService.authenticate(signInRequest));
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
