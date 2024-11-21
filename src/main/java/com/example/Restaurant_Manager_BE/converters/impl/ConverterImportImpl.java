package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterImport;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.example.Restaurant_Manager_BE.converters.ConverterDetailImport;
import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ConverterImportImpl implements ConverterImport {
    private final ModelMapper modelMapper;
    private final ConverterDetailImport converterDetailImport;
    @Override
    public ImportDTO toDTO(ImportEntity entity) {
        if(entity == null){
            return null;
        }
        return ImportDTO.builder()
                .id(entity.getId())
                .total(entity.getTotal())
                .employeeId(entity.getEmployee().getId())
                .employeeName(entity.getEmployee().getFirstName()+entity.getEmployee().getLastName())
                .supplierId(entity.getSupplier().getId())
                .supplierName(entity.getSupplier().getName())
                .dateCreate(entity.getDateCreate())
                .total(entity.getTotal())
//                .detailsImportDTOList(converterDetailImport.toDTOList(entity.getDetailsProductList()))
                .build();
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
    public void mergeNonNullFields(ImportEntity target, ImportEntity source) {
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
