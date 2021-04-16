package com.example.cs;

import com.example.cs.model.RetailCustomer;
import com.example.cs.service.RetailServiceImpl;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cs")
public class RetailCustomerResource {

    @Autowired
    public RetailServiceImpl retailService;

    @GetMapping("/customer/{id}")
    private ResponseEntity<RetailCustomer> getCustomer(@PathVariable("id") Long customerID) {

        return retailService.getCustomersById(customerID);
    }

    @PostMapping("/customer")
    private ResponseEntity<RetailCustomer> persistResponse(
            @Validated @RequestBody final RetailCustomer retailCustomer) {

       return retailService.create(retailCustomer);
    }

}
