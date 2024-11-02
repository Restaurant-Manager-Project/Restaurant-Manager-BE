package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.dto.TableDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TableService {
    ResponseEntity<APIResponse> findByDirection(String direction);

    ResponseEntity<APIResponse> generateQRCode(String direction);

    ResponseEntity<APIResponse> mergeAllDetailsInOrderList(String direction);

    void generateDirection(String direction);

}
