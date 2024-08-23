package org.poc.bookingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("bookings")
public class Booking {

    @Id
    @Column("booking_id")
    private String bookingId;

    @Column("user_id")
    private String userId;

    @Column("show_id")
    private String showId;

    @Column("theatre_id")
    private String theatreId;

    @Column("movie_id")
    private String movieId;

    @Column("seat_numbers")
    private String seatNumbers; // Store seat numbers as comma-separated values

    @Column("show_time")
    private LocalDateTime showTime;

    @Column("total_amount")
    private double totalAmount;

    @Column("discount_applied")
    private boolean isDiscountApplied;

    @Column("booking_time")
    private LocalDateTime bookingTime;
}
