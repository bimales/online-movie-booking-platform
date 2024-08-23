package org.poc.onlinemovieticket.repositories;

import org.poc.onlinemovieticket.entities.Theatre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface TheatreRepository extends ReactiveMongoRepository<Theatre, String> {
}
