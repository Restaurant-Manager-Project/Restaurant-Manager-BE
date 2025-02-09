package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.ImportResponse;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import com.example.Restaurant_Manager_BE.mapper.request.DetailsImportRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = DetailsImportResponseMapper.class)
public interface ImportResponseMapper extends BaseResponseMapper<ImportResponse, ImportEntity> {
    @Override
    @Mapping(target = "employeeName", expression = "java(entity.getEmployee().getLastName() + \" \" + entity.getEmployee().getFirstName())")
    @Mapping(target = "supplierName", expression = "java(entity.getSupplier().getName())")
    @Mapping(target = "detailsImport", source = "detailsProductList")
    ImportResponse toDto(ImportEntity entity);
}
