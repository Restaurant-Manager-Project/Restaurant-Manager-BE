package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.InvoiceResponse;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper extends BaseResponseMapper<InvoiceResponse, InvoiceEntity> {
    @Override
    @Mapping(target = "employeeName", source = "employee", qualifiedByName = "getFullNameEmployee")
    @Mapping(target = "clientName", source = "client" , qualifiedByName = "getFullNameClient")
    InvoiceResponse toDto(InvoiceEntity entity);

    @Named("getFullNameClient")
    default String getFullNameClient(ClientEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getLastName() + " " + entity.getFirstName();
    }

    @Named("getFullNameEmployee")
    default String getFullNameEmployee(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getLastName() + " " + entity.getFirstName();
    }




}
