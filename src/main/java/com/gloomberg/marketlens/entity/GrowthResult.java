package com.gloomberg.marketlens.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@Data
public class GrowthResult implements Serializable {

    String symbol;
    Integer year;
    Integer subYear;
    Double value;
    Double percent;

}
