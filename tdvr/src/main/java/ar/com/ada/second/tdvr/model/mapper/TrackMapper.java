package ar.com.ada.second.tdvr.model.mapper;

import ar.com.ada.second.tdvr.model.dto.TrackDTO;

import javax.sound.midi.Track;

@Mapper(componentModel ="spring")
public interface TrackMapper extends DataMapper<TrackDTO, Track>{
    TrackMapper MAPPER = Mappers.getMapper(TrackMapper.class);

}
