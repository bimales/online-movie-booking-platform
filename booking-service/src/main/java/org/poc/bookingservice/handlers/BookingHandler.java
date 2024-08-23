package org.poc.bookingservice.handlers;

import org.poc.bookingservice.entities.Booking;
import org.poc.bookingservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BookingHandler {

    private final BookingRepository bookingRepository;

    public BookingHandler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Mono<ServerResponse> createBooking(ServerRequest request) {
        return request.bodyToMono(Booking.class)
                .map(this::applyDiscounts)
                .flatMap(booking -> {
                    booking.setBookingId(UUID.randomUUID().toString());
                    booking.setBookingTime(LocalDateTime.now());
                    return bookingRepository.save(booking);
                })
                .flatMap(savedBooking -> ServerResponse.ok().bodyValue(savedBooking));
    }

    public Mono<ServerResponse> getBookingById(ServerRequest request) {
        String id = request.pathVariable("id");
        return bookingRepository.findById(id)
                .flatMap(booking -> ServerResponse.ok().bodyValue(booking))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getBookingsByUserId(ServerRequest request) {
        String userId = request.pathVariable("userId");
        return ServerResponse.ok().body(bookingRepository.findByUserId(userId), Booking.class);
    }

    public Mono<ServerResponse> getTheatresRunningShow(ServerRequest request) {
        String theatreId = request.queryParam("theatreId").orElse("");
        LocalDateTime startTime = LocalDateTime.parse(request.queryParam("startDate").orElse(LocalDateTime.now().toString()));
        LocalDateTime endTime = LocalDateTime.parse(request.queryParam("endDate").orElse(LocalDateTime.now().plusDays(1).toString()));

        return ServerResponse.ok().body(
                bookingRepository.findByTheatreIdAndShowTimeBetween(theatreId, startTime, endTime), Booking.class);
    }

    private Booking applyDiscounts(Booking booking) {
        String[] seatNumbersArray = booking.getSeatNumbers().split(",");
        int seatCount = seatNumbersArray.length;
        double pricePerSeat = booking.getTotalAmount() / seatCount;

        // Apply 50% discount on the third ticket
        if (seatCount >= 3) {
            booking.setTotalAmount(booking.getTotalAmount() - (pricePerSeat * 0.5));
            booking.setDiscountApplied(true);
        }

        // Apply 50% discount if the show is an afternoon show
        if (booking.getShowTime().getHour() >= 12 && booking.getShowTime().getHour() < 17) {
            booking.setTotalAmount(booking.getTotalAmount() * 0.5);
            booking.setDiscountApplied(true);
        }

        // Re-serialize the seat numbers list
        booking.setSeatNumbers(Stream.of(seatNumbersArray).collect(Collectors.joining(",")));
        return booking;
    }
}
