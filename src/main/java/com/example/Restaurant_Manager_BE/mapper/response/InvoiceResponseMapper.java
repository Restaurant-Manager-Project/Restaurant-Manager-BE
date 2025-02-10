package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.InvoiceResponse;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper extends BaseResponseMapper<InvoiceResponse, InvoiceEntity> {
    @Override
    @Mapping(target = "employeeName", expression = "java(entity.getEmployee().getLastName() + \" \" + entity.getEmployee().getFirstName())")
    @Mapping(target = "clientName", expression = "java(entity.getClient().getLastName() + \" \" + entity.getClient().getFirstName())")
    InvoiceResponse toDto(InvoiceEntity entity);
}
