package io.cloudtype.springelastic.repository;

import io.cloudtype.springelastic.model.Movies;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MoviesCriteriaQueryRepository {

    private final ElasticsearchOperations operations;

    public List<Movies> findByCriteriaQuery(Movies movie) {

        CriteriaQuery query = createCriteriaQuery(movie);

        SearchHits<Movies> search = operations.search(query, Movies.class);

        return search
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    private CriteriaQuery createCriteriaQuery(Movies movie) {

        CriteriaQuery query = new CriteriaQuery(new Criteria());

        if (movie == null) {
            query.addCriteria(Criteria.where("id").exists());
        }

        if (movie.getId() != null) {
            query.addCriteria(Criteria.where("id").is(movie.getId()));
        }

        if (StringUtils.hasText(movie.getTitle())) {
            query.addCriteria(Criteria.where("title").contains(movie.getTitle()));
        }

        if (StringUtils.hasText(movie.getEnglishTitle())) {
            query.addCriteria(Criteria.where("englishTitle").contains(movie.getEnglishTitle()));
        }

        if (StringUtils.hasText(movie.getDirector())) {
            query.addCriteria(Criteria.where("director").contains(movie.getDirector()));
        }

        return query;
    }
}
