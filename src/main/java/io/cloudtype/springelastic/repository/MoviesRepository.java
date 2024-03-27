package io.cloudtype.springelastic.repository;

import io.cloudtype.springelastic.model.Movies;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends ElasticsearchRepository<Movies, String> {
    
    List<Movies> findByTitle(String title);

    List<Movies> findByEnglishTitle(String englishTitle);

    List<Movies> findByDirector(String director);

}
