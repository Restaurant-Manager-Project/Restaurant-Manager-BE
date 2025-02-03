package com.example.Restaurant_Manager_BE.mapper;
//import lib here
import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import com.example.Restaurant_Manager_BE.entities.StatusTableEntity;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TableMapper extends BaseMapper<TableRequest, TableResponse, TableEntity> {

    
    @Mapping(target = "statusName", source = "statusTable", qualifiedByName = "mapStatusTable")
    TableResponse toDto(TableEntity entity);

    @Named("mapStatusTable")
    default String mapStatusTable(StatusTableEntity statusTableEntity) {
        return statusTableEntity.getName();
    }
    
   
    
}
