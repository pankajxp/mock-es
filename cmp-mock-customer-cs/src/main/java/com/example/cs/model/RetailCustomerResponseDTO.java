package com.example.cs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RetailCustomerResponseDTO {

    @Valid
    @NotNull
    @JsonProperty("esCustomerID")
    private Long esCustomerID;

    @JsonProperty("esName")
    private String esName;

    @JsonProperty("esIsSenior")
    private Boolean esIsSenior;

}
