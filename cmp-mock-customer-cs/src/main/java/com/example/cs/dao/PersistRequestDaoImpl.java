package com.example.cs.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Component
public class PersistRequestDaoImpl implements PersistRequestDao{

    private static final Logger logger = LogManager.getLogger(PersistRequestDaoImpl.class);

    private String persistanceUrl;

    private String contextPath;

    private RestTemplate appRestClient;

    @Override
    public void persistRequest(Map<String, String> headers, String reqBody) {
        try {
            PersistRequestPayloadDTO requestDTO = new PersistRequestPayloadDTO(headers, reqBody, contextPath);
            HttpEntity<PersistRequestPayloadDTO> request = new HttpEntity<>(requestDTO, getHttpHeaders(headers));
            ResponseEntity<ErrorResponseDTO> responseDTO = appRestClient.exchange(persistanceUrl, HttpMethod.POST, request, ErrorResponseDTO.class);
            HttpStatus statusCode = responseDTO.getStatusCode();
            logger.debug("Response Received from persistence api With Status Code: " + statusCode);
        } catch (HttpStatusCodeException e) {
            String errMessage = e.getResponseBodyAsString();
            int statusCode = e.getStatusCode().value();
            logger.error(String.format("Persistence API failure occured for request headers %s and request body %s : error message %s ", headers, reqBody, errMessage), e);
        }
    }

    private HttpHeaders getHttpHeaders(Map<String, String> headers) {
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        return reqHeaders;

    }

    public void setPersistanceUrl(String persistanceUrl) {
        this.persistanceUrl = persistanceUrl;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setAppRestClient(RestTemplate appRestClient) {
        this.appRestClient = appRestClient;
    }
}
