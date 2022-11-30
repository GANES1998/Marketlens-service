package com.gloomberg.marketlens.dto.variance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SectorVarianceResult {

    String sector;
    Integer year;
    Integer subyear;
    Double value;
    Double stdDev;

}
