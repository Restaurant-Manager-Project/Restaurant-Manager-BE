package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.DetailsImportDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;

import java.util.List;

public interface ConvertDetailsImport {
    DetailsImportDTO toDTO(DetailsImportEntity entity);

    DetailsImportEntity toEntity(DetailsImportDTO dto);

    List<DetailsImportDTO> toDTOList(List<DetailsImportEntity> entityList);

    List<DetailsImportEntity> toEntityList(List<DetailsImportDTO> dtoList, ImportEntity importEntity);
}
