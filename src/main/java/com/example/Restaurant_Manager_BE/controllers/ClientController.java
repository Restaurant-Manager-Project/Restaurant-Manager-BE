package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


    @PreAuthorize("hasRole('client.view')")
    @GetMapping("/api/clients/search")
    public ResponseEntity<APIResponse> findByPhone(@RequestParam String phone) {
        return clientService.findByPhone(phone);
    }

    @PreAuthorize("hasRole('client.view')")
    @GetMapping("/api/clients/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PreAuthorize("hasRole('client.create')")
    @PostMapping("/api/clients")
    public ResponseEntity<APIResponse> createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PreAuthorize("hasRole('client.delete')")
    @DeleteMapping("/api/clients/{id}")
    public ResponseEntity<APIResponse> deleteClient(@PathVariable("id") Long client_id) {
        return clientService.deleteClient(client_id);
    }
    @PreAuthorize("hasRole('client.update')")
    @PutMapping("/api/clients/{id}")
    public ResponseEntity<APIResponse> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }
    @PreAuthorize("hasRole('client.view')")
    @GetMapping("api/clients")
    public ResponseEntity<APIResponse> findAll() {
        return clientService.getALL();
    }

}
