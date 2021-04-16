package com.example.es.service;

import com.example.es.model.RetailCustomer;
import com.example.es.dao.CMPRetailRepository;
import com.example.es.model.RetailCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CMPRetailService implements CMPRetailServiceImpl{

    @Autowired
    public CMPRetailRepository cmpRetailRepository;

    @Transactional
    public Iterable<RetailCustomer> getAllCustomers(){
        return cmpRetailRepository.findAll();
    }


    @Autowired
    public RestTemplate restTemplate;

    @Transactional
    public ResponseEntity getCustomersById(Long customerID) {
        if(cmpRetailRepository.findById(customerID).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(cmpRetailRepository.findById(customerID).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
        }
    }

    public ResponseEntity create(@Validated RetailCustomer retailCustomer){
        cmpRetailRepository.save(retailCustomer);
        if(cmpRetailRepository.findById(retailCustomer.getEsCustomerID()).isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cmpRetailRepository.findById(retailCustomer.getEsCustomerID()).get());

        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Adding Customer");
        }

    }

}
