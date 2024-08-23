package org.poc.onlinemovieticket.repositories;

import org.poc.onlinemovieticket.entities.Screen;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ScreenRepository extends ReactiveMongoRepository<Screen, String> {
    Flux<Screen> findByTheatreId(String theatreId);
}
