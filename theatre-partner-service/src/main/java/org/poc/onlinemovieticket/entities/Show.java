package org.poc.onlinemovieticket.entities;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shows")
public class Show {

    @Id
    private String showId;  // UUID as a String
    private String screenId;
    private String movieId;
    private LocalDate startTime;
    private LocalDate endTime;
    private double pricePerSeat;
}
