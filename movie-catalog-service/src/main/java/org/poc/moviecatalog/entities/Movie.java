package org.poc.moviecatalog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")
public class Movie {

    @Id
    private String movieId;  // UUID as a String
    private String title;
    private String genre;
    private String language;
    private int duration; // in minutes
    private double rating; // e.g., 8.5
    private String description;
    private LocalDate releaseDate;
}
