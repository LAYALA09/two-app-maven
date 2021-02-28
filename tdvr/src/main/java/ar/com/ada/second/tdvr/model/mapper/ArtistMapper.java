package ar.com.ada.second.tdvr.model.mapper;

import ar.com.ada.second.tdvr.model.dto.ArtistDTO;
import ar.com.ada.second.tdvr.model.entity.Artist;

@Mapper(componentModel = "spring")
public interface ArtistMapper extends DataMapper<ArtistDTO, Artist> {
    ArtistMapper MAPPER = Mappers.getMapper(ArtistMapper.class);
}
