package com.eze.catalogue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private int id;
    private String name;
    private String language;
    private String type;
    private String description;
    private double hours;
    private double rating;

}
