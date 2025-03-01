package com.example.Restaurant_Manager_BE.mapper.request;


import com.example.Restaurant_Manager_BE.dto.request.DetailOrderRequest;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DetailOrderRequestMapper extends BaseRequestMapper<DetailOrderRequest, DetailsOrderEntity> {
    @Override
    @Mapping(target = "product", source = "productId", qualifiedByName = "toProduct")
    DetailsOrderEntity toEntity(DetailOrderRequest dto);

    @Named("toProduct")
    default ProductEntity toProduct(Long productId) {
        if (productId == null) {
            return null;
        }
        return ProductEntity.builder()
                .id(productId)
                .build();
    }
}
