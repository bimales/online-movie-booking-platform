package org.poc.moviecatalog.repositories;

import org.poc.moviecatalog.entities.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

    Flux<Movie> findByGenre(String genre);
    Flux<Movie> findByLanguage(String language);
    Flux<Movie> findByDurationLessThanEqual(int duration);
    Flux<Movie> findByTitleContaining(String title);
    Flux<Movie> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
}
