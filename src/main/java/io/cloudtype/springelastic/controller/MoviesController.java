package io.cloudtype.springelastic.controller;

import io.cloudtype.springelastic.model.Movies;
import io.cloudtype.springelastic.service.MoviesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies() {
        return ResponseEntity.ok(moviesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movies> getMovieById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(moviesService.getById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<List<Movies>> getMoviesByTitle(@RequestParam("q") String title) {
        return ResponseEntity.ok().body(moviesService.getByTitle(title));
    }

    @GetMapping("/englishTitle")
    public ResponseEntity<List<Movies>> getMoviesByEnglishTitle(@RequestParam("q") String englishTitle) {
        return ResponseEntity.ok().body(moviesService.getByEnglishTitle(englishTitle));
    }

    @GetMapping("/director")
    public ResponseEntity<List<Movies>> getMoviesByDirector(@RequestParam("q") String director) {
        return ResponseEntity.ok().body(moviesService.getByDirector(director));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Movies>> getMoviesByCriteria(@RequestBody Movies movie) {
        return ResponseEntity.ok().body(moviesService.getByCriteriaQuery(movie));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movies addMovie(@RequestBody @Valid Movies movie) {
        return moviesService.add(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Movies> updateMovie(@RequestBody Movies movie, @PathVariable("id") String id) {
        Movies updatedMovie = moviesService.update(movie, id);
        return ResponseEntity.ok().body(updatedMovie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteMovieById(@PathVariable String id) {
        moviesService.deleteById(id);

        return ResponseEntity.ok().body("Deleted");
    }
}
