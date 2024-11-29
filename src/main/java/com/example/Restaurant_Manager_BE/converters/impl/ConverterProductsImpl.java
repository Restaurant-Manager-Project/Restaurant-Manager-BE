package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterProducts;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ConverterProductsImpl implements ConverterProducts {
    private final CategoryRepository categoryRepository;
    private final LocalizationUtils localizationUtils;

    @Override
    public ProductDTO toDTO(ProductEntity entity) {
        if(entity == null) return null;
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .img(entity.getImg())
                .description(entity.getDescription())
                .categoryName(entity.getCategory().getName())
                .price(entity.getDetailsImportList().get(0).getPrice())
                .quantity_from_import(entity.getDetailsImportList().get(0).getQuantity())
                .quantity(entity.getQuantity())
                .build();

    }
@Override
public List<ProductDTO> toDTOList(List<ProductEntity> entities) {
    return entities.stream()
            .map(entity -> {
                // Lấy thông tin từ detailsImportList (nếu có)
                DetailsImportEntity detail = (entity.getDetailsImportList() != null && !entity.getDetailsImportList().isEmpty())
                        ? entity.getDetailsImportList().get(0)
                        : null;

                //Đổi sang DTO
                return ProductDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .img(entity.getImg())
                        .description(entity.getDescription())
                        .categoryName(entity.getCategory().getName())
                        .price(detail != null ? detail.getPrice() : 0)
                        .quantity_from_import(detail != null ? detail.getQuantity() : 0)
                        .quantity(entity.getQuantity())
                        .build();
            })
            .collect(Collectors.toList());
}


    @Override
    public ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) return null;

        ProductEntity.ProductEntityBuilder productEntityBuilder = ProductEntity.builder()
                .name(dto.getName())
                .img(dto.getImg())
                .description(dto.getDescription())
                .isDeleted(false);

        // Only set the category if categoryId is provided
        if (dto.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_NOT_EXISTED)));
            productEntityBuilder.category(category);
        }

        return productEntityBuilder.build();
    }
//    @Override
//    public List<ProductDTO> toDTOList(List<ProductEntity> entityList) {
//        if (entityList == null || entityList.isEmpty()) {
//            return null;
//        }
//        List<ProductDTO> list = new ArrayList<>();
//        entityList.forEach(x -> {
//            ProductDTO productDTO = toDTO(x);
//            list.add(productDTO);
//        });
//        return list;
//    }

    @Override
    public List<ProductEntity> toEntityList(List<ProductDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        List<ProductEntity> list = new ArrayList<>();
        dtoList.forEach(x -> {
            ProductEntity productEntity= toEntity(x);
            list.add(productEntity);
        });
        return list;
    }

    @Override
    public void mergeNonNullFieldsProduct(ProductEntity target, ProductEntity source) {
        if (source == null) {
            return;
        }
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}


