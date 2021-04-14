package com.example.cmp.service;

import com.example.cmp.model.CMPRetailCustomer;
import com.example.cmp.dao.CMPRetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
public class CMPRetailService implements CMPRetailServiceImpl{

    @Autowired
    public CMPRetailRepository cmpRetailRepository;

    @Transactional
    public Iterable<CMPRetailCustomer> getAllCustomers(){
        return cmpRetailRepository.findAll();
    }


    @Transactional
    public ResponseEntity getCustomersById(Long customerID) {
        if(cmpRetailRepository.findById(customerID).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(cmpRetailRepository.findById(customerID).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
        }
    }

    public ResponseEntity create(@Validated CMPRetailCustomer cmpRetailCustomer){
        cmpRetailRepository.save(cmpRetailCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(cmpRetailRepository.findById(cmpRetailCustomer.getEsCustomerID()).get());

    }

}
