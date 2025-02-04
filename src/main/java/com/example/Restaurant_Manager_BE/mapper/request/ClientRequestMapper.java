package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.ClientRequest;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientRequestMapper extends BaseRequestMapper<ClientRequest, ClientEntity> {
}
