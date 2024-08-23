package org.poc.onlinemovieticket.repositories;

import org.poc.onlinemovieticket.entities.Show;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ShowRepository extends ReactiveMongoRepository<Show, String> {
    Flux<Show> findByScreenId(String screenId);
    Flux<Show> findByMovieId(String movieId);
}