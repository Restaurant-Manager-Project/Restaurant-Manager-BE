package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterDetailImport;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDetailImportImpl implements ConverterDetailImport {
    @Override
    public List<ImportDTO> toDTOList(List<DetailsImportEntity> entityList){
        List<ImportDTO> dtoList = new ArrayList<ImportDTO>();
        for (DetailsImportEntity e : entityList) {
            ImportDTO dto = new ImportDTO();
        }
        return dtoList;
    }

}
