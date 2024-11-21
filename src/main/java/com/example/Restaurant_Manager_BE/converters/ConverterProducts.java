package com.example.Restaurant_Manager_BE.converters;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;


import java.util.List;

public interface ConverterProducts {
    public ProductDTO toDTO(ProductEntity entity);

    public ProductEntity toEntity(ProductDTO dto);

    public List<ProductDTO> toDTOList(List<ProductEntity> entityList);

    public List<ProductEntity> toEntityList(List<ProductDTO> dtoList);

    public void mergeNonNullFieldsProduct(ProductEntity target, ProductEntity source);
}
