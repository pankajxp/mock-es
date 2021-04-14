package com.example.cmp.service;

import com.example.cmp.model.CMPRetailCustomer;
import org.springframework.http.ResponseEntity;

public interface CMPRetailServiceImpl{

    ResponseEntity<CMPRetailCustomer> create(CMPRetailCustomer cmpRetailCustomer);

    ResponseEntity<CMPRetailCustomer> getCustomersById(Long customerID);

}
