package org.poc.bookingservice.repositories;

import org.poc.bookingservice.entities.Booking;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BookingRepository extends ReactiveCrudRepository<Booking, String> {

    Flux<Booking> findByUserId(String userId);

    @Query("SELECT * FROM bookings WHERE theatre_id = :theatreId AND show_time BETWEEN :start AND :end")
    Flux<Booking> findByTheatreIdAndShowTimeBetween(String theatreId, LocalDateTime start, LocalDateTime end);
}
