package org.poc.onlinemovieticket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Theatre {

    @Id
    private String id;
    private String name;
    private String address;
    private int numberOfScreens;
    private List<String> listOfScreenNames;
    private String contactInfo;


}
