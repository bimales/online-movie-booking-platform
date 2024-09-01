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
    private UUID bookingId;

    @Column("user_id")
    private UUID userId;

    @Column("show_id")
    private UUID showId;

    @Column("theatre_id")
    private UUID theatreId;

    @Column("screen_id")
    private UUID screenId;

    @Column("movie_id")
    private UUID movieId;

    @Column("seat_numbers")
    private String seatNumbers; // Store seat numbers as comma-separated values

    @Column("show_time")
    private LocalDateTime showTime;

    @Column("total_amount")
    private double totalAmount;

    @Column("discount_applied")
    private boolean isDiscountApplied;

    @Column("payment_status")
    private String paymentStatus;

    @Column("booking_time")
    private LocalDateTime bookingTime;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
