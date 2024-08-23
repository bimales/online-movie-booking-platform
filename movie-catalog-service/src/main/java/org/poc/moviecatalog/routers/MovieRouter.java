package org.poc.moviecatalog.routers;

import org.poc.moviecatalog.handlers.MovieHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieRouter {

    @Bean
    public RouterFunction<ServerResponse> movieRoutes(MovieHandler movieHandler) {
        return RouterFunctions
                .nest(path("/movie-catalog-service"),
                                route(GET("/v1"), movieHandler::getAllMovies)
                                .andRoute(POST("/v1"), movieHandler::addMovie)
                                .andRoute(GET("/v1/{id}"), movieHandler::getMovieById)
                                .andRoute(PUT("/v1/{id}"), movieHandler::updateMovie)
                                .andRoute(DELETE("/v1/{id}"), movieHandler::deleteMovie)
                                .andRoute(GET("/v1/search/genre"), movieHandler::searchMoviesByGenre)
                                .andRoute(GET("/v1/search/language"), movieHandler::searchMoviesByLanguage)
                                .andRoute(GET("/v1/search/duration"), movieHandler::searchMoviesByDuration)
                                .andRoute(GET("/v1/search/title"), movieHandler::searchMoviesByTitle)
                                .andRoute(GET("/v1/search/release-date"), movieHandler::searchMoviesByReleaseDate)
                );
    }
}
