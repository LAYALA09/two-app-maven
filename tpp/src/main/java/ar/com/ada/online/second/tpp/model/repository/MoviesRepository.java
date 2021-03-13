package ar.com.ada.online.second.tpp.model.repository;

import ar.com.ada.online.second.tpp.model.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Long> {
}
