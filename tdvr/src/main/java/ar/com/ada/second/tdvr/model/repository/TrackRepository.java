package ar.com.ada.second.tdvr.model.repository;

import ar.com.ada.second.tdvr.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sound.midi.Track;


@Repository
public interface TrackRepository extends JpaRepository<Track,Long> {
}