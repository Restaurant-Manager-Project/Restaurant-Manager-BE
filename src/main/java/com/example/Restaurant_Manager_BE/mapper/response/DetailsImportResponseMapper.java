package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.DetailsImportResponse;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DetailsImportResponseMapper extends BaseResponseMapper<DetailsImportResponse, DetailsImportEntity> {
    @Override
    @Mapping(target = "productName", source = "entity.product", qualifiedByName = "convertName")
    DetailsImportResponse toDto(DetailsImportEntity entity);

    @Named("convertName")
    default String convertName(ProductEntity productEntity) {
        return productEntity == null ? "Hihi" : productEntity.getName();
    }
}
