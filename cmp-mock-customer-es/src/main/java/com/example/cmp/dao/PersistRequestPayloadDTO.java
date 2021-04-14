package com.example.cmp.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersistRequestPayloadDTO {
    @JsonProperty("headers")
    private Object headers;

    @JsonProperty("body")
    private Object body;

    @JsonProperty("serviceName")
    private String serviceName;

    public PersistRequestPayloadDTO(Object headers, Object body, String serviceName) {
        this.headers = headers;
        this.body = body;
        this.serviceName = serviceName;
    }

    public PersistRequestPayloadDTO() {
    }
}
