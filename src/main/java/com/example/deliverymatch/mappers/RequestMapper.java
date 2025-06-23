package com.example.deliverymatch.mappers;

import com.example.deliverymatch.dtos.RequestDTO;
import com.example.deliverymatch.entities.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(target = "announcementId", source = "announcement.id")
    RequestDTO toDTO(Request request);
    Request toEntity(RequestDTO requestDTO);
    List<RequestDTO> toDTOs(List<Request> requests);
}
