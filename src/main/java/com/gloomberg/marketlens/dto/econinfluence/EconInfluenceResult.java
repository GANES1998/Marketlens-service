package com.gloomberg.marketlens.dto.econinfluence;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EconInfluenceResult {

    String type;
    String symbol;
    Integer year;
    Integer subYear;
    Double value;


}
