package org.poc.onlinemovieticket.handlers;

import org.poc.onlinemovieticket.entities.Show;
import org.poc.onlinemovieticket.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ShowHandler {

    @Autowired
    ShowRepository showRepository;

    public Mono<ServerResponse> createShow(ServerRequest request) {
        return request.bodyToMono(Show.class)
                .map(show -> {
                    show.setShowId(UUID.randomUUID().toString());
                    return show;
                })
                .flatMap(showRepository::save)
                .flatMap(savedShow -> ServerResponse.ok().bodyValue(savedShow))
                .onErrorResume(e -> ServerResponse.status(500).bodyValue("Failed to create show: " + e.getMessage()));
    }

    public Mono<ServerResponse> getShowById(ServerRequest request) {
        String showId = request.pathVariable("showId");
        return showRepository.findById(showId)
                .flatMap(show -> ServerResponse.ok().bodyValue(show))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllShows(ServerRequest request) {
        return ServerResponse.ok().body(showRepository.findAll(), Show.class);
    }

    public Mono<ServerResponse> getShowsByScreenId(ServerRequest request) {
        String screenId = request.pathVariable("screenId");
        return ServerResponse.ok().body(showRepository.findByScreenId(screenId), Show.class);
    }

    public Mono<ServerResponse> getShowsByMovieId(ServerRequest request) {
        String movieId = request.pathVariable("movieId");
        return ServerResponse.ok().body(showRepository.findByMovieId(movieId), Show.class);
    }

    public Mono<ServerResponse> deleteShowById(ServerRequest request) {
        String showId = request.pathVariable("showId");
        return showRepository.deleteById(showId)
                .then(ServerResponse.ok().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
