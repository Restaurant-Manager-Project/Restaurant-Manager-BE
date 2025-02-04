package com.example.Restaurant_Manager_BE.mapper.request;
//import lib here
import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import com.example.Restaurant_Manager_BE.entities.StatusTableEntity;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TableRequestMapper extends BaseRequestMapper<TableRequest, TableEntity> {

    
}
