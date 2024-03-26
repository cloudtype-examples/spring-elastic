package io.cloudtype.springelastic.service;

import io.cloudtype.springelastic.model.Movies;
import io.cloudtype.springelastic.repository.MoviesRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class MoviesServiceImpl implements MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesServiceImpl(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public List<Movies> getAll() {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                moviesRepository.findAll().iterator(), 0
                        ), false
                )
                .collect(Collectors.toList());
    }

    @Override
    public Movies add(Movies movie) {
        log.info("add: {} ", movie);
        return moviesRepository.save(movie);
    }

    @Override
    public Movies getById(String id) {
        return moviesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    @Override
    public Movies update(Movies movie, String id) {
        moviesRepository.findById(id)
                .ifPresentOrElse(movie_ -> {
                            movie_.setTitle(movie.getTitle());
                            movie_.setEnglishTitle(movie.getEnglishTitle());
                            movie_.setReleasedOn(movie.getReleasedOn());
                            movie_.setRunningTime(movie.getRunningTime());
                            movie_.setDirector(movie.getDirector());
                            movie_.setDistributor(movie.getDistributor());
                            movie_.setGenre(movie.getGenre());
                            movie_.setLanguage(movie.getLanguage());
                            moviesRepository.save(movie_);
                        }, () -> {
                            throw new ResourceNotFoundException("Not Found");
                        }
                );

        return movie;
    }

    @Override
    public void deleteById(String id) {
        moviesRepository.deleteById(id);
    }
}
