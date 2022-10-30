package com.gloomberg.marketlens.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gloomberg.marketlens.entity.id.StockId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@IdClass(StockId.class)
public class Stock {

    @Id
    @JsonProperty("symbol")
    String symbol;

    @Id
    @JsonProperty("day")
    @JsonFormat(pattern = "yyyy-MMM-dd")
    Date day;

    @JsonProperty
    Double open;

    @JsonProperty
    Double close;

    @JsonProperty
    Double low;

    @JsonProperty
    Double high;
}
