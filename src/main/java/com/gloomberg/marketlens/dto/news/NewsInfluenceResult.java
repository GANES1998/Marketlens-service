package com.gloomberg.marketlens.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsInfluenceResult {

    String sector;
    Integer year;
    Integer subYear;
    Double value;

}
