package org.poc.moviecatalog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "org.poc.moviecatalog.repositories")
public class MovieCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogApplication.class, args);
    }
}
