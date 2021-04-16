package com.example.cs;

import com.example.cs.model.RetailCustomer;
import com.example.cs.model.RetailCustomerDTO;
import org.apache.http.client.config.RequestConfig;
import com.example.cs.service.RetailServiceImpl;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/cs")
public class RetailCustomerResource {

    private Logger log = LogManager.getLogger(RetailCustomerResource.class);

    @Autowired
    public RetailServiceImpl retailService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${es.url}")
    String url;

    @GetMapping("/customer/{id}")
    private ResponseEntity<RetailCustomer> getCustomer(@PathVariable("id") Long customerID) {


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url + "/" + customerID, HttpMethod.GET, entity, RetailCustomer.class);

        //return retailService.getCustomersById(customerID);
    }

    @PostMapping("/customer")
    private ResponseEntity<RetailCustomer> persistResponse(
            @Validated @RequestBody final RetailCustomer retailCustomer) {

        HttpEntity<RetailCustomer> entity = new HttpEntity<>(new RetailCustomer(retailCustomer));

        //return restTemplate.exchange(url, HttpMethod.POST, entity, RetailCustomer.class);

        return restTemplate.postForEntity(url, entity, RetailCustomer.class);
       //return retailService.create(retailCustomer);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
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
