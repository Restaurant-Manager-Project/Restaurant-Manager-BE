package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import com.example.Restaurant_Manager_BE.enums.StatusTable;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
@Mapper(componentModel = "spring")
public interface TableResponseMapper extends BaseResponseMapper<TableResponse, TableEntity> {
    @Mapping(target = "statusName", source = "statusTable", qualifiedByName = "mapStatusTable")
    TableResponse toDto(TableEntity entity);

    @Named("mapStatusTable")
    default String mapStatusTable(StatusTable status) {
        return status.getDesc();
    }
}
