package com.example.cs.service;

import com.example.cs.RetailCustomerResource;
import com.example.cs.model.RetailCustomer;
import com.example.cs.dao.RetailRepository;
import com.example.cs.model.RetailCustomerDTO;
import com.example.cs.model.RetailCustomerMapper;
import com.example.cs.model.RetailCustomerResponseDTO;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;

@Service
public class RetailServiceImpl implements RetailService {

    //private Logger log = LogManager.getLogger(RetailCustomerResource.class);

    @Autowired
    public RetailRepository retailRepository;

    @Value("${es.url}")
    String url;

    @Autowired
    RestTemplate restTemplate;

    @Transactional
    public ResponseEntity getCustomersById(Long customerID) {

        RestTemplate restTemplate = new RestTemplate();

        if(retailRepository.findById(customerID).isPresent()){

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);

           // log.info("Get Customer with ID {}", customerID);

            ResponseEntity<RetailCustomerDTO> response = restTemplate.exchange(url + "/" + customerID, HttpMethod.GET, entity, RetailCustomerDTO.class);

            RetailCustomerMapper mapper = new RetailCustomerMapper();
            RetailCustomer customer = mapper.mapCustomerDTO(response.getBody());

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
        }
    }

    public ResponseEntity create(@Validated RetailCustomer retailCustomer){


        retailRepository.save(retailCustomer);

        RetailCustomerMapper mapper = new RetailCustomerMapper();
        RetailCustomerResponseDTO esCustomer = mapper.mapCustomerToEsDTO(retailCustomer);

        HttpEntity<RetailCustomerResponseDTO> entity = new HttpEntity<>(esCustomer);
        HttpEntity<RetailCustomerResponseDTO> customer = restTemplate.postForEntity(url, entity, RetailCustomerResponseDTO.class);
        //log.info("Creating New Customer with ID {}", customer.getBody().getEsCustomerID());
        return getCustomersById(customer.getBody().getEsCustomerID());
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.setConnectTimeout(Duration.ofSeconds(1)).setReadTimeout(Duration.ofSeconds(1)).build();
        restTemplate.setRequestFactory(initAndGetRequestFactory());
        return builder.build();
    }

    private ClientHttpRequestFactory initAndGetRequestFactory() {
        RequestConfig requestConfig = RequestConfig.custom().build();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().useSystemProperties().setDefaultRequestConfig(requestConfig);

        return new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
    }
}
