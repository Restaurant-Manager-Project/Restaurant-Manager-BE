package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.InvoiceRequest;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceRequestMapper extends BaseRequestMapper<InvoiceRequest, InvoiceEntity> {
}
