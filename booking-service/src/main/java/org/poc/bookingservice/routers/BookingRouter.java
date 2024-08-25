package org.poc.bookingservice.routers;

import org.poc.bookingservice.handlers.BookingHandler;
import org.poc.bookingservice.handlers.BulkHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookingRouter {

    @Bean
    public RouterFunction<ServerResponse> bookingRoutes(BookingHandler bookingHandler, BulkHandler bulkHandler) {
        return RouterFunctions
                .nest(path("/booking-service/bookings"),
                        route(POST("/v1"), bookingHandler::createBooking)
                                .andRoute(GET("/v1/{id}"), bookingHandler::getBookingById)
                                .andRoute(GET("/v1/user/{userId}"), bookingHandler::getBookingsByUserId)
                                .andRoute(GET("/v1/theatres-running-show"), bookingHandler::getTheatresRunningShow)
                                .andRoute(POST("/v1/bulk-cancellation"), bulkHandler::bulkCancellation)
                );
    }
}
