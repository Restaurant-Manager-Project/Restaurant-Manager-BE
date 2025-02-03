package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConvertDetailsImport;
import com.example.Restaurant_Manager_BE.converters.ConverterImport;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.entities.SupplierEntity;
import com.example.Restaurant_Manager_BE.utils.FormatUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ConverterImportImpl implements ConverterImport {
//    private final ModelMapper modelMapper;
    private final ConvertDetailsImport convertDetailsImport;

    @Override
    public ImportDTO toDTO(ImportEntity entity) {
        if (entity == null) {
            return null;
        }
        return ImportDTO.builder()
                .id(entity.getId())
                .dateCreate(entity.getDateCreate())
                .total(entity.getTotal())
                .supplierId(entity.getSupplier().getId())
                .supplierName(entity.getSupplier().getName())
                .build();
    }

    @Override
    public ImportEntity toEntity(ImportDTO dto) {
        if (dto ==  null) {
            return null;
        }
        ImportEntity importEntity = ImportEntity.builder()
                .employee(EmployeeEntity.builder().id(dto.getEmployeeId()).build())
                .supplier(SupplierEntity.builder().id(dto.getSupplierId()).build())
                .dateCreate(dto.getDateCreate())
                .total(dto.getTotal())
                .build();
        importEntity.setDetailsProductList(convertDetailsImport.toEntityList(dto.getDetailsImportDTOList(), importEntity));
        return importEntity;
    }

    @Override
    public List<ImportDTO> toDTOList(List<ImportEntity> entityList) {
        if (entityList == null) {
            return null;
        }
        List<ImportDTO> importDTOList = new ArrayList<>();
        entityList.forEach(entity -> {
            importDTOList.add(toDTO(entity));
        });
        return importDTOList;
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
