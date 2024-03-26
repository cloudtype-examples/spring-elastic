package io.cloudtype.springelastic.repository;

import io.cloudtype.springelastic.model.Movies;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends ElasticsearchRepository<Movies, String> {
}
