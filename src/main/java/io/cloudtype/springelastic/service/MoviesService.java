package io.cloudtype.springelastic.service;

import io.cloudtype.springelastic.model.Movies;

import java.util.List;

public interface MoviesService {

    public List<Movies> getAll();

    public Movies add(Movies movie);

    public Movies getById(String id);

    public Movies update(Movies movie, String id);

    public void deleteById(String id);
}
