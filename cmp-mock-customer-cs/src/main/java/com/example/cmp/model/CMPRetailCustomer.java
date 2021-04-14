package com.example.cmp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CMPRetailCustomer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique ID for the customer")
    @JsonProperty
    private Long csCustomerID;

    @Column
    @ApiModelProperty(value = "The customers name")
    @JsonProperty
    private String csName;

    @Column
    @ApiModelProperty(value = "Identifics whether the customer is a senior citizen or not")
    @JsonProperty
    private Boolean csIsSenior;

    public CMPRetailCustomer(Long csCustomerID, String csName, Boolean csIsSenior) {
        this.csCustomerID = csCustomerID;
        this.csName = csName;
        this.csIsSenior = csIsSenior;
    }

    public CMPRetailCustomer(){}

    public CMPRetailCustomer(CMPRetailCustomer customer) {
        this.setCsCustomerID(customer.getCsCustomerID());
        this.setCsName(customer.getCsName());
        this.setCsIsSenior(customer.getCsIsSenior());
    }
}
