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

@RestController
@RequestMapping(value = "movies")
public class MoviesController {

    @Autowired
    private MoviesServices moviesServices;

    @PostMapping({ "/actors/{actorsId}/movies", "/actors/{actorsId}/movies/" })

    public ResponseEntity postactorstMethod(
            @Valid @RequestBody MoviesDTO dto,
            @PathVariable Long actorsId) throws URISyntaxException {
        MoviesDTO moviesSaved = moviesServices.createNew(dto, actorsId);

        URI uri = new URI("/actors/" + moviesSaved.getId());

        return ResponseEntity
                .created(uri)
                .body(moviesSaved);
    }


    @GetMapping({ "/movies", "/movies/" })

    public ResponseEntity getMoviesMethod() {
        // se llama al servicio y se le pide el listado de albums

        List<MoviesDTO> movies = moviesServices.getAll();

        // se crea el response request
        return ResponseEntity
                .ok()
                .body(movies);
    }

    @GetMapping({ "/movies/{id}", "/movies/{id}/" })

    public ResponseEntity getMoviesByIdMethod(@PathVariable Long id) {

        MoviesDTO byId = moviesServices.getById(id);

        return ResponseEntity
                .ok()
                .body(byId);
    }


    @DeleteMapping({ "/movies/{id}", "/movies/{id}/" })

    public ResponseEntity deleteMoviesByIdMethod(@PathVariable Long id) {
        moviesServices.remove(id);
        return ResponseEntity
                .noContent()
                .build();
    }


    @PatchMapping({ "/actors/{actorsId}/movies/{moviesId}", "/actors/{actorsId}/movies/{moviesId}/" })

    public ResponseEntity patchActorsByIdMethod(

            @RequestBody MoviesDTO dto,
            @PathVariable Long actorstId,
            @PathVariable Long moviesId) {

        MoviesDTO moviesUpdated = moviesServices.update(dto, actorstId, moviesId);

        return ResponseEntity
                .ok()
                .body(moviesUpdated);
    }


}

