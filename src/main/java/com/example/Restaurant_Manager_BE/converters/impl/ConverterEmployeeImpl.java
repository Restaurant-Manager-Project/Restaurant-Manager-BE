package com.example.Restaurant_Manager_BE.converters.impl;
import com.example.Restaurant_Manager_BE.converters.ConverterAccount;
import com.example.Restaurant_Manager_BE.converters.ConverterEmployee;
import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.dto.EmployeeDTO;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConverterEmployeeImpl implements ConverterEmployee {
    private final ConverterAccount converterAccount;
    @Override
    public EmployeeDTO toDTO(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        return EmployeeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .accountDTO(converterAccount.toDto(entity.getAccount()))
                .build();
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDTO dto) {
        if (dto ==  null) {
            return null;
        }
        EmployeeEntity entity = EmployeeEntity.builder()
                .phone(dto.getPhone())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .isDeleted(false)
                .build();
        entity.setAccount(converterAccount.toEntity(dto.getAccountDTO(),entity));
        return entity;
    }

    @Override
    public List<EmployeeDTO> toDTOList(List<EmployeeEntity> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        List<EmployeeDTO> list = new ArrayList<>();
        entityList.forEach(x -> {
            EmployeeDTO dto = toDTO(x);
            list.add(dto);
        });
        return list;
    }

    @Override
    public List<EmployeeEntity> toEntityList(List<EmployeeDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        List<EmployeeEntity> list = new ArrayList<>();
        dtoList.forEach(x -> {
            EmployeeEntity entity = toEntity(x);
            list.add(entity);
        });
        return list;
    }

    @Override
    public void mergeNonNullFields(EmployeeEntity target, EmployeeEntity source) {
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
