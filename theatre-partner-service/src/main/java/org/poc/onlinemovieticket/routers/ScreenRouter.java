package org.poc.onlinemovieticket.routers;

import org.poc.onlinemovieticket.handlers.ScreenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ScreenRouter {

    @Bean
    public RouterFunction<ServerResponse> screenRoutes(ScreenHandler screenHandler) {
        return route(POST("/theatre-service/v1/screens"), screenHandler::createScreen)
                .andRoute(GET("/theatre-service/v1/screens/{screenId}"), screenHandler::getScreenById)
                .andRoute(GET("/theatre-service/v1/screens/theatre/{theatreId}"), screenHandler::getScreensByTheatreId)
                .andRoute(PUT("/theatre-service/v1/screens/{screenId}"), screenHandler::updateScreen)
                .andRoute(DELETE("/theatre-service/v1/screens/{screenId}"), screenHandler::deleteScreenById);
    }
}

