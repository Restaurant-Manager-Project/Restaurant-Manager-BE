package com.example.Restaurant_Manager_BE.service;
import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.model.TablesModel;

import java.util.List;

public interface TablesService {
    void updateTable(TablesModel tablesModel);
    MessageRespone findTableByPassword(String password);

}
