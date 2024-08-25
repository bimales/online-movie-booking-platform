package org.poc.bookingservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.poc.bookingservice.entities.Booking;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkBooking {

    private List<Booking> listOfBookings;
}
