package ar.com.ada.second.tdvr.controller;

import ar.com.ada.second.tdvr.model.mapper.TrackMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public class TrackController {
    TrackMapper MAPPER = Mappers.getMapper(TrackMapper.class);
}
