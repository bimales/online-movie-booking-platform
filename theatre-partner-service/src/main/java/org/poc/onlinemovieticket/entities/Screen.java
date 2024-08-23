package org.poc.onlinemovieticket.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "screens")
public class Screen {

    @Id
    private String screenId;  // UUID as a String
    private String theatreId; // Associated theatre ID
    private String screenName;
    private int seatingCapacity;
}
