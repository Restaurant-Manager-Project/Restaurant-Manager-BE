package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.ClientResponse;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.RankEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper extends BaseResponseMapper<ClientResponse, ClientEntity> {
    @Mapping(target = "rankName", source = "rank", qualifiedByName = "mapRank")
    ClientResponse toDto(ClientEntity entity);

    @Named("mapRank")
    default String mapRankName(RankEntity rank) {
        return rank.getName();
    }
}
