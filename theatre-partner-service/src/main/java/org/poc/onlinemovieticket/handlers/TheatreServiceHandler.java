package org.poc.onlinemovieticket.handlers;

import org.poc.onlinemovieticket.entities.Theatre;
import org.poc.onlinemovieticket.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TheatreServiceHandler {

    @Autowired
    TheatreRepository theatreRepository;

    public Mono<ServerResponse> onboardTheatre(ServerRequest request) {
        return request.bodyToMono(Theatre.class)
                .map(theatre -> {
                    theatre.setId(UUID.randomUUID().toString());
                    return theatre;
                })
                .flatMap(theatreRepository::save)
                .flatMap(theatre -> ServerResponse.ok().bodyValue(theatre))
                .switchIfEmpty(ServerResponse.badRequest().build())
                .onErrorResume(e-> ServerResponse.status(500).bodyValue("Failed to onboard Theatre" + e.getMessage()));
    }

    public Mono<ServerResponse> getTheatreById(ServerRequest request) {
        String id = request.pathVariable("id");
        return theatreRepository.findById(id)
                .flatMap(theatre -> ServerResponse.ok().bodyValue(theatre))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllTheatres(ServerRequest request) {
        return ServerResponse.ok().body(theatreRepository.findAll(), Theatre.class);
    }

    public Mono<ServerResponse> updateTheatre(ServerRequest request) {
        String theatreId = request.pathVariable("id");
        return theatreRepository.findById(theatreId)
                .flatMap(existingTheatre -> request.bodyToMono(Theatre.class)
                        .flatMap(theatre -> {
                            existingTheatre.setName(theatre.getName());
                            existingTheatre.setAddress(theatre.getAddress());
                            existingTheatre.setNumberOfScreens(theatre.getNumberOfScreens());
                            existingTheatre.setListOfScreenNames(theatre.getListOfScreenNames());
                            existingTheatre.setContactInfo(theatre.getContactInfo());
                            return theatreRepository.save(existingTheatre);
                        }))
                .flatMap(updatedTheatre -> ServerResponse.ok().bodyValue(updatedTheatre))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteTheatre(ServerRequest request) {
        String theatreId = request.pathVariable("id");
        return theatreRepository.findById(theatreId)
                .flatMap(existingTheatre -> theatreRepository.delete(existingTheatre)
                        .then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
