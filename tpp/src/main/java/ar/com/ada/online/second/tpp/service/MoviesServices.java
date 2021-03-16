package ar.com.ada.online.second.tpp.service;

import ar.com.ada.online.second.tpp.component.BusinessLogicExceptionComponent;
import ar.com.ada.online.second.tpp.model.dto.MoviesDTO;
import ar.com.ada.online.second.tpp.model.entity.Movies;
import ar.com.ada.online.second.tpp.model.mapper.AvoidingMappingContext;
import ar.com.ada.online.second.tpp.model.mapper.MoviesMapper;
import ar.com.ada.online.second.tpp.model.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServices implements Services<MoviesDTO, Movies> {
    private MoviesMapper movieMapper = MoviesMapper.MAPPER;
    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;
    @Autowired
    private AvoidingMappingContext context;
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public MoviesDTO createNew(MoviesDTO dto) {

        Movies movies = moviesMapper.toEntity(dto, context);

        moviesRepository.save(movies);

        MoviesDTO moviesSaved = moviesMapper.toDTO(movies, context);

        return moviesSaved;

    }

    @Override
    public List<MoviesDTO> getAll() {

        List<Movies> moviesList = moviesRepository.findAll();

        List<MoviesDTO> moviesDTOS = movieMapper.toDTO(moviesList, context);

        return moviesDTOS;

    }

    @Override

    public <MoviesDTO> getById(Long id) {
        Optional<Movies> moviesOptional = moviesRepository.findById(id);

        Movies movies = moviesOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Movies", id));

        MoviesDTO moviesDTO = moviesMapper.toDTO(movies, context);

        return moviesDTO;

    }

    @Override
    public MoviesDTO update(MoviesDTO dto, Long id) {
        // verifico si el id existe en la base de datos
        Optional<Movies> moviesOptional = moviesRepository.findById(id);

        Movies moviesById = moviesOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Movies", id));

        mergeData(moviesById, dto);

        moviesRepository.save(moviesById);

        MoviesDTO moviesUpdated = moviesMapper.toDTO(moviesById, context);

        return moviesUpdated
    }

    @Override
    public void remove(Long id) {
        Optional<Movies> artistByIdToDelete = moviesRepository.findById(id);

        Movies movies = moviesByIdToDelete
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Artist", id));

        moviesRepository.deleteById(id);
    }

    @Override
    public void mergeData(Movies entity, MoviesDTO dto) {
        if (dto.hasNullOrEmptyAttributes())
            throw logicExceptionComponent.getExceptionEntityEmptyValues("Movies");

        if (!entity.getName().equals(dto.getName()))
            entity.setName(dto.getName());
    }

    public MoviesDTO removeById(Long id) {
        Optional<Movies> moviesByIdToDelete = moviesRepository.findById(id);

        Movies movies = moviesByIdToDelete
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Movies", id));

        moviesRepository.delete(movies);

        MoviesDTO moviesDeleted = moviesMapper.toDTO(movies, context);

        return moviesDeleted;
    }
}

