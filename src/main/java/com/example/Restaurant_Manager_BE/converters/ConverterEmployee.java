package com.example.Restaurant_Manager_BE.converters;
import com.example.Restaurant_Manager_BE.dto.EmployeeDTO;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import java.util.List;

public interface ConverterEmployee {
    EmployeeDTO toDTO(EmployeeEntity entity);

    EmployeeEntity toEntity(EmployeeDTO dto);

    List<EmployeeDTO> toDTOList(List<EmployeeEntity> entityList);

    List<EmployeeEntity> toEntityList(List<EmployeeDTO> dtoList);

    void mergeNonNullFields(EmployeeEntity target,EmployeeEntity source);
}
