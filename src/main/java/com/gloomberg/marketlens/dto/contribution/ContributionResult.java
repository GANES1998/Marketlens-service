package com.gloomberg.marketlens.dto.contribution;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContributionResult {

    String symbol;
    Integer year;
    Integer subYear;
    Double marketCap;

}
