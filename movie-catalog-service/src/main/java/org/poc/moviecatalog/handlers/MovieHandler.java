package org.poc.moviecatalog.handlers;

import org.poc.moviecatalog.entities.Movie;
import org.poc.moviecatalog.repositories.MovieRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class MovieHandler {

    private final MovieRepository movieRepository;

    public MovieHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Mono<ServerResponse> getAllMovies(ServerRequest request) {
        return ServerResponse.ok().body(movieRepository.findAll(), Movie.class);
    }

    public Mono<ServerResponse> getMovieById(ServerRequest request) {
        String id = request.pathVariable("id");
        return movieRepository.findById(id)
                .flatMap(movie -> ServerResponse.ok().bodyValue(movie))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> searchMoviesByGenre(ServerRequest request) {
        String genre = request.queryParam("genre").orElse("");
        return ServerResponse.ok().body(movieRepository.findByGenre(genre), Movie.class);
    }

    public Mono<ServerResponse> searchMoviesByLanguage(ServerRequest request) {
        String language = request.queryParam("language").orElse("");
        return ServerResponse.ok().body(movieRepository.findByLanguage(language), Movie.class);
    }

    public Mono<ServerResponse> searchMoviesByDuration(ServerRequest request) {
        int duration = Integer.parseInt(request.queryParam("duration").orElse("0"));
        return ServerResponse.ok().body(movieRepository.findByDurationLessThanEqual(duration), Movie.class);
    }

    public Mono<ServerResponse> searchMoviesByTitle(ServerRequest request) {
        String title = request.queryParam("title").orElse("");
        return ServerResponse.ok().body(movieRepository.findByTitleContaining(title), Movie.class);
    }

    public Mono<ServerResponse> searchMoviesByReleaseDate(ServerRequest request) {
        LocalDate startDate = LocalDate.parse(request.queryParam("startDate").orElse("1900-01-01"));
        LocalDate endDate = LocalDate.parse(request.queryParam("endDate").orElse(LocalDate.now().toString()));
        return ServerResponse.ok().body(movieRepository.findByReleaseDateBetween(startDate, endDate), Movie.class);
    }

    public Mono<ServerResponse> addMovie(ServerRequest request) {
        return request.bodyToMono(Movie.class)
                .map(movie -> {
                    movie.setMovieId(UUID.randomUUID().toString());
                    return movie;
                })
                .flatMap(movieRepository::save)
                .flatMap(savedMovie -> ServerResponse.ok().bodyValue(savedMovie));
    }

    public Mono<ServerResponse> updateMovie(ServerRequest request) {
        String id = request.pathVariable("id");
        return movieRepository.findById(id)
                .flatMap(existingMovie -> request.bodyToMono(Movie.class)
                        .flatMap(updatedMovie -> {
                            updatedMovie.setMovieId(id);
                            return movieRepository.save(updatedMovie);
                        })
                )
                .flatMap(savedMovie -> ServerResponse.ok().bodyValue(savedMovie))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteMovie(ServerRequest request) {
        String id = request.pathVariable("id");
        return movieRepository.findById(id)
                .flatMap(existingMovie -> movieRepository.delete(existingMovie)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
