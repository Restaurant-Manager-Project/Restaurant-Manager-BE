package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.dto.request.RefreshTokenRequest;
import com.example.Restaurant_Manager_BE.dto.request.SignInRequest;
import com.example.Restaurant_Manager_BE.dto.response.TokenResponse;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.AuthenService;
import com.example.Restaurant_Manager_BE.services.EmployeeService;
import com.example.Restaurant_Manager_BE.services.RoleService;
import com.example.Restaurant_Manager_BE.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> access(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenService.authenticate(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenService.refresh(refreshTokenRequest));
    }

//    @GetMapping("/role/{id}")
//    public ResponseEntity<APIResponse> role(@PathVariable Long id) {
//        List<String> listKey = roleService.getPermissionKey(id);
//
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setResult(listKey);
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @GetMapping("/roles/{username}")
//    public ResponseEntity<APIResponse> roles(@PathVariable String username) {
//        AccountEntity listRole = accountRepository.findByUsername(username).get();
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setResult(listRole);
//        return ResponseEntity.ok(apiResponse);
//    }
}
