package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConvertDetailsImport;
import com.example.Restaurant_Manager_BE.converters.ConverterImport;
import com.example.Restaurant_Manager_BE.dto.DetailsImportDTO;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertDetailsImportImpl implements ConvertDetailsImport {
    @Override
    public DetailsImportDTO toDTO(DetailsImportEntity entity) {
        if (entity == null) {
            return null;
        }
        return DetailsImportDTO.builder()
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .quantity(entity.getQuantity())
                .importId(entity.getId())
                .importPrice(entity.getImportPrice())
                .sellPrice(entity.getPrice())
                .build();
    }

    @Override
    public DetailsImportEntity toEntity(DetailsImportDTO dto) {
        if (dto ==  null) {
            return null;
        }
        return DetailsImportEntity.builder()
                .product(ProductEntity.builder().id(dto.getProductId()).build())
                .importBill(ImportEntity.builder().id(dto.getImportId()).build())
                .quantity(dto.getQuantity())
                .importPrice(dto.getImportPrice())
                .Price(dto.getSellPrice())
                .isDeleted(false)
                .build();
    }

    @Override
    public List<DetailsImportDTO> toDTOList(List<DetailsImportEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<DetailsImportDTO> detailsImportDTOList = new ArrayList<>();
        entityList.forEach(entity -> {
            detailsImportDTOList.add(toDTO(entity));
        });
        return detailsImportDTOList;
    }

    @Override
    public List<DetailsImportEntity> toEntityList(List<DetailsImportDTO> dtoList, ImportEntity importEntity) {
        if (dtoList == null) {
            return null;
        }
        List<DetailsImportEntity> detailsImportEntityList = new ArrayList<>();
        dtoList.forEach(dto -> {
            DetailsImportEntity detailsImportEntity = toEntity(dto);
            detailsImportEntity.setImportBill(importEntity);
            detailsImportEntityList.add(detailsImportEntity);
        });
        return detailsImportEntityList;
    }

}
