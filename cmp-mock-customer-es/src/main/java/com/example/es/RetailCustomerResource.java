package com.example.es;

import com.example.es.model.RetailCustomer;
import com.example.es.service.CMPRetailService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class RetailCustomerResource {

    private Logger log = LogManager.getLogger(RetailCustomerResource.class);

    @Autowired
    public CMPRetailService cmpRetailService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/customer/{id}")
    public ResponseEntity<RetailCustomer> getCustomer(@PathVariable("id") Long customerID) {

        log.info("Get customerID {} ", customerID);
        return cmpRetailService.getCustomersById(customerID);

    }

    @PostMapping("/customer")
    private ResponseEntity<RetailCustomer> persistResponse(
            @Validated @RequestBody final RetailCustomer retailCustomer) {
        log.info("Posted new customer with id {} ", retailCustomer.getEsCustomerID());
       return cmpRetailService.create(retailCustomer);
    }
}