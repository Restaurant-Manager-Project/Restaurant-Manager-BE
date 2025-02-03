package com.example.Restaurant_Manager_BE.services;
// import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import java.util.List;


public interface TableService {
    TableResponse findByDirection(String direction);

    String generateQRCode(Long tableId);

    // TableResponse mergeAllDetailsInOrderList(String direction);

    void generateDirection(String direction);
    TableResponse updateStatusOfTableByName(Long id,String statusName);
    TableResponse deleteTableByID(Long id);
    List<TableResponse> getALLTables();
}
