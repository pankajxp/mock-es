package com.example.cmp.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class ErrorData {

    @JsonProperty("code")
    private String httpStatusCode;

    @JsonProperty("reasonCode")
    private String reasonCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("additionalInformation")
    private AdditionalInfo additionalInfo;

    public ErrorData(String httpStatusCode, String reasonCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.reasonCode = reasonCode;
        this.message = message;
    }

    public ErrorData(Errors error) {
        this.httpStatusCode = error.getHttpStatusCode();
        this.reasonCode = error.getReasonCode();
        this.message = error.getMessage();
        this.additionalInfo = new AdditionalInfo(error.getNarrative());
    }
}
