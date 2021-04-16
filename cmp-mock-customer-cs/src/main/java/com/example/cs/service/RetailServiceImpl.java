package com.example.cs.service;

import com.example.cs.model.RetailCustomer;
import com.example.cs.dao.RetailRepository;
import com.example.cs.model.RetailCustomerDTO;
import com.example.cs.model.RetailCustomerRequestDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RetailServiceImpl implements RetailService {

    @Autowired
    public RetailRepository retailRepository;

//    @Transactional
//    public Iterable<RetailCustomer> getAllCustomers(){
//        return retailRepository.findAll();
//    }


    @Transactional
    public ResponseEntity getCustomersById(Long customerID) {

        RestTemplate restTemplate = new RestTemplate();
        String esUrl="http://localhost:8080/api/customer/1";

        if(retailRepository.findById(customerID).isPresent()){

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("gateway-client", null);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<RetailCustomerDTO> response = restTemplate.exchange(esUrl, HttpMethod.GET, entity, RetailCustomerDTO.class);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
        }
    }

    public ResponseEntity create(@Validated RetailCustomer retailCustomer){
        retailRepository.save(retailCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(retailRepository.findById(retailCustomer.getCsCustomerID()).get());

    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

}
