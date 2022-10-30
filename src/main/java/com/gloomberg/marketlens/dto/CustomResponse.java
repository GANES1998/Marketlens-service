package com.gloomberg.marketlens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder(value = {"data", "error", "errorData"})
public class CustomResponse<T> {

    @JsonProperty("data")
    T data;

    @JsonProperty("error")
    boolean error;

    @JsonProperty("errorData")
    String errorData;

}
