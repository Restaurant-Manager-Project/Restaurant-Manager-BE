package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/api/clients/search")
    public ResponseEntity<APIResponse> findByPhone(@RequestParam String phone) {
        return clientService.findByPhone(phone);
    }

    @PostMapping("/api/clients")
    public ResponseEntity<APIResponse> createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

}
