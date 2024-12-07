package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.dto.TableDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TableService {
    ResponseEntity<APIResponse> findByDirection(String direction);

    ResponseEntity<APIResponse> generateQRCode(Long tableId);

    ResponseEntity<APIResponse> mergeAllDetailsInOrderList(String direction);

    void generateDirection(String direction);
    ResponseEntity<APIResponse> updateStatusOfTableByID(Long id,Long status_id);
    ResponseEntity<APIResponse> deleteTableByID(Long id);
    ResponseEntity<APIResponse> getALLTables();
}
