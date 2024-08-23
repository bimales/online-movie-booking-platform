package org.poc.onlinemovieticket.routers;

import org.poc.onlinemovieticket.handlers.TheatreServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class TheatreServiceRouter {

    @Autowired
    TheatreServiceHandler theatreServiceHandler;

    @Bean
    public RouterFunction<ServerResponse> theatreServiceRoute(){
        return RouterFunctions.route()
                .POST("/theatre-service/v1/theatres", theatreServiceHandler::onboardTheatre)
                .GET("/theatre-service/v1/theatres/{id}", theatreServiceHandler::getTheatreById)
                .PUT("/theatre-service/v1/theatres/{id}", theatreServiceHandler::updateTheatre)
                .GET("/theatre-service/v1/theatres", theatreServiceHandler::getAllTheatres)
                .DELETE("/theatre-service/v1/theatres/{id}", theatreServiceHandler::deleteTheatre)
                .build();

    }
}
