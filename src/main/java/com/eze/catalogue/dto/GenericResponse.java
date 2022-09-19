package com.eze.catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {

    private int statusCode;
    private String description;
    private Object data;
    private Object errors;

}
