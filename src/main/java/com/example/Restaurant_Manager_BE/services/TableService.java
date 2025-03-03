package com.example.Restaurant_Manager_BE.services;
// import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import com.example.Restaurant_Manager_BE.enums.StatusTable;

import java.util.List;


public interface TableService {
    TableResponse findByDirection(String direction);

    String generateQRCode(Long tableId);

    // TableResponse mergeAllDetailsInOrderList(String direction);

    void generateDirection(String direction);
    TableResponse updateTable(Long id, TableRequest tableRequest);
    TableResponse deleteTableByID(Long id);
    List<TableResponse> getALLTables();
}
