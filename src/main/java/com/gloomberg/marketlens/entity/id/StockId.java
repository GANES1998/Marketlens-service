package com.gloomberg.marketlens.entity.id;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StockId implements Serializable {

    String symbol;
    Date day;

}
