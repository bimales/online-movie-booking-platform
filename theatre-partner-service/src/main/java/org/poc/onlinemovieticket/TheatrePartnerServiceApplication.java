package org.poc.onlinemovieticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "org.poc.onlinemovieticket.repositories")
public class TheatrePartnerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheatrePartnerServiceApplication.class, args);
    }
}
