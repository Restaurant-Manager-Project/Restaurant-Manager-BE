package com.example.Restaurant_Manager_BE.service;
import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.model.TablesModel;

import java.util.List;

public interface TablesService {

    MessageRespone findByDirection(String direction);
    MessageRespone generateDirection(Long id);

    MessageRespone generateQRCode(Long id);

}
