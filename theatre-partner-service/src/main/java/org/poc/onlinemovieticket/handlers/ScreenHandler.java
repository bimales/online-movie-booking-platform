package org.poc.onlinemovieticket.handlers;

import org.poc.onlinemovieticket.entities.Screen;
import org.poc.onlinemovieticket.repositories.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ScreenHandler {

    @Autowired
    ScreenRepository screenRepository;

    public Mono<ServerResponse> createScreen(ServerRequest request) {
        return request.bodyToMono(Screen.class)
                .map(screen -> {
                    screen.setScreenId(UUID.randomUUID().toString());
                    return screen;
                })
                .flatMap(screenRepository::save)
                .flatMap(savedScreen -> ServerResponse.ok().bodyValue(savedScreen))
                .onErrorResume(e -> ServerResponse.status(500).bodyValue("Failed to create screen: " + e.getMessage()));
    }

    public Mono<ServerResponse> getScreenById(ServerRequest request) {
        String screenId = request.pathVariable("screenId");
        return screenRepository.findById(screenId)
                .flatMap(screen -> ServerResponse.ok().bodyValue(screen))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getScreensByTheatreId(ServerRequest request) {
        String theatreId = request.pathVariable("theatreId");
        return ServerResponse.ok().body(screenRepository.findByTheatreId(theatreId), Screen.class);
    }

    public Mono<ServerResponse> updateScreen(ServerRequest request) {
        String screenId = request.pathVariable("screenId");
        return screenRepository.findById(screenId)
                .flatMap(existingScreen -> request.bodyToMono(Screen.class)
                        .flatMap(screen -> {
                            screen.setScreenName(existingScreen.getScreenName());
                            screen.setSeatingCapacity(existingScreen.getSeatingCapacity());
                            return screenRepository.save(screen);
                        })
                        .flatMap(updatedScreen -> ServerResponse.ok().bodyValue(updatedScreen))
                        .switchIfEmpty(ServerResponse.notFound().build())
                );
    }

    public Mono<ServerResponse> deleteScreenById(ServerRequest request) {
        String screenId = request.pathVariable("screenId");
        return screenRepository.deleteById(screenId)
                .then(ServerResponse.ok().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
