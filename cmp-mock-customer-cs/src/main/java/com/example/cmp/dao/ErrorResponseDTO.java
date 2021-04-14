package com.example.cmp.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponseDTO {

    @JsonProperty("errors")
    private List<ErrorData> errors;
}
