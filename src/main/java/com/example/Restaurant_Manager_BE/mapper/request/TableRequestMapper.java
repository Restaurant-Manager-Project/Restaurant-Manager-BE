package com.example.Restaurant_Manager_BE.mapper.request;
//import lib here
import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface TableRequestMapper extends BaseRequestMapper<TableRequest, TableEntity> {

    @Override
    @Mapping(target = "statusTable", source = "status")
    TableEntity toEntity(TableRequest dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "statusTable", source = "status")
    void update(@MappingTarget TableEntity entity, TableRequest dto);

}
