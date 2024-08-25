package org.poc.bookingservice.handlers;

import org.poc.bookingservice.dtos.BulkBooking;
import org.poc.bookingservice.dtos.BulkCancellation;
import org.poc.bookingservice.entities.Booking;
import org.poc.bookingservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class BulkHandler {

    private final BookingRepository bookingRepository;

    public BulkHandler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public Mono<ServerResponse> bulkCancellation(ServerRequest request) {
        return request.bodyToMono(BulkCancellation.class)
                .flatMap(bulkCancellation -> {
                    return bookingRepository.deleteAllById(bulkCancellation.getListOfBookingIds());
                })
                .flatMap( e -> ServerResponse.ok().bodyValue("Bulk cancellation completed."))
                .onErrorResume(e -> ServerResponse.status(500).bodyValue("Failed " + e.getMessage()));

    }

    public Mono<ServerResponse> bulkBooking(ServerRequest request) {
        return request.bodyToMono(BulkBooking.class)
                .map(b -> b.getListOfBookings())
                .flatMap(this::saveBulkBookings)
                .flatMap(e -> ServerResponse.ok().bodyValue("Bulk cancellation completed."))
                .onErrorResume(e -> ServerResponse.status(500).bodyValue("Failed " + e.getMessage()));

    }


    private Mono<List<Booking>> saveBulkBookings(List<Booking> bulkBookings) {
        return bookingRepository.saveAll(bulkBookings).collectList();
    }
}
