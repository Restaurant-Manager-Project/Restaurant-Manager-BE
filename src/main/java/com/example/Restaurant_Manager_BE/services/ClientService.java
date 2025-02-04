package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.ClientRequest;
import com.example.Restaurant_Manager_BE.dto.response.ClientResponse;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ClientResponse findByPhone(String phone);
    boolean createClient(ClientRequest clientRequest);
    boolean deleteClient(Long clientId);
    boolean updateClient(Long id, ClientRequest clientRequest);
    List<ClientResponse> getAll();
//    ResponseEntity<APIResponse> getRank(Long id,Long paid);
    ClientResponse findById(Long id);
}
