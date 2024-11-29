package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity<APIResponse> findByPhone(String phone);
    ResponseEntity<APIResponse> createClient(ClientDTO clientDTO);
    ResponseEntity<APIResponse> deleteClient(Long client_id);
    ResponseEntity<APIResponse> updateClient(Long id, ClientDTO clientDTO);
    void SkipNullFields(ClientDTO clientDTO, ClientEntity clientEntity);
    ResponseEntity<APIResponse> getALL();
    ResponseEntity<APIResponse> getRank(Long id,Long paid);
    ResponseEntity<APIResponse> findById(Long id);
}
