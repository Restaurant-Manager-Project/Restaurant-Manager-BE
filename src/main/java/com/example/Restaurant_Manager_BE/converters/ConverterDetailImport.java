package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.DetailsImportDTO;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ConverterDetailImport {
    List<ImportDTO> toDTOList(List<DetailsImportEntity> entityList);
}
