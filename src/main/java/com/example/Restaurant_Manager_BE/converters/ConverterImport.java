package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ConverterImport {
    public ImportDTO toDTO(ImportEntity entity);

    public ImportEntity toEntity(ImportDTO dto);

    public List<ImportDTO> toDTOList(List<ImportEntity> entityList);

    public List<ImportEntity> toEntityList(List<ImportDTO> dtoList);

    public void mergeNonNullFields(ImportEntity target, ImportEntity source);
}
