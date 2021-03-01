package ar.com.ada.second.tdvr.model.mapper;

import ar.com.ada.second.tdvr.model.dto.TrackDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.sound.midi.Track;

@Mapper(componentModel ="spring")
public interface TrackMapper extends DataMapper<TrackDTO, Track>{
    TrackMapper MAPPER = Mappers.getMapper(TrackMapper.class);

}
