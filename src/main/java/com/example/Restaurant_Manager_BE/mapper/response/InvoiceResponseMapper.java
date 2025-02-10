package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.InvoiceResponse;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper extends BaseResponseMapper<InvoiceResponse, InvoiceEntity> {
}
