package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.ClientRequest;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ClientService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final LocalizationUtils localizationUtils;



    @GetMapping("/api/clients/search")
    public ResponseEntity<APIResponse> findByPhone(@RequestParam String phone) {
        APIResponse apiResponse = APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS))
                .result(clientService.findByPhone(phone))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('client.view')")
    @GetMapping("/api/clients/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable Long id) {
        APIResponse apiResponse = APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_SUCCESS))
                .result(clientService.findById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/api/clients")
    public ResponseEntity<APIResponse> createClient(@RequestBody ClientRequest clientRequest) {
        String message = clientService.createClient(clientRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_FALIED);
        APIResponse apiResponse = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('client.delete')")
    @DeleteMapping("/api/clients/{id}")
    public ResponseEntity<APIResponse> deleteClient(@PathVariable("id") Long id) {
        String message = clientService.deleteClient(id) ?
                localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_DELETE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_DELETE_FAILED);
        APIResponse apiResponse = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize("hasAuthority('client.update')")
    @PutMapping("/api/clients/{id}")
    public ResponseEntity<APIResponse> updateClient(@PathVariable("id") Long id, @RequestBody ClientRequest clientRequest) {
        String message = clientService.updateClient(id, clientRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_UPDATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_UPDATE_FAILED);
        APIResponse apiResponse = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize("hasAuthority('client.view')")
    @GetMapping("api/clients")
    public ResponseEntity<APIResponse> findAll() {
        APIResponse apiResponse = APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_GET_LIST_SUCCESS))
                .result(clientService.getAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
