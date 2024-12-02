package com.example.Restaurant_Manager_BE.converters;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ConverterProducts {
    public ProductDTO toDTO(ProductEntity entity);

    public Page<ProductDTO> toDTO_pagination(Page<ProductEntity> productEntityPage);

    public ProductEntity toEntity(ProductDTO dto);

    public List<ProductDTO> toDTOList(List<ProductEntity> entityList);

    public List<ProductEntity> toEntityList(List<ProductDTO> dtoList);

    public void mergeNonNullFieldsProduct(ProductEntity target, ProductEntity source);
}
