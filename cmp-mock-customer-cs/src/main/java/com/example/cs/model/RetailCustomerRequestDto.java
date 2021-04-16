package com.example.cs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RetailCustomerRequestDto {

    @JsonProperty("esCustomerID")
    private Long csCustomerID;

    @JsonProperty("esName")
    private String csName;

    @JsonProperty("esIsSenior")
    private Boolean csIsSenior;

}
