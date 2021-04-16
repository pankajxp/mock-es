package com.example.cs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RetailCustomer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique ID for the customer")
    @JsonProperty("csCustomerID")
    private Long csCustomerID;

    @Column
    @ApiModelProperty(value = "The customers name")
    @JsonProperty("csName")
    private String csName;

    @Column
    @ApiModelProperty(value = "Identifics whether the customer is a senior citizen or not")
    @JsonProperty("csIsSenior")
    private Boolean csIsSenior;

    public RetailCustomer(Long csCustomerID, String csName, Boolean csIsSenior) {
        this.csCustomerID = csCustomerID;
        this.csName = csName;
        this.csIsSenior = csIsSenior;
    }

    public RetailCustomer(){}

    public RetailCustomer(RetailCustomer customer) {
        this.setCsCustomerID(customer.getCsCustomerID());
        this.setCsName(customer.getCsName());
        this.setCsIsSenior(customer.getCsIsSenior());
    }
}
