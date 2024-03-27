package io.cloudtype.springelastic.service;

import io.cloudtype.springelastic.model.Movies;
import io.cloudtype.springelastic.repository.MoviesCriteriaQueryRepository;
import io.cloudtype.springelastic.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {

    private final MoviesRepository moviesRepository;
    private final MoviesCriteriaQueryRepository moviesCriteriaQueryRepository;


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
    public List<Movies> getByTitle(String title) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                moviesRepository.findByTitle(title).iterator(), 0
                        ), false
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Movies> getByEnglishTitle(String englishTitle) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                moviesRepository.findByEnglishTitle(englishTitle).iterator(), 0
                        ), false
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Movies> getByDirector(String director) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                moviesRepository.findByDirector(director).iterator(), 0
                        ), false
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Movies> getByCriteriaQuery(Movies movie) {
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                moviesCriteriaQueryRepository.findByCriteriaQuery(movie).iterator(), 0
                        ), false
                )
                .collect(Collectors.toList());
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
