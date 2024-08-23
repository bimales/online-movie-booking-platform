package org.poc.onlinemovieticket.routers;

import org.poc.onlinemovieticket.handlers.ShowHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ShowRouter {

    @Bean
    public RouterFunction<ServerResponse> showRoutes(ShowHandler showHandler) {
        return route(POST("/theatre-service/v1/shows"), showHandler::createShow)
                .andRoute(GET("/theatre-service/v1/shows"), showHandler::getAllShows)
                .andRoute(GET("/theatre-service/v1/shows/{showId}"), showHandler::getShowById)
                .andRoute(GET("/theatre-service/v1/shows/screen/{screenId}"), showHandler::getShowsByScreenId)
                .andRoute(GET("/theatre-service/v1/shows/movie/{movieId}"), showHandler::getShowsByMovieId)
                .andRoute(DELETE("/theatre-service/v1/shows/{showId}"), showHandler::deleteShowById);
    }
}
