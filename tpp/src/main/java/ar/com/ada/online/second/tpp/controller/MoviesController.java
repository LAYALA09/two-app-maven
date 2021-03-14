package ar.com.ada.online.second.tpp.controller;

import ar.com.ada.online.second.tpp.model.dto.MoviesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

public class MoviesController {
    @Autowired
    private MoviesServices moviesServices;

    @PostMapping({ "/actors/{actorsId}/movies", "/actors/{actorsId}/movies/" })

    public ResponseEntity postactorstMethod(
            @Valid @RequestBody MoviesDTO dto,
            @PathVariable Long actorsId) throws URISyntaxException {
        MoviesDTO moviesSaved = moviesServices.createNew(dto, artistId);

        URI uri = new URI("/actors/" + moviesSaved.getId());

        return ResponseEntity
                .created(uri)
                .body(moviesSaved);
    }


    @GetMapping({ "/movies", "/movies/" })

    public ResponseEntity getAlbumsMethod() {
        // se llama al servicio y se le pide el listado de albums

        List<AlbumDTO> albums = albumServices.getAll();

        // se crea el response request
        return ResponseEntity
                .ok()
                .body(albums);
    }

    @GetMapping({ "/albums/{id}", "/albums/{id}/" })

    public ResponseEntity getAlbumByIdMethod(@PathVariable Long id) {

        AlbumDTO byId = albumServices.getById(id);

        return ResponseEntity
                .ok()
                .body(byId);
    }


    @DeleteMapping({ "/albums/{id}", "/albums/{id}/" })

    public ResponseEntity deleteAlbumByIdMethod(@PathVariable Long id) {
        albumServices.remove(id);
        return ResponseEntity
                .noContent()
                .build();
    }


    @PatchMapping({ "/artists/{artistId}/albums/{albumId}", "/artists/{artistId}/albums/{albumId}/" })

    public ResponseEntity patchArtistByIdMethod(

            @RequestBody AlbumDTO dto,
            @PathVariable Long artistId,
            @PathVariable Long albumId) {

        MoviesDTO albumUpdated = albumServices.update(dto, artistId, albumId);

        return ResponseEntity
                .ok()
                .body(albumUpdated);
    }


}

