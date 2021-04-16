package com.example.es.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RetailCustomerDTO {

    @JsonProperty("esCustomerID")
    private Long esCustomerID;

    @JsonProperty("esName")
    private String esName;

    @JsonProperty("esIsSenior")
    private Boolean esIsSenior;

}
