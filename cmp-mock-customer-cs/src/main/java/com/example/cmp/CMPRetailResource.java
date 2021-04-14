package com.example.cmp;

import com.example.cmp.model.CMPRetailCustomer;
import com.example.cmp.service.CMPRetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CMPRetailResource {

    @Autowired
    public CMPRetailService cmpRetailService;

    @GetMapping("/customer/{id}")
    private ResponseEntity<CMPRetailCustomer> getCustomer(@PathVariable("id") Long customerID) {
        return cmpRetailService.getCustomersById(customerID);
    }

    @PostMapping("/customer")
    private ResponseEntity<CMPRetailCustomer> persistResponse(
            @Validated @RequestBody final CMPRetailCustomer cmpRetailCustomer) {
       return cmpRetailService.create(cmpRetailCustomer);
    }
}
