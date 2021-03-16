package ar.com.ada.online.second.tpp.controller;

import ar.com.ada.online.second.tpp.model.dto.MoviesDTO;
import ar.com.ada.online.second.tpp.service.MoviesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ActorsController {
    @Autowired
    private MoviesServices moviesServices;



    @GetMapping({ "/", "" })

    public ResponseEntity getMoviesMethod() {
        // se llama al servicio y se le pide el listado de peliculas

        List<MoviesDTO> movies = moviesServices.getAll();

        // se crea el response request
        return ResponseEntity
                .ok()
                .body(movies);
    }

    @GetMapping({ "/{id}", "/{id}/" })

    public ResponseEntity getMoviesByIdMethod(@PathVariable Long id) {

        MoviesDTO byId = moviesServices.getById(id);

        return ResponseEntity
                .ok()
                .body(byId);
    }

    @PostMapping({ "/", " " })

    public ResponseEntity postmoviestMethod(@Valid @RequestBody MoviesDTO dto) throws URISyntaxException {
        MoviesDTO newMovies = moviesServices.createNew(dto);

        URI uri = new URI("/movies/" + newMovies.getId());

        return ResponseEntity
                .created(uri)
                .body(newMovies);
    }


    @DeleteMapping({ "{id}", "{id}/" })

    public ResponseEntity deleteMoviesByIdMethod(@PathVariable Long id) {

        moviesServices.remove(id);
        return ResponseEntity
                .noContent()
                .build();
    }


    @PatchMapping({ "/{id}", "/{id}/" })

    public ResponseEntity patchActorsByIdMethod(

            @RequestBody MoviesDTO dto,
            @PathVariable Long id
    ) {

        MoviesDTO moviesUpdated = moviesServices.update(dto, id);

        return ResponseEntity
                .ok()
                .body(moviesUpdated);
    }


}
