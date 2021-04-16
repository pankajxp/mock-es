package com.example.cs.model;

public class RetailCustomerMapper {

    public RetailCustomer mapCustomerDTO(RetailCustomerDTO retailCustomerDTO){
        RetailCustomer retailCustomer = new RetailCustomer();

        retailCustomer.setCsCustomerID(retailCustomerDTO.getCsCustomerID());
        retailCustomer.setCsName(retailCustomerDTO.getCsName());
        retailCustomer.setCsIsSenior(retailCustomerDTO.getCsIsSenior());

        return retailCustomer;
    }

    public RetailCustomerResponseDTO mapCustomerToEsDTO(RetailCustomer retailCustomer){
        RetailCustomerResponseDTO retailCustomerDto = new RetailCustomerResponseDTO();

        retailCustomerDto.setEsCustomerID(retailCustomer.getCsCustomerID());
        retailCustomerDto.setEsName(retailCustomer.getCsName());
        retailCustomerDto.setEsIsSenior(retailCustomer.getCsIsSenior());

        return retailCustomerDto;
    }
}
