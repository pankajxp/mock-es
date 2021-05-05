package com.example.es.service;

import com.example.es.dao.RetailCustomerRepository;
import com.example.es.model.RetailCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RetailCustomerServiceImpl implements RetailCustomerService {

    @Autowired
    public RetailCustomerRepository retailCustomerRepository;

    @Transactional
    public Iterable<RetailCustomer> getAllCustomers() {
        return retailCustomerRepository.findAll();
    }

    @Autowired
    public RestTemplate restTemplate;

    private static List<RetailCustomer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();
        RetailCustomer retailCustomer = new RetailCustomer(1L, "Humaira", false);
        customers.add(retailCustomer);
    }

    public ResponseEntity getCustomersById(Long customerID) {
        if (customers.size() >= customerID - 1) {
            return ResponseEntity.status(HttpStatus.OK).body(customers.get(customerID.intValue() - 1));
            //            return ResponseEntity.status(HttpStatus.OK).body(retailCustomerRepository.findById(customerID).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
        }
    }

    public ResponseEntity create(@Validated RetailCustomer retailCustomer) {

        customers.add(retailCustomer);
        //        retailCustomerRepository.save(retailCustomer);
        if (retailCustomer != null) {
            customers.add(retailCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(retailCustomer);
            //            return ResponseEntity.status(HttpStatus.CREATED).body(retailCustomerRepository.findById(retailCustomer.getEsCustomerID()).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Adding Customer");
        }

    }

}
