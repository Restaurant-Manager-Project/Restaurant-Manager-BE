package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterImport;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ConverterImportImpl implements ConverterImport {
    private final ModelMapper modelMapper;
    @Override
    public ImportDTO toDTO(ImportEntity entity) {
        return null;
    }

    @Override
    public ImportEntity toEntity(ImportDTO dto) {
        if (dto ==  null) {
            return null;
        }
        return modelMapper.map(dto, ImportEntity.class);
    }

    @Override
    public List<ImportDTO> toDTOList(List<ImportEntity> entityList) {
        return null;
    }

    @Override
    public List<ImportEntity> toEntityList(List<ImportDTO> dtoList) {
        return null;
    }

    @Override
    public ImportEntity mergeNonNullFields(ImportEntity target, ImportEntity source) {
        if (source == null) {
            return null;
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
        return target;
    }
}
